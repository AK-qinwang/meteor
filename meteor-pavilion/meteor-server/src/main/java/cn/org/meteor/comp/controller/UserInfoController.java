package cn.org.meteor.comp.controller;

import cn.org.meteor.comp.service.userinfo.UserCredenceReadService;
import cn.org.meteor.comp.vo.UserCredenceInfoVO;
import cn.org.meteor.comp.vo.UserInfoVO;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * *************************************************************************
 * <p/>
 *
 * @文件名称: UserInfoController.java
 * @包 路 径： cn.org.meteor.comp.controller
 * @版权所有：北京数字认证股份有限公司 (C) 2018
 * @类描述:
 * @版本: V1.0 @创建人：wangyangyang
 * @创建时间：2018/11/19 15:58
 */
@Controller
@RequestMapping("/userinfo")
public class UserInfoController {
    @Autowired
    private UserCredenceReadService userCredenceReadService;

    /**
     * 根据登录名获取用户的详细信息
     *
     * @param userCredenceInfoVO
     * @return String
     */
    @PostMapping("/findUserInfo")
    @ResponseBody
    public String findUserInfoByLoginName(@RequestBody UserCredenceInfoVO userCredenceInfoVO) {
        UserInfoVO userInfoVO = userCredenceReadService.findUserInfoByLoginName(userCredenceInfoVO);
        JSONObject jsonObject = new JSONObject();
        if (userInfoVO != null) {
            return JSONObject.toJSONString(userInfoVO);
        } else {
            jsonObject.put("result", "false");
            return jsonObject.toJSONString();
        }
    }
}
