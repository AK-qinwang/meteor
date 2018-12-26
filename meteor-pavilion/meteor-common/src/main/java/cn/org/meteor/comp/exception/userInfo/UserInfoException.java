package cn.org.meteor.comp.exception.userInfo;

import cn.org.meteor.comp.exception.MeteorException;

/**
 * *************************************************************************
 * <p/>
 *
 * @文件名称: UserInfoException.java
 * @包 路 径： cn.org.meteor.comp.exception.userInfo
 * @版权所有：北京数字认证股份有限公司 (C) 2018
 * @类描述:
 * @版本: V1.0 @创建人：wangyangyang
 * @创建时间：2018/12/26 17:43
 */
public class UserInfoException extends MeteorException {

    public UserInfoException(String message) {
        super(message);
    }

    public UserInfoException(String message, String errorCode) {
        super(message, errorCode);
    }

    /**
     * 构造方法
     *
     * @param errorCode       错误码
     * @param argsKey         错误信息中参数对应的KEY
     * @param nestedException 异常历史
     */
    public UserInfoException(String errorCode, String[] argsKey, Throwable nestedException) {
        super(RESOURCE_BUNDLE_NAME, errorCode, argsKey, nestedException);
    }

    public UserInfoException(String errorCode, String[] argsKey) {
        super(RESOURCE_BUNDLE_NAME, errorCode, argsKey, null);
    }

    public static String REGISTER_USER_ERROR = "101000";
}
