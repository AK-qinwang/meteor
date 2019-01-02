package cn.org.meteor.comp.exception;


import cn.org.meteor.comp.enumeration.ResultCodeEnum;
import cn.org.meteor.comp.locale.L10NMessage;
import cn.org.meteor.comp.locale.ResourceBundleCache;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * Title:BaseException
 * <p>
 * Description: 异常基类，支持本地化消息
 * <p>
 * Company: BJCA
 *
 * @author ZZW
 */
@SuppressWarnings("serial")
public class BaseException extends Exception implements L10NMessage {
    // 错误码
    private String errorCode = null;
    // 错误信息
    private String message = null;
    // 异常信息中的参数使用的KEY
    private String[] argsKey = null;
    // 异常信息中参数使用的参数值（参数不使用KEY，直接传值）
    private Object[] vArgsValue = null;
    // 异常历史
    private Throwable originalException = null;
    // 错误信息文件缓存
    private static ResourceBundleCache amCache = ResourceBundleCache.getInstance();
    // 错误信息文件名
    private String bundleName = null;
    // 错误信息，文件句柄
    private ResourceBundle bundle = null;

    public BaseException() {
    }

    public BaseException(ResultCodeEnum resultCodeEnum) {
        this.errorCode = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getDesc();
    }

    /**
     * 构造方法
     *
     * @param message 异常信息
     */
    public BaseException(String message) {
        this.message = message;
    }

    public BaseException(String message, String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    /**
     * 构造方法
     *
     * @param message           异常信息
     * @param originalException 异常历史
     */
    public BaseException(String message, Throwable originalException) {
        this.message = message;
        this.originalException = originalException;
    }

    /**
     * 构造方法
     *
     * @param rbName            资源文件
     * @param errorCode         错误码（文件中的KEY）
     * @param originalException 异常历史
     */
    public BaseException(String rbName, String errorCode, Throwable originalException) {
        this.errorCode = errorCode;
        this.bundleName = rbName;
        this.originalException = originalException;
    }

    /**
     * 构造方法
     *
     * @param rbName            资源文件
     * @param errorCode         错误码（文件中的KEY）
     * @param argsKey           错误信息中参数使用的KEY
     * @param originalException
     */
    public BaseException(String rbName, String errorCode, String[] argsKey, Throwable originalException) {
        this.bundleName = rbName;
        this.errorCode = errorCode;
        if (argsKey != null) {
            this.argsKey = Arrays.copyOf(argsKey, argsKey.length);
        }
        this.originalException = originalException;
    }

    /**
     * 直接添加错误信息中的参数值
     *
     * @param argsValue 参数值
     */
    public void addMessageArgsValue(Object[] argsValue) {
        if (argsValue != null && argsValue.length > 0) {
            this.vArgsValue = argsValue.clone();
        }
    }

    /**
     * 根据本地对象获得错误信息，不包括异常历史的信息
     */
    @Override
    public String getL10NMessage(java.util.Locale locale) {
        String result = null;
        if (this.errorCode != null && this.bundleName != null && locale != null) {
            this.bundle = amCache.getResBundle(this.bundleName, locale);
            String mid = this.bundle.getString(this.errorCode);
            if (this.argsKey == null || this.argsKey.length == 0) {
                if (this.vArgsValue != null && this.vArgsValue.length > 0) {
                    result = MessageFormat.format(mid, vArgsValue);
                } else {
                    result = mid;
                }
            } else {
                int argsLen = this.argsKey.length;
                String[] argValue = new String[argsLen];
                for (int i = 0; i < argsLen; i++) {
                    argValue[i] = this.bundle.getString(this.argsKey[i]);
                }
                result = MessageFormat.format(mid, argValue);
            }
        }
        return result;
    }

    /**
     * 根据本地对象获得错误信息，包括异常历史的信息
     *
     * @param locale 本地对象
     * @return
     */
    public String getCompleteL10NMessage(java.util.Locale locale) {
        String result = this.getL10NMessage(locale);
        //加上错误码
        if (result != null) {
            result = this.errorCode + ":" + result;
        }

        String chainedMessage = null;
        if (this.originalException != null) {
            if (this.originalException instanceof L10NMessage) {
                L10NMessage lex = (L10NMessage) this.originalException;
                chainedMessage = lex.getL10NMessage(locale);
                if (chainedMessage != null) {
                    //错误码和消息
                    chainedMessage = lex.getMessageKey() + ":" + chainedMessage;
                }
            } else {
                chainedMessage = this.originalException.getMessage();
            }
        }
        if (chainedMessage != null) {
            if (result != null) {
                result = result + "\n" + chainedMessage;
            } else {
                result = chainedMessage;
            }
        }
        return result;
    }

    @Override
    public String getResourceBundleName() {
        return this.bundleName;
    }

    @Override
    public String getMessageKey() {
        return this.errorCode;
    }

    @Override
    public String[] getMessageArgsKey() {
        return this.argsKey;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public Throwable getOriginalException() {
        return this.originalException;
    }
}
