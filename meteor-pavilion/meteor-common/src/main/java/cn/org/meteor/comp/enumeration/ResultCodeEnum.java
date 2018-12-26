package cn.org.meteor.comp.enumeration;

public enum ResultCodeEnum {

    REGISTER_USER_ERROR("101000", "注册异常");

    private String code;
    private String desc;

    ResultCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getErrorCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
