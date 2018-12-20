package cn.org.meteor.comp.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVO implements Serializable {

    private String uuid;
    private String aliasName;
    private String password;
    private String mobilePhone;
    private String userName;
    private Integer status;
}
