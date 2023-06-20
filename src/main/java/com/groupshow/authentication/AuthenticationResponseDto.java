package com.groupshow.authentication;

import com.groupshow.user.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponseDto {
    private User user;
    private String accessJwt;
    private String refreshJwt;
}
