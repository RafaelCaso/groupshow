package com.groupshow.authentication;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResetPasswordRequestDto {
    private String email;
    private String currentPassword;
    private String newPassword;
    private String confirmNewPassword;
}
