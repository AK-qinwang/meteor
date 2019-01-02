package cn.org.meteor.comp.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author JC
 */
@Data
@ApiModel(value = "LoginRequest", description = "用户注册对象")
public class LoginRequest {
    @ApiModelProperty(value = "密码(前端md5加密)", name = "password", example = "123456")
    private String password;
    @ApiModelProperty(value = "用户名", name = "LoginName", example = "张三", required = true)
    private String loginName;
    @ApiModelProperty(value = "验证码", name = "verificationCode", example = "张三")
    private String verificationCode;
}
