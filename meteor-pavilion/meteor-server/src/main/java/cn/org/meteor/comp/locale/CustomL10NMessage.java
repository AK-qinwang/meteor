package cn.org.meteor.comp.locale;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * 
 * Title:CustomL10NMessage
 * 
 * Description: 本地化消息类
 * 
 * Company: BJCA
 * 
 * @author ZZW
 */
public class CustomL10NMessage implements L10NMessage {
	// 缓存
	private static ResourceBundleCache amCache = ResourceBundleCache
			.getInstance();
	// 消息在文件中的KEY
	private String msgKey = null;
	// 消息参数在文件中的KEY
	private String[] argsKey = null;
	// 消息参数值（不使用KEY，直接使用值）
	private Object[] vArgsValue = null;
	// 消息文件名
	private String bundleName = null;
	// 文件句柄
	private ResourceBundle bundle = null;

	/**
	 * 构造方法
	 * 
	 * @param rbName
	 *            资源文件
	 * @param msgKey
	 *            消息key
	 */
	public CustomL10NMessage(String rbName, String msgKey) {
		this.bundleName = rbName;
		this.msgKey = msgKey;
	}

	/**
	 * 构造方法
	 * 
	 * @param rbName
	 *            资源文件名
	 * @param msgKey
	 *            消息key
	 * @param argsKey
	 *            消息参数使用的key
	 */
	public CustomL10NMessage(String rbName, String msgKey, String[] argsKey) {
		this.bundleName = rbName;
		this.msgKey = msgKey;
        this.argsKey = Arrays.copyOf(argsKey, argsKey.length);
	}

	/**
	 * 获得消息，默认使用Locale.SIMPLIFIED_CHINESE
	 */
	public String getMessage() {
		return getL10NMessage(java.util.Locale.SIMPLIFIED_CHINESE);
	}

	/**
	 * 通过Locale对象获得消息
	 */
	public String getL10NMessage(java.util.Locale locale) {
		String result = null;

		if (this.msgKey != null && this.bundleName != null && locale != null) {
			this.bundle = amCache.getResBundle(this.bundleName, locale);
			String mid = this.bundle.getString(this.msgKey);
			if (this.argsKey == null || this.argsKey.length == 0) {
				if (this.vArgsValue != null && this.vArgsValue.length > 0) {
					result = MessageFormat.format(mid, vArgsValue);
				}else{
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

	public String getResourceBundleName() {
		return this.bundleName;
	}

	public String getMessageKey() {
		return this.msgKey;
	}

	public String[] getMessageArgsKey() {
		return this.argsKey;
	}

    public void setvArgsValue(Object[] vArgsValue) {
        this.vArgsValue = Arrays.copyOf(vArgsValue, vArgsValue.length);
    }
}
