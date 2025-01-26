package com.apps.backend.dto;

import com.apps.backend.model.Authority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class RegisterRequestDTO {
    @NotBlank(message = "username field is required")
    private String username;

    @NotBlank(message = "password field is required")
    private String password;

    @NotBlank(message = "first name field is required")
    private String firstName;

    @NotBlank(message = "last name field is required")
    private String lastName;

    @NotEmpty(message = "authorities should not be empty")
    private List<Authority> authorities;

    public RegisterRequestDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }
}
