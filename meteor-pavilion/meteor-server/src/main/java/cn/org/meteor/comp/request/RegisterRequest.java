package cn.org.meteor.comp.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author JC
 */
@Data
@ApiModel(value = "RegisterRequest", description = "用户注册对象")
public class RegisterRequest {
    @ApiModelProperty(value = "应用别名", name = "aliasName", example = "alipay", required = true)
    private String aliasName;
    @ApiModelProperty(value = "手机号", name = "mobilePhone", example = "1375656756", required = true)
    private String mobilePhone;
    @ApiModelProperty(value = "密码", name = "password", example = "123456", required = true)
    private String password;
    @ApiModelProperty(value = "用户名", name = "userName", example = "张三")
    private String userName;
}
