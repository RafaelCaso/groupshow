package com.groupshow.authentication;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationRequestDto {
    private String email;
    private String password;
}
