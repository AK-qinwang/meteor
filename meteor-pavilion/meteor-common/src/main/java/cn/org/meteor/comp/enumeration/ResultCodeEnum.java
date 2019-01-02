package cn.org.meteor.comp.enumeration;

public enum ResultCodeEnum {
    SECESS("100000", "成功"),
    FAIL("100001", "系统异常"),
    REGISTER_USER_ERROR("101000", "注册异常"),
    USER_NOT_EXIST_ERROR("101001", "用户不存在"),
    PASSWORD_ERROR("101001", "密码错误");

    private String code;
    private String desc;

    ResultCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
