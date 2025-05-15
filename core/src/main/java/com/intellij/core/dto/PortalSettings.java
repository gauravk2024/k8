package com.intellij.core.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PortalSettings {

    private String logo;

    @NotNull(message = "Portal settings whitelabel can not be null!")
    @Pattern(regexp = "^$|^[a-z0-9]+$", message = "Invalid Portal settings whitelabel format, it should be lowercase alphanumric.")
    @Size(max = 20, message = "Portal settings whitelabel should not exceed {max} characters")
    private String whiteLabel;

    @NotBlank(message = "Portal settings theme color can not be blanked!")
    private String themeColor;

    private String companyName;
    private String dba;

    private String pgCode;
}