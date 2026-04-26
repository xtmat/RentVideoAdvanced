package com.project.videorental.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.videorental.Exchange.Request.AuthRequest;
import com.project.videorental.Exchange.Request.RegisterRequest;
import com.project.videorental.Exchange.Response.AuthReponse;
import com.project.videorental.Service.AuthService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController("authentication_controller")
@RequestMapping("auth/")
@Slf4j
public class AuthController {

    @Autowired
    AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<AuthReponse> register(@Valid @RequestBody RegisterRequest registerRequest){
        AuthReponse authReponse = authService.register(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(authReponse);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthReponse> login(@Valid @RequestBody AuthRequest authRequest){
        AuthReponse authReponse = authService.login(authRequest);
        return ResponseEntity.ok(authReponse);
    }

    
    @GetMapping("/greeting")
    public String getGreeting(){
        log.error("error inside main method");
		log.warn("warning inside main");
		log.debug("dbuge");
		log.trace("this is trace");
		log.info("info");

        return "Good morning";
    }
}
