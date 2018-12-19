package cn.org.meteor.comp.service.userinfo;

import cn.org.meteor.comp.mapper.generate.UserCredenceInfoMapper;
import cn.org.meteor.comp.mapper.generate.UserInfoMapper;
import cn.org.meteor.comp.po.generate.UserCredenceInfo;
import cn.org.meteor.comp.po.generate.UserCredenceInfoExample;
import cn.org.meteor.comp.po.generate.UserInfo;
import cn.org.meteor.comp.util.RedisUtil;
import cn.org.meteor.comp.vo.UserCredenceInfoVO;
import cn.org.meteor.comp.vo.UserInfoVO;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

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
    UserCredenceInfoMapper userCredenceInfoMapper;
    @Resource
    UserInfoMapper userInfoMapper;


    @Cacheable(value = RedisUtil.METEOR_PREFIX+"meteor_user",key = "#userCredenceInfoInfoVO.loginName",unless = "#result == null")
    @Override
    public UserInfoVO findUserInfoByLoginName(UserCredenceInfoVO userCredenceInfoInfoVO) {
        try {
            System.out.println("----查数据库----");
            UserInfoVO userInfoVO = new UserInfoVO();
            String loginName = userCredenceInfoInfoVO.getLoginName();
            String uniqueid = "";
            UserCredenceInfoExample userCredenceInfoExample = new UserCredenceInfoExample();
            userCredenceInfoExample.createCriteria().andLoginNameEqualTo(loginName);
            List<UserCredenceInfo> userCredenceInfos = userCredenceInfoMapper.selectByExample(userCredenceInfoExample);
            if(!CollectionUtils.isEmpty(userCredenceInfos) && userCredenceInfos.size()==1){
                uniqueid = userCredenceInfos.get(0).getUniqueid();
            }
            if(StringUtils.isNotBlank(uniqueid)){
                UserInfo userInfo = userInfoMapper.selectByPrimaryKey(uniqueid);
                if(userInfo != null){
                    PropertyUtils.copyProperties(userInfoVO,userInfo);
                    return userInfoVO;
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
