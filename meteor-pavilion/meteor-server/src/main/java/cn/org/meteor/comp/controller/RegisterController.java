package cn.org.meteor.comp.controller;

import cn.org.meteor.comp.result.Result;
import cn.org.meteor.comp.service.userinfo.UserWriteService;
import cn.org.meteor.comp.vo.UserVO;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RegisterController {
    @Resource
    private UserWriteService userWriteService;

    @RequestMapping(value = "passwordRegister", method = RequestMethod.POST)
    public String passwordRegister(UserVO userVO) {
        userWriteService.userRegisterByPassword(userVO);
        Map<String, Object> data = new HashMap<String, Object>(16);
        return JSON.toJSONString(Result.success(data));
    }
}
