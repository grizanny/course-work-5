package com.arah.cwa.gui.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer id;

    private String login;

    private String password;

    private UserRole role;

    private String email;

    private String firstName;

    private String secondName;

    private Integer age;
}
