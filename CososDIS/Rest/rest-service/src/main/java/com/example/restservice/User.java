package com.example.restservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;

@AllArgsConstructor@Getter@Setter@NoArgsConstructor
public class User {
    private String name;
    private String email;
    private String [] roles;
    private boolean admin;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + Arrays.toString(roles) +
                ", admin=" + admin +
                '}';
    }

}
