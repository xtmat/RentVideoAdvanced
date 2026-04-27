// package com.project.videorental.Exceptions;

// import org.springframework.http.HttpStatusCode;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.MethodArgumentNotValidException;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.RestControllerAdvice;
// import jakarta.servlet.http.HttpServletRequest;

// @RestControllerAdvice
// public class GlobalExceptionHandler {
    

//     @ExceptionHandler(RuntimeException.class)
//     public ResponseEntity<?> allExceptionHandler(HttpServletRequest request, RuntimeException ex){
//         ErrorRecord errorRecord = new ErrorRecord(ex.getMessage(), request.getRequestURI(), request.getMethod());
//         return ResponseEntity.status(HttpStatusCode.valueOf(409)).body(errorRecord);
//     }

//     @ExceptionHandler(MethodArgumentNotValidException.class)
//     public ResponseEntity<?> methodArgumentNotValidExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException ex){
//         ErrorRecord errorRecord = new ErrorRecord(ex.getMessage(), request.getRequestURI(), request.getMethod());
//         return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(errorRecord);
//     }

// }
