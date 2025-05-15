package com.intellij.core.entity;

import com.intellij.core.dto.Base;
import com.intellij.core.enums.ImportRequestType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;

@Entity
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "audit_log")
public class AuditLogEntity extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private ImportRequestType entity;

    private Long numberOfRecords;

    private UUID providerGroupUuid;

    private String providerGroupName;

    private String status;

    private String csvTemplatePath;

}
