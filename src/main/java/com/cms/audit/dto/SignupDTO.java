package com.cms.audit.dto;

import com.cms.audit.models.entities.users.UserProfile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignupDTO {

    private String email;
    private String username;
    private String password;
    private UserProfile profile;

}
