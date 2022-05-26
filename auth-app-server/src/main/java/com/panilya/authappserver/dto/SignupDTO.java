package com.panilya.authappserver.dto;

import com.panilya.authappserver.model.UserRole;

import java.util.List;

public class SignupDTO {
    private Long id;
    private String usename;
    private String password;
    private List<UserRole> userRoles;

    public SignupDTO(Long id, String usename, String password, List<UserRole> userRoles) {
        this.id = id;
        this.usename = usename;
        this.password = password;
        this.userRoles = userRoles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsename() {
        return usename;
    }

    public void setUsename(String usename) {
        this.usename = usename;
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
