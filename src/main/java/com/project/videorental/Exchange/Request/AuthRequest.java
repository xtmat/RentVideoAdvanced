package com.project.videorental.Exchange.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthRequest {

    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;
}
