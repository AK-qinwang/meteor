package cn.org.meteor.comp.controller;

import cn.org.meteor.comp.converter.LoginConverter;
import cn.org.meteor.comp.po.generate.UserInfo;
import cn.org.meteor.comp.request.LoginRequest;
import cn.org.meteor.comp.result.Result;
import cn.org.meteor.comp.service.userinfo.UserCredenceReadService;
import cn.org.meteor.comp.util.Annotation;
import cn.org.meteor.comp.validator.LoginValidator;
import cn.org.meteor.comp.vo.LoginVO;
import cn.org.meteor.comp.vo.UserCredenceInfoVO;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RestController
@RequestMapping("/login")
@Api(description = "用户", tags = {"用户"})
@Slf4j
public class LoginController {

    @Autowired
    private UserCredenceReadService userCredenceReadService;

    @RequestMapping("/loginView")
    public String loginView(ModelMap result) {
        result.put("name", "欢迎");
        return "user/login";
    }

    @PostMapping(value = "loginByPassword")
    @ApiOperation(value = "密码登录", notes = "密码登录", httpMethod = "POST")
    public Result loginByPassword(@RequestBody LoginRequest loginRequest) {
        try {
            LoginValidator.checkLogin(loginRequest);
            LoginVO loginVO = LoginConverter.toVO(loginRequest);
            String token = userCredenceReadService.loginByPassword(loginVO);
            return Result.success(token);
        } catch (Exception e) {
            return Result.fail(e);
        }
    }

    @RequestMapping("/portal")
    public void loginByIDToken1AndIDToken2(@RequestBody UserCredenceInfoVO userCredenceInfoVO) {
        //获取登录名,密码
        String loginName = userCredenceInfoVO.getLoginName();
        String credenceAppend = userCredenceInfoVO.getCredenceAppend();
        System.out.println("登录名:" + loginName + "密码:" + credenceAppend);
    }
}
