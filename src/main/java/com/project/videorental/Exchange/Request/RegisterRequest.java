package com.project.videorental.Exchange.Request;

import org.hibernate.validator.constraints.Length;

import com.project.videorental.Model.Enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegisterRequest {
    
    private String firstName;
    private String lastName;
    @NotNull
    @Email
    private String email;
    @NotNull
    @Length(min=4, max = 10)
    private String password;
    private Role role;

}
