package com.groupshow.authentication;

import com.groupshow.user.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AuthenticationResponseDto {
    private User user;
    private LocalDateTime accessJwtExpiresOn;
    private LocalDateTime refreshJwtExpiresOn;
}
