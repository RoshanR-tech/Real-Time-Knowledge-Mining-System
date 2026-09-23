package com.knowledgemining.backend.dto;

public class LoginResponse {

    private boolean success;
    private Long id;
    private String name;
    private String email;
    private String role;

    public LoginResponse(boolean success, Long id, String name, String email, String role) {
        this.success = success;
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public boolean isSuccess() {
        return success;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}