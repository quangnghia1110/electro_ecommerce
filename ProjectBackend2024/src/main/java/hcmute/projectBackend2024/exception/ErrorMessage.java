package hcmute.projectBackend2024.exception;

import lombok.Value;

import java.time.Instant;

@Value
public class ErrorMessage {
    int statusCode;
    Instant timestamp;
    String message;
    String description;
}
