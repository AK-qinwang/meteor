package cn.org.meteor.comp.converter;

import cn.org.meteor.comp.request.LoginRequest;
import cn.org.meteor.comp.vo.LoginVO;

public class LoginConverter {

    public static LoginVO toPasswordVO(LoginRequest loginRequest) {
        LoginVO loginVO = new LoginVO();
        loginVO.setLoginName(loginRequest.getLoginName());
        loginVO.setPassword(loginRequest.getPassword());
        return loginVO;
    }

    public static LoginVO toVerificationCodeVO(LoginRequest loginRequest) {
        LoginVO loginVO = new LoginVO();
        loginVO.setLoginName(loginRequest.getLoginName());
        loginVO.setVerificationCode(loginRequest.getVerificationCode());
        return loginVO;
    }
}
