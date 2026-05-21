package com.adarsh.backend.shared.application.port;

public interface EmailPort {
    void execute(String email,String code);
}
