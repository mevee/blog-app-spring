package com.vee.Blogapp.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
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

}
