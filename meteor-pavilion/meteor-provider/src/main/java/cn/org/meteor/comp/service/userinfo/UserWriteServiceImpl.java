package cn.org.meteor.comp.service.userinfo;

import cn.org.meteor.comp.enumeration.StatusEnum;
import cn.org.meteor.comp.mapper.generate.LoginAccountMapper;
import cn.org.meteor.comp.mapper.generate.UserMapper;
import cn.org.meteor.comp.po.generate.LoginAccount;
import cn.org.meteor.comp.po.generate.User;
import cn.org.meteor.comp.service.userinfo.converter.UserConverter;
import cn.org.meteor.comp.util.UUIDUtil;
import cn.org.meteor.comp.vo.UserVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author JC
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserWriteServiceImpl implements UserWriteService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private LoginAccountMapper loginAccountMapper;

    @Override
    public Long userRegisterByPassword(UserVO userVO) {
        //TODO 校验用户是否被注册
        User user = UserConverter.toUserPO(userVO);
        initUser(user);
        Long userId = userMapper.insert(user);

        LoginAccount loginAccount = UserConverter.toLoginAccount(userVO);
        initLoginAccount(loginAccount, userId);
        loginAccountMapper.insert(loginAccount);
        return userId;
    }

    private void initUser(User user) {
        user.setStatus(StatusEnum.ENABLE.getValue());
        user.setUuid(UUIDUtil.getSSNTime());
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
    }

    private void initLoginAccount(LoginAccount loginAccount, Long userId) {
        loginAccount.setUserId(userId);
        loginAccount.setStatus(StatusEnum.ENABLE.getValue());
        loginAccount.setCreateTime(LocalDateTime.now());
        loginAccount.setUpdateTime(LocalDateTime.now());
    }
}
