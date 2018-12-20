package cn.org.meteor.comp.mapper.generate;

import cn.org.meteor.comp.po.generate.User;
import cn.org.meteor.comp.po.generate.UserInfo;
import cn.org.meteor.comp.po.generate.UserInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    /**
     * 新增用户
     *
     * @param user 用户实体
     * @return id
     */
    int insert(User user);

    User findByMobile(@Param(value = "mobilePhone") String mobilePhone);

}