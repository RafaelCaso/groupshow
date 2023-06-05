package com.groupshow.utilities.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
