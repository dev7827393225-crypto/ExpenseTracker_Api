package org.expensetracker.DTO;

import lombok.*;




@Data
public class LoginDto {

    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
}