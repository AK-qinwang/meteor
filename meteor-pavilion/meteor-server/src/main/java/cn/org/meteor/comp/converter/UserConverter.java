package cn.org.meteor.comp.converter;

import cn.org.meteor.comp.po.generate.User;
import cn.org.meteor.comp.request.RegisterRequest;
import cn.org.meteor.comp.vo.UserVO;

public class UserConverter {
    public static UserVO toVO(User user) {
        UserVO userVO = new UserVO();
        userVO.setAliasName(user.getAliasName());
        userVO.setMobilePhone(user.getMobilePhone());
        userVO.setStatus(user.getStatus());
        userVO.setUuid(user.getUuid());
        return userVO;
    }

    public static UserVO toVO(RegisterRequest registerRequest) {
        UserVO userVO = new UserVO();
        userVO.setAliasName(registerRequest.getAliasName());
        userVO.setMobilePhone(registerRequest.getMobilePhone());
        userVO.setPassword(registerRequest.getPassword());
        userVO.setUserName(registerRequest.getUserName());
        return userVO;
    }
}
