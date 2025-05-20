package com.intellij.core.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

@Slf4j
public class KubernetesAuthenticationFilter extends OncePerRequestFilter {

    private final String publicKeyPath;
    private final String tokenAudience;
    private PublicKey publicKey;

    public KubernetesAuthenticationFilter(String publicKeyPath, String tokenAudience) {
        this.publicKeyPath = publicKeyPath;
        this.tokenAudience = tokenAudience;
        loadPublicKey();
    }

    private void loadPublicKey() {
        try {
            String keyContent = new String(Files.readAllBytes(Paths.get(publicKeyPath)));
            keyContent = keyContent.replace("-----BEGIN PUBLIC KEY-----", "")
                    .replace("-----END PUBLIC KEY-----", "")
                    .replaceAll("\\s", "");
            
            byte[] keyBytes = Base64.getDecoder().decode(keyContent);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            log.error("Failed to load Kubernetes public key", e);
            throw new RuntimeException("Failed to load Kubernetes public key", e);
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                  HttpServletResponse response, 
                                  FilterChain filterChain) throws ServletException, IOException {
        
        String token = extractToken(request);
        if (token != null) {
            try {
                Jws<Claims> claims = validateToken(token);
                
                // Extract service account information
                String serviceAccountName = claims.getBody().get("sub", String.class);
                String namespace = claims.getBody().get("kubernetes.io/serviceaccount/namespace", String.class);
                
                // Create authentication object
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    serviceAccountName,
                    null,
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_KUBERNETES_SERVICE_ACCOUNT"))
                );
                
                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.info("Successfully authenticated service account: {} in namespace: {}", 
                    serviceAccountName, namespace);
            } catch (Exception e) {
                log.error("Failed to validate Kubernetes token", e);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }
        
        filterChain.doFilter(request, response);
    }
    
    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
    
    private Jws<Claims> validateToken(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(publicKey)
            .requireAudience(tokenAudience)
            .build()
            .parseClaimsJws(token);
    }
} 