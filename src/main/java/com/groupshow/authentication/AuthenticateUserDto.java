package com.groupshow.authentication;

import com.groupshow.user.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AuthenticateUserDto {
    private User user;
    private String accessJwt;
    private LocalDateTime accessJwtExpiresOn;
    private String refreshJwt;
    private LocalDateTime refreshJwtExpiresOn;
}
