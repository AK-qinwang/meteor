package cn.org.meteor.comp.locale;

import java.io.Serializable;
import java.util.*;

/**
 * 
 * Title:ResourceBundleCache
 * 
 * Description: 资源文件缓存
 * 
 * Company: BJCA
 * 
 * @author ZZW
 */
public class ResourceBundleCache implements Serializable {
	// 单例
	private static ResourceBundleCache instance = new ResourceBundleCache();
	// 文件名和句柄映射
	@SuppressWarnings("unchecked")
	private Map mapBundles = new HashMap(30);

	private ResourceBundleCache() {
	}

	/**
	 * 获得单实例
	 * 
	 * @return
	 */
	public static ResourceBundleCache getInstance() {
        return instance;
	}

	/**
	 * 获得文件句柄
	 * 
	 * @param name
	 *            文件名
	 * @param locale
	 *            本地化对象
	 * @return
	 * @throws MissingResourceException
	 */
	@SuppressWarnings("unchecked")
	public ResourceBundle getResBundle(String name, Locale locale)
			throws MissingResourceException {
		ResourceBundle resBundle = null;
		Map map = (Map) mapBundles.get(name);

		if (map != null) {
			resBundle = (ResourceBundle) map.get(locale);
		}
		if (resBundle == null) {
			try {
				resBundle = ResourceBundle.getBundle(name, locale);
			} catch (MissingResourceException mre) {
				throw mre;
			}

			synchronized (mapBundles) {
				if (map == null) {
					map = new HashMap(5);
					mapBundles.put(name, map);
				}
				map.put(locale, resBundle);
			}
		}

		return resBundle;
	}

	/**
	 * 情况内存
	 */
	public void clear() {
		synchronized (mapBundles) {
			mapBundles.clear();
		}
	}
}
