package cdhananjay.spring_boot_crud.dto;

import java.time.LocalDateTime;

public class ExceptionResponseDto {
    private LocalDateTime time;
    private int statusCode;
    private String error;
    private String message;
    private String path;

    public ExceptionResponseDto(LocalDateTime time, int statusCode, String error, String message, String path) {
        this.time = time;
        this.statusCode = statusCode;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
