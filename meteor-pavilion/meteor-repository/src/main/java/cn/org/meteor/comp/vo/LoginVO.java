package cn.org.meteor.comp.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginVO implements Serializable {
    private String LoginName;
    private String password;
    private String verificationCode;
}
