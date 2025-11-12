package com.appswaves.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;

@AllArgsConstructor
@NoArgsConstructor
@Data
//@Entity()
//@Table()
public class UserEntity {
    private String fullName;
    private String email;
    private String password;
    private String DateOfBirthDate;
    @Enumerated(EnumType.STRING)
    private Role role;

}
