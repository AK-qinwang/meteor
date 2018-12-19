package cn.org.meteor.comp.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserVO implements Serializable {

    @ApiModelProperty(value = "uuid", name = "uuid", example = "1375656756", required = true)
    private String uuid;
    @ApiModelProperty(value = "应用别名", name = "aliasName", example = "alipay", required = true)
    private String aliasName;
    @ApiModelProperty(value = "手机号", name = "mobilePhone", example = "1375656756", required = true)
    private String mobilePhone;
    @ApiModelProperty(value = "用户名", name = "userName", example = "张三", required = true)
    private String userName;
    @ApiModelProperty(value = "状态", name = "status", example = "0", required = true)
    private Integer status;
}
