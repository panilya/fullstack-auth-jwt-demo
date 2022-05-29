package com.panilya.authappserver.dto;

import com.panilya.authappserver.model.UserRole;

import java.util.List;

public class SignupDTO {
    private Long id;
    private String username;
    private String password;
    private List<UserRole> userRoles;

    public SignupDTO(Long id, String username, String password, List<UserRole> userRoles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userRoles = userRoles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }
}
