package com.spring.learningRest.business;

import lombok.Data;

@Data
public class LoginBody {
    private String username;
    private String password;

    public LoginBody(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // You may also provide a no-argument constructor if needed
    public LoginBody() {
    }
}

