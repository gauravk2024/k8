package com.intellij.core.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleResponse {

    private UUID uuid;

    private boolean isPredefined;

    private String roleName;

    private String description;

    private boolean active;

    private boolean deleted;

}
