package cn.org.meteor.comp.service.userinfo.converter;

import cn.org.meteor.comp.po.generate.User;
import cn.org.meteor.comp.vo.UserVO;

public class UserConverter {

    public static User toPO(UserVO userVO) {
        User user = new User();
        user.setAliasName(userVO.getAliasName());
        user.setMobilePhone(user.getMobilePhone());
        user.setStatus(user.getStatus());
        user.setUuid(user.getUuid());
        return user;
    }
}
