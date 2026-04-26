package com.project.videorental.Exceptions;

public record ErrorRecord(String message, String requestUri, String requestMethod) {
    
}
