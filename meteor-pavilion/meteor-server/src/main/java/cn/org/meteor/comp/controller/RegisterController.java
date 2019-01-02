package cn.org.meteor.comp.controller;

import cn.org.meteor.comp.converter.UserConverter;
import cn.org.meteor.comp.exception.MeteorException;
import cn.org.meteor.comp.exception.userInfo.UserInfoException;
import cn.org.meteor.comp.request.RegisterRequest;
import cn.org.meteor.comp.result.Result;
import cn.org.meteor.comp.service.userinfo.UserWriteService;
import cn.org.meteor.comp.service.userinfo.UserWriteServiceImpl;
import cn.org.meteor.comp.validator.UserValidator;
import cn.org.meteor.comp.vo.UserVO;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JC
 */
@Slf4j
@Api(description = "用户", tags = {"用户"})
@RestController
public class RegisterController {
    @Resource
    private UserWriteService userWriteService;

    @PostMapping(value = "passwordRegister")
    @ApiOperation(value = "用户名密码注册", notes = "用户名密码注册", httpMethod = "POST")
    public String passwordRegister(@RequestBody RegisterRequest registerRequest) {
        Map<String, Object> data = new HashMap<>(16);
        try {
            UserValidator.checkUser(registerRequest);
            UserVO userVO = UserConverter.toVO(registerRequest);
            Long userId = userWriteService.userRegisterByPassword(userVO);
            data.put("userId", userId);
        } catch (MeteorException e) {
            return JSON.toJSONString(Result.fail(e));
        }
        return JSON.toJSONString(Result.success(data));
    }
}
