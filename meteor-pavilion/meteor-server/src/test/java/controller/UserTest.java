package controller;

import cn.org.meteor.comp.Application;
import cn.org.meteor.comp.converter.UserConverter;
import cn.org.meteor.comp.request.RegisterRequest;
import cn.org.meteor.comp.service.userinfo.UserWriteService;
import cn.org.meteor.comp.validator.UserValidator;
import cn.org.meteor.comp.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class UserTest {

    @Resource
    private StringEncryptor stringEncryptor;

    @Resource
    private UserWriteService userWriteService;

    @Test
    public void encryptPwd() {
        String result = stringEncryptor.encrypt("_Nx9KtSktp**");
        String decrypt = stringEncryptor.decrypt("Sekd2lUFVgmhfwPU2GxDVZEQtzwHLFYy");
        System.out.println(result);
    }

    @Test
    public void passwordRegister() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setMobilePhone("15868420843");
        registerRequest.setAliasName("jd");
        registerRequest.setPassword("123456");
        Map<String, Object> data = new HashMap<>(16);
        try {
            UserValidator.checkUser(registerRequest);
            UserVO userVO = UserConverter.toVO(registerRequest);
            Long userId = userWriteService.userRegisterByPassword(userVO);
            data.put("userId", userId);
        } catch (Exception e) {
            log.error("用户注册异常", e);
        }
    }

}
