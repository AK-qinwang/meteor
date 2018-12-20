package cn.org.meteor.comp.po.generate;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class LoginAccount implements Serializable {

    private Long id;

    private Long userId;

    private String loginName;

    private String password;

    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
