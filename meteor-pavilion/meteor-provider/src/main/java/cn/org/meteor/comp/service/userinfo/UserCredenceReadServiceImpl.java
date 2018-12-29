package cn.org.meteor.comp.service.userinfo;

import cn.org.meteor.comp.enumeration.ResultCodeEnum;
import cn.org.meteor.comp.exception.userInfo.UserInfoException;
import cn.org.meteor.comp.mapper.generate.LoginAccountMapper;
import cn.org.meteor.comp.mapper.generate.UserCredenceInfoMapper;
import cn.org.meteor.comp.mapper.generate.UserInfoMapper;
import cn.org.meteor.comp.po.generate.LoginAccount;
import cn.org.meteor.comp.po.generate.UserCredenceInfo;
import cn.org.meteor.comp.po.generate.UserCredenceInfoExample;
import cn.org.meteor.comp.po.generate.UserInfo;
import cn.org.meteor.comp.util.RedisUtil;
import cn.org.meteor.comp.util.TokenUtil;
import cn.org.meteor.comp.vo.LoginVO;
import cn.org.meteor.comp.vo.UserCredenceInfoVO;
import cn.org.meteor.comp.vo.UserInfoVO;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.UserException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * *************************************************************************
 * <p/>
 *
 * @文件名称: UserCredenceReadServiceImpl.java
 * @包 路 径： cn.org.meteor.comp.service.userinfo
 * @版权所有：北京数字认证股份有限公司 (C) 2018
 * @类描述:
 * @版本: V1.0 @创建人：wangyangyang
 * @创建时间：2018/11/19 15:06
 */
@Service
public class UserCredenceReadServiceImpl implements UserCredenceReadService {
    @Resource
    private UserCredenceInfoMapper userCredenceInfoMapper;
    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private LoginAccountMapper loginAccountMapper;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Cacheable(value = RedisUtil.METEOR_PREFIX + "meteor_user", key = "#userCredenceInfoInfoVO.loginName", unless = "#result == null")
    @Override
    public UserInfoVO findUserInfoByLoginName(UserCredenceInfoVO userCredenceInfoInfoVO) {
        try {
            System.out.println("----查数据库----");
            UserInfoVO userInfoVO = new UserInfoVO();
            String loginName = userCredenceInfoInfoVO.getLoginName();
            String uniqueId = "";
            UserCredenceInfoExample userCredenceInfoExample = new UserCredenceInfoExample();
            userCredenceInfoExample.createCriteria().andLoginNameEqualTo(loginName);
            List<UserCredenceInfo> userCredenceInfos = userCredenceInfoMapper.selectByExample(userCredenceInfoExample);
            if (!CollectionUtils.isEmpty(userCredenceInfos) && userCredenceInfos.size() == 1) {
                uniqueId = userCredenceInfos.get(0).getUniqueid();
            }
            if (StringUtils.isNotBlank(uniqueId)) {
                UserInfo userInfo = userInfoMapper.selectByPrimaryKey(uniqueId);
                if (userInfo != null) {
                    PropertyUtils.copyProperties(userInfoVO, userInfo);
                    return userInfoVO;
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String loginByPassword(LoginVO loginVO) throws Exception {
        //获取账户信息
        LoginAccount byLoginName = loginAccountMapper.findByLoginName(loginVO.getLoginName());
        if (byLoginName == null) {
            throw new UserInfoException("该用户不存在", ResultCodeEnum.USER_NOT_EXIST_ERROR.getCode());
        }
        if (!loginVO.getPassword().equals(byLoginName.getPassword())) {
            throw new UserInfoException("密码错误", ResultCodeEnum.PASSWORD_ERROR.getCode());
        }
        //生成token
        String token = TokenUtil.getToken(loginVO.getLoginName(), loginVO.getPassword());
        //存入redis
        redisTemplate.opsForValue().set("token", token, 60 * 30, TimeUnit.MILLISECONDS);
        return token;
    }
}
