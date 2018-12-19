package cn.org.meteor.comp.service.userinfo;

import cn.org.meteor.comp.mapper.generate.UserMapper;
import cn.org.meteor.comp.po.generate.User;
import cn.org.meteor.comp.service.userinfo.converter.UserConverter;
import cn.org.meteor.comp.vo.UserVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserWriteServiceImpl implements UserWriteService {

    @Resource
    private UserMapper userMapper;

    @Override
    public void userRegisterByPassword(UserVO userVO) {
        User user = UserConverter.toPO(userVO);
        userMapper.insert(user);
    }
}
