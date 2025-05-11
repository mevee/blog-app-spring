package com.vee.Blogapp.payloads;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Email is required")
    private String email;

    private String mobile;
    private String password;
    private String createdAt;
    private String updatedAt;
    private String image;
    private String blockedBy;
    private Boolean isBlocked;
    private Boolean isDeleted;
    private Boolean isEmailVerified;
    private Boolean isMobileVerified;
    private Boolean authToken;

}
