package com.cms.audit.dto.response;

import org.springframework.http.HttpStatus;

import com.cms.audit.models.entities.users.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    
    private String token;
    private User data;
    private String err;
    private HttpStatus status;

}
