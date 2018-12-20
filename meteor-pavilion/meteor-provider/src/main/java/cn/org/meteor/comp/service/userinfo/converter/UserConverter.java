package cn.org.meteor.comp.service.userinfo.converter;

import cn.org.meteor.comp.po.generate.LoginAccount;
import cn.org.meteor.comp.po.generate.User;
import cn.org.meteor.comp.vo.UserVO;

public class UserConverter {

    public static User toUserPO(UserVO userVO) {
        User user = new User();
        user.setAliasName(userVO.getAliasName());
        user.setMobilePhone(userVO.getMobilePhone());
        user.setStatus(userVO.getStatus());
        user.setUuid(userVO.getUuid());
        return user;
    }

    public static LoginAccount toLoginAccount(UserVO userVO) {
        LoginAccount loginAccount = new LoginAccount();
        loginAccount.setLoginName(userVO.getMobilePhone());
        loginAccount.setPassword(userVO.getPassword());
        loginAccount.setStatus(userVO.getStatus());
        return loginAccount;
    }
}
