package com.goodhomes.authentication.dao.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class User{
    @Id
    private Long userId;
    private String email;
    private Boolean isEmailVerified;
    private String phoneNumber;
    private Boolean isPhoneNumberVerified;
    private Boolean isAccountLocked;
    private String lastLogin;
    private Boolean failedLoginAttempts;
    private String createdDate;
    private String updatedDate;
}
