package cn.org.meteor.comp.service.userinfo;

import cn.org.meteor.comp.exception.MeteorException;
import cn.org.meteor.comp.exception.userInfo.UserInfoException;
import cn.org.meteor.comp.vo.UserVO;

/**
 * @author JC
 */
public interface UserWriteService {

    /**
     * 根据用户名密码注册
     *
     * @param userVO 用户vo
     */
    Long userRegisterByPassword(UserVO userVO) throws MeteorException;
}
