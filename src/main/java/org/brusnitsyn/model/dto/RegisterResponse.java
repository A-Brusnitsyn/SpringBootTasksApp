package org.brusnitsyn.model.dto;

public class RegisterResponse {
    private boolean success;
    private String message;
    private Long userId;
    private String username;

    public RegisterResponse(boolean success, String errorMessage) {
    }

    // Статические фабричные методы
    public static RegisterResponse success(String message, Long userId, String username) {
        return new RegisterResponse(true, message, userId, username);
    }
    

    public static RegisterResponse error(String message) {
        return new RegisterResponse(false, message, null, null);
    }

    public RegisterResponse(boolean success, String message, Long userId, String username) {
        this.success = success;
        this.message = message;
        this.userId = userId;
        this.username = username;
    }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}