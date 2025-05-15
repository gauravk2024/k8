package com.intellij.core.entity;

import com.intellij.core.dto.Base;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role")
@EntityListeners(AuditingEntityListener.class)
public class RoleEntity extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private UUID uuid = UUID.randomUUID();

    private String roleName;

    private String description;

    @ManyToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "id")
    private UserEntity admin;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "role_permission_mapping",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id")}
    )
    private Set<PermissionEntity> permissionEntitySet = new HashSet<>();

    private boolean isPredefined;

    private boolean active = true;

    private boolean deleted = false;
}
