package cn.org.meteor.comp.converter;

import cn.org.meteor.comp.po.generate.User;
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
}
