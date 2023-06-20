package com.groupshow.authentication;

import com.groupshow.user.GradeLevel;
import com.groupshow.user.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequestDto {
    private UserRole userRole;
    private String firstName;
    private String lastName;
    private String email;
    private GradeLevel gradeLevel;
    private String major;
    private String minor;
}
