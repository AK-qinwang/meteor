package cn.org.meteor.comp.po.generate;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class User implements Serializable {

    private Long id;

    private String uuid;

    private String aliasName;

    private String mobilePhone;

    private String userName;

    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
