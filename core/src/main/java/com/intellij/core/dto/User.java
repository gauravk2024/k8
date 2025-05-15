package com.intellij.core.dto;


import com.intellij.core.dto.response.RoleResponse;
import com.intellij.core.enums.Category;
import com.intellij.core.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private UUID uuid;

    private String userName;

    @NotBlank(message = "User First Name is mandatory")
    @Pattern(regexp = "[a-zA-Z][a-zA-Z ]+", message ="Invalid User First Name" )
    @Size(min = 2,max = 32,message = "User First Name length should be in-between 2-32 characters")
    private String firstname;

    @NotBlank(message = "User Last Name is mandatory")
    @Pattern(regexp = "[a-zA-Z][a-zA-Z ]+", message ="Invalid User Last Name" )
    @Size(min = 2,max = 32,message = "User Last Name length should be in-between 2-32 characters")
    private String lastname;

    private String middlename;

    @NotBlank(message = "User Email is mandatory")
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message ="Invalid Email Address" )
    @Size(min = 5,max = 64,message = "Email length should be in-between 5-64 characters")
    private String email;

    @Size(min = 10,max = 10,message = "Phone number should be 10 digits")
    @NotNull(message = "Phone number is mandatory")
    private String phone;

    private Gender gender;

    private String fax;

    private Category category;
    private boolean emailVerified;
    private boolean phoneVerified;
    private String iamId;
    private String avtar;

    private Address practiceAddress;

    private Address billingAddress;

    private String website;

    private PortalSettings portalSettings;

    private Boolean isSameAsPracticeAddress;

    private long providerGroupCount;

    private String tenantGroup;
    private RoleResponse roleResponse;
    private boolean active;
    private boolean deleted;
    private Instant lastLogin;
    private Instant createdAt;
    private Instant updatedAt;
    private String createdBy;
    private String modifiedBy;
}
