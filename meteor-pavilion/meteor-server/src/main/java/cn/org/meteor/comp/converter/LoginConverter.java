package cn.org.meteor.comp.converter;

import cn.org.meteor.comp.po.generate.User;
import cn.org.meteor.comp.request.LoginRequest;
import cn.org.meteor.comp.request.RegisterRequest;
import cn.org.meteor.comp.vo.LoginVO;
import cn.org.meteor.comp.vo.UserVO;

public class LoginConverter {
    public static LoginVO toVO(LoginRequest loginRequest) {
        LoginVO loginVO = new LoginVO();
        loginVO.setUserName(loginRequest.getUserName());
        loginVO.setPassword(loginRequest.getPassword());
        return loginVO;
    }

}
