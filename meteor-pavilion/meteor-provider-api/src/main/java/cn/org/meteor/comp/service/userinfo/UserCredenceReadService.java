package cn.org.meteor.comp.service.userinfo;

import cn.org.meteor.comp.vo.UserCredenceInfoVO;
import cn.org.meteor.comp.vo.UserInfoVO;

/**
 * *************************************************************************
 * <p/>
 *
 * @文件名称: UserCredenceReadService.java
 * @包 路 径： cn.org.meteor.comp.service.userinfo
 * @版权所有：北京数字认证股份有限公司 (C) 2018
 * @类描述:
 * @版本: V1.0 @创建人：wangyangyang
 * @创建时间：2018/11/19 15:04
 */
public interface UserCredenceReadService {
    UserInfoVO findUserInfoByLoginName(UserCredenceInfoVO userCredenceInfoInfoVO);
}
