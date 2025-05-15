package com.intellij.core.dto.request;

import com.intellij.core.dto.Address;
import com.intellij.core.dto.PortalSettings;
import com.intellij.core.enums.Category;
import com.intellij.core.enums.Gender;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequest {

    @NotBlank(message = "UserName is mandatory")
    private String userName;

    @NotBlank(message = "User Email is mandatory")
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message ="Invalid Email Address" )
    @Size(min = 5,max = 64,message = "Email length should be between 5-64")
    private String email;

    @NotBlank(message = "User First Name is mandatory")
    @Pattern(regexp = "[a-zA-Z][a-zA-Z ]+", message ="Invalid User First Name" )
    @Size(min = 2,max = 32,message = "User First Name length should be in-between 2-32 characters")
    private String firstname;

    @NotBlank(message = "User Last Name is mandatory")
    @Pattern(regexp = "[a-zA-Z][a-zA-Z ]+", message ="Invalid User Last Name" )
    @Size(min = 2,max = 32,message = "User Last Name length should be in-between 2-32 characters")
    private String lastname;

    private String middlename;

    @Size(min = 10,max = 10,message = "Phone length should be 10")
    @NotNull(message = "Phone Number is mandatory")
    private String phone;
    private Gender gender;
    private String dob;
    private String avtar;

    private String fax;

    @NotNull(message = "Invalid category!")
    private Category category;

    @Valid
    private Address practiceAddress;

    private Address billingAddress;

    private Boolean isSameAsPracticeAddress;

    private String website;

    @Valid
    @NotNull(message = "Please fill portal settings details!")
    private PortalSettings portalSettings;

    private String externalLink;
}
