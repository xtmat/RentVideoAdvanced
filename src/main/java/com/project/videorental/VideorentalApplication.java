package com.project.videorental;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class VideorentalApplication {

	// Logger Log = Logger.getLogger("Main logger"); 

	public static void main(String[] args) {
		SpringApplication.run(VideorentalApplication.class, args);
	}

	@GetMapping("/")
	public String welcome(){
		return "Hello from authenticated endpoint";
	}


	@GetMapping("/admins")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String welcomeAdmin(){
		return "hello from admin's endpoint";
	}

}
