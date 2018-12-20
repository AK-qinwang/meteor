package cn.org.meteor.comp.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class UUIDUtil {
    public static String randomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String getSSNUUID() {
        String replace = UUID.randomUUID().toString().replace("-", "");
        return replace.substring(0, 15).toUpperCase();
    }

    public static String getSSNHashCode() {
        //最大支持1-9个集群机器部署
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        //有可能是负数
        if (hashCodeV < 0) {
            hashCodeV = -hashCodeV;
        }
        Date date = new Date();
        long time = date.getTime();
        return time + "" + hashCodeV;
    }

    public static String getSSNTime() {
        //最大支持1-9个集群机器部署
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        //有可能是负数
        if (hashCodeV < 0) {
            hashCodeV = -hashCodeV;
        }
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String format = sd.format(date);
        String ssn = format + hashCodeV;
        int length = ssn.length();
        if (length < 18) {
            int li = 18 - length;
            Random random = new Random();
            for (int i = 0; i < li; i++) {
                ssn += random.nextInt(10);

            }
        }
        return ssn;
    }
}
