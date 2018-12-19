package cn.org.meteor.comp.service.userinfo;

import cn.org.meteor.comp.vo.UserVO;

public interface UserWriteService {

    void userRegisterByPassword(UserVO userVO);
}
