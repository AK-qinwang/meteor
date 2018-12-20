package cn.org.meteor.comp.mapper.generate;

import cn.org.meteor.comp.po.generate.User;
import cn.org.meteor.comp.po.generate.UserInfo;
import cn.org.meteor.comp.po.generate.UserInfoExample;

import java.util.List;

public interface UserMapper {

    /**
     * 新增用户
     *
     * @param user 用户实体
     * @return id
     */
    Long insert(User user);

}