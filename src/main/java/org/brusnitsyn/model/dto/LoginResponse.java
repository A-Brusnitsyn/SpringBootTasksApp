package org.brusnitsyn.model.dto;

public class LoginResponse {
    private boolean success;
    private String message;
    private String username;
    private Long userId;

    public LoginResponse() {}

    public LoginResponse(boolean success, String message, String username, Long userId) {
        this.success = success;
        this.message = message;
        this.username = username;
        this.userId = userId;
    }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}