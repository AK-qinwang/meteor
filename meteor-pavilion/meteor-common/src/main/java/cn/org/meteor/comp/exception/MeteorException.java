package cn.org.meteor.comp.exception;

import java.util.Locale;

/**
 * *************************************************************************
 * <p/>
 *
 * @文件名称: MeteorException.java
 * @包 路 径： cn.org.meteor.comp.exception
 * @版权所有：北京数字认证股份有限公司 (C) 2018
 * @类描述:
 * @版本: V1.0 @创建人：wangyangyang
 * @创建时间：2018/12/26 17:42
 */
public class MeteorException extends BaseException{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    // 异常信息使用的资源文件
    public static final String RESOURCE_BUNDLE_NAME = "error";
    // 系统异常
    public static final String SYSTEM_ERROR = "100000";

    //没有操作权限
    public static final String NO_PERMISSION = "100001";

    //系统未被授权
    public static final String NOT_AUTHORITY_CODE = "100002";

    public static final String NOT_AUTHORITY_MESSAGE="系统未被授权！";

    //输入数据中含有非法参数
    public static final String Illegal_PARAM = "100003";

    /**
     * 构造方法
     *
     * @param message
     *            异常信息
     */
    public MeteorException(String message) {
        super(message);
    }

    /**
     * 构造方法
     *
     * @param message
     *            错误信息
     * @param nestedException
     *            异常历史
     */
    public MeteorException(String message, Throwable nestedException) {
        super(message, nestedException);
    }

    /**
     * 构造方法
     * @param rbName 错误资源文件名
     * @param errorCode 错误码
     * @param argsKey 错误信息中的参数值所使用的KEY
     * @param nestedException 异常历史
     */
    public MeteorException(String rbName, String errorCode, String[] argsKey,
                           Throwable nestedException) {
        super(rbName, errorCode, argsKey, nestedException);
    }

    public MeteorException(String errorCode, String[] argsKey,
                           Throwable nestedException) {
        super(RESOURCE_BUNDLE_NAME, errorCode, argsKey, nestedException);
    }

    /**
     * 获得本地化后的异常信息
     *
     * @return 获得本地化后的异常信息
     */
    public String getL10NMessage() {
        return super.getL10NMessage(Locale.SIMPLIFIED_CHINESE);
    }

    /**
     * 获得本地化后的异常信息，包括异常历史信息
     *
     * @return
     */
    public String getCompleteL10NMessage() {
        return super.getCompleteL10NMessage(Locale.SIMPLIFIED_CHINESE);
    }
    /**
     * 获得本地化后的异常信息
     *
     * @return 获得本地化后的异常信息
     */
    public String getL10NMessage(Locale locale) {
        return super.getL10NMessage(locale);
    }

    /**
     * 获得本地化后的异常信息，包括异常历史信息
     *
     * @return
     */
    public String getCompleteL10NMessage(Locale locale) {
        return super.getCompleteL10NMessage(locale);
    }
}
