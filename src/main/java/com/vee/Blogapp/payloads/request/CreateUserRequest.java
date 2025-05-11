package com.vee.Blogapp.payloads.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CreateUserRequest {
    private Long id;
    private String name;
    private String email;
    private String mobile;
    private String password;
    private String createdAt;
    private String updatedAt;
    private String image;
    private String blockedBy;
    private Boolean isBlocked;
    private Boolean isDeleted;
    private Boolean isEmailVerified;
    private Boolean isMobileVerified;
    private Boolean authToken;

    public Boolean getAuthToken() {
        return authToken;
    }

    public void setAuthToken(Boolean authToken) {
        this.authToken = authToken;
    }

    public Boolean getMobileVerified() {
        return isMobileVerified;
    }

    public void setMobileVerified(Boolean mobileVerified) {
        isMobileVerified = mobileVerified;
    }

    public Boolean getEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        isEmailVerified = emailVerified;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    public String getBlockedBy() {
        return blockedBy;
    }

    public void setBlockedBy(String blockedBy) {
        this.blockedBy = blockedBy;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CreateUserRequest{" +
                "createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", image='" + image + '\'' +
                ", blockedBy='" + blockedBy + '\'' +
                ", isBlocked=" + isBlocked +
                ", isDeleted=" + isDeleted +
                ", isEmailVerified=" + isEmailVerified +
                ", isMobileVerified=" + isMobileVerified +
                ", authToken=" + authToken +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
