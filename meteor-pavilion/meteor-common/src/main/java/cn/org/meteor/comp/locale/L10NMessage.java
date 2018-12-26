package cn.org.meteor.comp.locale;

import java.util.Locale;

/**
 * 
 * Title:L10NMessage
 *
 * Description: 本地化消息接口
 *
 * Company: BJCA
 *
 * @author ZZW
 */
public interface L10NMessage {
	/**
	 * 获得消息
	 * @return String
	 */
    String getMessage();
    
    /**
     * 获得本地化消息
     * @param loc 本地化对象
     * @return String
     */
    String getL10NMessage(Locale loc);


    /**
     * 获得资源文件名
     * @return  String
     */
    String getResourceBundleName();

    /**
     * 获得消息的在文件中的KEY
     * @return  String
     */
    String getMessageKey();

    /**
     * 获得参数在文件中的KEY集合
     * @return   String
     */
    String[] getMessageArgsKey();

}
