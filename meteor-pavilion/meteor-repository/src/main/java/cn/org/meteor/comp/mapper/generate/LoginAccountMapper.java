package cn.org.meteor.comp.mapper.generate;

import cn.org.meteor.comp.po.generate.LoginAccount;
import org.apache.ibatis.annotations.Param;

public interface LoginAccountMapper {

    /**
     * 创建
     *
     * @param record
     * @return
     */
    int insert(LoginAccount record);

    /**
     * 根据登录名查询登录信息
     *
     * @param loginName 登录名
     * @return LoginAccount
     */
    LoginAccount findByLoginName(@Param(value = "loginName") String loginName);

    int updateByPrimaryKey(LoginAccount record);
}