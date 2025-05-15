package com.intellij.core.service;

import com.intellij.core.enums.ResponseCode;
import com.intellij.core.exception.HitechException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public abstract class AppService {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected void throwError(ResponseCode code, String message) throws HitechException {
        log.error("Custom Exception : [{}] {}", code, message);
        throw new HitechException(code, message);
    }

    protected void throwError(Exception exception) throws HitechException {
        log.error("Handled Exception : {}", exception.getMessage());
        throwError(new HitechException(exception));


    }

//    protected static User getCurrentUser() throws HitechException {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if(Objects.isNull(auth)) return null;
//        try {
//            SignedJWT signedJWT = SignedJWT.parse(auth.getPrincipal().toString());
//            User user =  signedJWT.getJWTClaimsSet().toType(new ClaimTransformer());
//            return user;
//        }
//        catch (Exception e) {
//            log.error("Exception occured while parsing token: ", e);
//        }
//        return null;
//    }
}