package cn.org.meteor.comp.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserVO implements Serializable {

    private Long id;

    private String uuid;

    private String aliasName;

    private String mobilePhone;

    private String userName;

    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
