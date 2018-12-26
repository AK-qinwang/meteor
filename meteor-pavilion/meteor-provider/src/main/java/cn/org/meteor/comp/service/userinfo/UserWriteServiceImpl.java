package cn.org.meteor.comp.service.userinfo;

import cn.org.meteor.comp.enumeration.StatusEnum;
import cn.org.meteor.comp.exception.MeteorException;
import cn.org.meteor.comp.exception.userInfo.UserInfoException;
import cn.org.meteor.comp.mapper.generate.LoginAccountMapper;
import cn.org.meteor.comp.mapper.generate.UserMapper;
import cn.org.meteor.comp.po.generate.LoginAccount;
import cn.org.meteor.comp.po.generate.User;
import cn.org.meteor.comp.service.userinfo.converter.UserConverter;
import cn.org.meteor.comp.util.UUIDUtil;
import cn.org.meteor.comp.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author JC
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class UserWriteServiceImpl implements UserWriteService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private LoginAccountMapper loginAccountMapper;

    @Override
    public Long userRegisterByPassword(UserVO userVO) throws MeteorException {
        try {
            checkUserExist(userVO);
            User user = UserConverter.toUserPO(userVO);
            initUser(user);
            userMapper.insert(user);

            LoginAccount loginAccount = UserConverter.toLoginAccount(userVO);
            initLoginAccount(loginAccount, user.getId());
            loginAccountMapper.insert(loginAccount);
            return user.getId();
        } catch (Exception e) {
            log.error("UserWriteServiceImpl----->userRegisterByPassword error",e);
            throw new UserInfoException(UserInfoException.REGISTER_USER_ERROR,null,e);
        }
    }

    private void checkUserExist(UserVO userVO) {
        User byMobile = userMapper.findByMobile(userVO.getMobilePhone());
        if (byMobile != null) {
            throw new RuntimeException("该手机号已注册");
        }
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
