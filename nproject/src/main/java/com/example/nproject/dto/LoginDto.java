package com.example.nproject.dto;

public class LoginDto {
    private String userId;
    private String password;
    private boolean saveId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isSaveId() {
        return saveId;
    }

    public void setSaveId(boolean saveId) {
        this.saveId = saveId;
    }
}
