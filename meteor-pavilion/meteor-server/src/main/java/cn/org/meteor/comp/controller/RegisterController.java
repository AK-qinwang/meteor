package cn.org.meteor.comp.controller;

import cn.org.meteor.comp.converter.UserConverter;
import cn.org.meteor.comp.exception.MeteorException;
import cn.org.meteor.comp.request.RegisterRequest;
import cn.org.meteor.comp.result.Result;
import cn.org.meteor.comp.service.userinfo.UserWriteService;
import cn.org.meteor.comp.validator.UserValidator;
import cn.org.meteor.comp.vo.UserVO;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JC
 */
@Api(description = "用户注册登录", tags = {"用户"})
@Slf4j
@RestController
public class RegisterController {
    @Resource
    private UserWriteService userWriteService;

    @RequestMapping(value = "passwordRegister", method = RequestMethod.POST)
    @ApiOperation(value = "用户名密码注册", notes = "用户名密码注册", httpMethod = "POST")
    public String passwordRegister(@RequestBody RegisterRequest registerRequest) {
        Map<String, Object> data = new HashMap<>(16);
        try {
            UserValidator.checkUser(registerRequest);
            UserVO userVO = UserConverter.toVO(registerRequest);
            Long userId = userWriteService.userRegisterByPassword(userVO);
            data.put("userId", userId);
        } catch (MeteorException e) {
            log.error("RegisterController----->passwordRegister error,userName=" + registerRequest.getUserName(), e);
            return JSON.toJSONString(Result.fail(e));
        }
        return JSON.toJSONString(Result.success(data));
    }
}
