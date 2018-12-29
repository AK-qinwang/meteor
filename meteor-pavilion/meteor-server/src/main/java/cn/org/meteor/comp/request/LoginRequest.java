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
    @ApiModelProperty(value = "密码(前端md5加密)", name = "password", example = "123456", required = true)
    private String password;
    @ApiModelProperty(value = "用户名", name = "LoginName", example = "张三")
    private String loginName;
}
