package com.project.videorental.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.project.videorental.Exchange.Request.AuthRequest;
import com.project.videorental.Exchange.Request.RegisterRequest;
import com.project.videorental.Exchange.Response.AuthReponse;
import com.project.videorental.Model.User;
import com.project.videorental.Model.Enums.Role;
import com.project.videorental.Repository.UserRepository;


@Service
public class AuthService  {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired 
    AuthenticationManager authenticationManager;

    @Autowired 
    JWTService jwtService;

    public AuthReponse register(RegisterRequest registerRequest){
        if(userRepository.existsByEmail(registerRequest.getEmail())){
            throw new RuntimeException("This email id has already been used : "+registerRequest.getEmail());
        }
        if(registerRequest.getRole()==null){
            registerRequest.setRole(Role.CUSTOMER);
        }

        User user = User.builder()
                    .email(registerRequest.getEmail())
                    .password(passwordEncoder.encode(registerRequest.getPassword()))
                    .firstName(registerRequest.getFirstName())
                    .lastName(registerRequest.getLastName())
                    .role(registerRequest.getRole())
                    .build();
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        userRepository.save(user);
        return AuthReponse.builder().accessToken(jwtToken).build();
    }


    public AuthReponse login(AuthRequest authRequest){

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        UserDetails user = userRepository.findUserByEmail(authRequest.getEmail());
        String jwtToken = jwtService.generateToken(user);
        // System.out.println("=========================");
        return AuthReponse.builder().accessToken(jwtToken).build();
    }
    
}
