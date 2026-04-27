package com.project.videorental.Exchange.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class AuthReponse {
    private final String message = "Success";
    private String accessToken;    
}
