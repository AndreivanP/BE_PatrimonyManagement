package com.andreivan.rest.webservices.patrimonymanagement.jwt.resource;

import java.io.Serializable;

public class  JwtTokenRequest implements Serializable {

    private static final long serialVersionUID = -5616176897013108345L;

    private String username;
    private String password;

//    {
//        "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJpbjI4bWludXRlcyIsImV4cCI6MTU5MjE0NTI1OSwiaWF0IjoxNTkxNTQwNDU5fQ.-bvwoIO6Kz07q3SQumaxTf145Qk2l_iJbbQ_rPKHAlxsP6_aduQUmzmbWd8B62l20nTkUIta0Oxd1nbRjAyLzQ"
//    }

    public JwtTokenRequest() {
        super();
    }

    public JwtTokenRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}