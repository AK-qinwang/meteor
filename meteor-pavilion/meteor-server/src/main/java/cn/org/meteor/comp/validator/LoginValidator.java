package cn.org.meteor.comp.validator;

import cn.org.meteor.comp.request.LoginRequest;
import cn.org.meteor.comp.request.RegisterRequest;

import java.util.Optional;

public class LoginValidator {

    public static void checkLogin(LoginRequest loginRequest) {
        Optional.ofNullable(loginRequest.getLoginName()).orElseThrow(() -> new IllegalArgumentException("用户名为空"));
        Optional.ofNullable(loginRequest.getPassword()).orElseThrow(() -> new IllegalArgumentException("密码为空"));
    }
}
