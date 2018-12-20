package cn.org.meteor.comp.validator;

import cn.org.meteor.comp.request.RegisterRequest;

import java.util.Optional;

public class UserValidator {

    public static void checkUser(RegisterRequest registerRequest) {
        Optional.ofNullable(registerRequest.getMobilePhone()).orElseThrow(() -> new IllegalArgumentException("the mobile phone number is empty"));
        Optional.ofNullable(registerRequest.getPassword()).orElseThrow(() -> new IllegalArgumentException("the password is empty"));
    }
}
