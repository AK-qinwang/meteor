package cn.org.meteor.comp.service.userinfo;

import cn.org.meteor.comp.enumeration.ResultCodeEnum;
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
        checkUserExist(userVO);
        User user = initUser(userVO);
        userMapper.insert(user);

        LoginAccount loginAccount = initLoginAccount(userVO, user.getId());
        loginAccountMapper.insert(loginAccount);
        return user.getId();
    }

    private void checkUserExist(UserVO userVO) throws UserInfoException {
        User byMobile = userMapper.findByMobile(userVO.getMobilePhone());
        if (byMobile != null) {
            throw new UserInfoException("该手机号已注册", ResultCodeEnum.REGISTER_USER_ERROR.getErrorCode());
        }
    }

    private User initUser(UserVO userVO) {
        User user = new User();
        user.setAliasName(userVO.getAliasName());
        user.setMobilePhone(userVO.getMobilePhone());
        user.setStatus(userVO.getStatus());
        user.setUuid(userVO.getUuid());
        user.setStatus(StatusEnum.ENABLE.getValue());
        user.setUuid(UUIDUtil.getSSNTime());
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        return user;
    }

    private LoginAccount initLoginAccount(UserVO userVO, Long userId) {
        LoginAccount loginAccount = new LoginAccount();
        loginAccount.setLoginName(userVO.getMobilePhone());
        loginAccount.setPassword(userVO.getPassword());
        loginAccount.setStatus(userVO.getStatus());
        loginAccount.setUserId(userId);
        loginAccount.setStatus(StatusEnum.ENABLE.getValue());
        loginAccount.setCreateTime(LocalDateTime.now());
        loginAccount.setUpdateTime(LocalDateTime.now());
        return loginAccount;
    }
}
