package cn.org.meteor.comp.mapper.generate;

import cn.org.meteor.comp.po.generate.LoginAccount;
import cn.org.meteor.comp.po.generate.User;
import cn.org.meteor.comp.po.generate.UserInfo;

import java.util.List;

public interface LoginAccountMapper {

    /**
     * 创建
     *
     * @param record
     * @return
     */
    int insert(LoginAccount record);

    int updateByPrimaryKey(LoginAccount record);
}