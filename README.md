"# RentVideo"

Project Structure

src/main/java/com/rentvideo/

├── RentVideoApplication.java

├── config/

│   └── SecurityConfig.java

├── model/

│   ├── User.java

│   ├── Video.java

│   └── Role.java

├── repository/

│   ├── UserRepository.java

│   └── VideoRepository.java

├── service/

│   ├── UserService.java

│   ├── VideoService.java

│   └── CustomUserDetailsService.java

├── controller/

│   ├── AuthController.java

│   └── VideoController.java

├── dto/

│   ├── UserRegistrationDto.java

│   ├── UserResponseDto.java

│   └── VideoDto.java

└── exception/

&nbsp;   ├── GlobalExceptionHandler.java

&nbsp;   ├── ResourceNotFoundException.java

&nbsp;   └── DuplicateResourceException.java

