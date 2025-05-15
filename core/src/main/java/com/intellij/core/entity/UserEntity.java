package com.intellij.core.entity;

import com.intellij.core.dto.Base;
import com.intellij.core.enums.Category;
import com.intellij.core.enums.Gender;
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
@Table(name = "users")
public class UserEntity extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID uuid = UUID.randomUUID();

    private String iamId;

    private String userName;

    private String firstName;

    private String middleName;

    private String lastName;

    private String email;

    private String phone;

    private String dob;

    @Column(name = "faxId")
    private String fax;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "practice_address_id", referencedColumnName = "id")
    private AddressEntity practiceAddress;

    @OneToOne
    @JoinColumn(name = "billing_address_id", referencedColumnName = "id")
    private AddressEntity billingAddress;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private RoleEntity role;

    private long mappedByAdminId;

    private String website;

    private Instant lastLogin;

    private boolean isEmailVerified;

    private boolean isPhoneVerified;

    private boolean active = true;

    private boolean deleted = false;

    private Boolean isSameAsPracticeAddress = false;

}
