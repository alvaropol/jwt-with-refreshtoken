package com.salesianos.triana.dam.jwtwithrefreshtoken.security.errorhandling;

public class JwtTokenException extends RuntimeException{

    public JwtTokenException(String msg) {
        super(msg);
    }


}