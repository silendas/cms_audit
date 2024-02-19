package com.cms.audit.dto.response;

import java.util.List;
import java.util.Optional;

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
public class UserResponse {
    
    private User dataUser;
    private List<User> dataList;
    private Optional<User> dataOptional;
    private String err;
    private HttpStatus status;

}
