package cn.org.meteor.comp.controller;

import cn.org.meteor.comp.service.userinfo.UserCredenceReadService;
import cn.org.meteor.comp.vo.UserCredenceInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * *************************************************************************
 * <p/>
 *
 * @文件名称: LoginController.java
 * @包 路 径： cn.org.meteor.comp.controller
 * @版权所有：北京数字认证股份有限公司 (C) 2018
 * @类描述:
 * @版本: V1.0 @创建人：wangyangyang
 * @创建时间：2018/12/3 15:14
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserCredenceReadService userCredenceReadService;

    @RequestMapping("/loginView")
    public String loginView(ModelMap result) {
        result.put("name","欢迎");
        return "user/login";
    }

    @RequestMapping("/portal")
    @ResponseBody
    public void loginByIDToken1AndIDToken2(@RequestBody UserCredenceInfoVO userCredenceInfoVO) {
        //获取登录名,密码
        String loginName = userCredenceInfoVO.getLoginName();
        String credenceAppend = userCredenceInfoVO.getCredenceAppend();
        System.out.println("登录名:"+loginName+"密码:"+credenceAppend);
    }
}
