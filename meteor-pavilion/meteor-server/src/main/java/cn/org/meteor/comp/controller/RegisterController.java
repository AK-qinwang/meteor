package cn.org.meteor.comp.controller;

import cn.org.meteor.comp.result.Result;
import cn.org.meteor.comp.service.userinfo.UserWriteService;
import cn.org.meteor.comp.vo.UserVO;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Api(description = "用户注册登录", tags = {"用户"})
@RestController
public class RegisterController {
    @Resource
    private UserWriteService userWriteService;

    @RequestMapping(value = "passwordRegister", method = RequestMethod.POST)
    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
    public String passwordRegister(UserVO userVO) {
        userWriteService.userRegisterByPassword(userVO);
        Map<String, Object> data = new HashMap<String, Object>(16);
        return JSON.toJSONString(Result.success(data));
    }
}
