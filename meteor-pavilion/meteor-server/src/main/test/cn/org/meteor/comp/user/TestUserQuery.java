package cn.org.meteor.comp.user;

import cn.org.meteor.comp.Application;
import cn.org.meteor.comp.service.userinfo.UserCredenceReadService;
import cn.org.meteor.comp.vo.UserCredenceInfoVO;
import cn.org.meteor.comp.vo.UserInfoVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * *************************************************************************
 * <p/>
 *
 * @文件名称: TestUserQuery.java
 * @包 路 径： cn.org.meteor.comp.user
 * @版权所有：北京数字认证股份有限公司 (C) 2018
 * @类描述:
 * @版本: V1.0 @创建人：wangyangyang
 * @创建时间：2018/12/3 14:04
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class TestUserQuery {
    @Autowired
    UserCredenceReadService userCredenceReadService;

    @Test
    public void test_getUserByLoginName() {
        UserCredenceInfoVO userCredenceInfoVO = new UserCredenceInfoVO();
        userCredenceInfoVO.setLoginName("wyy");
        UserInfoVO userInfoByLoginName = userCredenceReadService.findUserInfoByLoginName(userCredenceInfoVO);
        System.out.println(userInfoByLoginName);
    }
}
