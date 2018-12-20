package cn.org.meteor.comp.enumeration;

public enum StatusEnum {
    STOP("stop", 0),
    ENABLE("enable", 1);

    private String code;
    private Integer value;


    StatusEnum(String code, Integer value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public Integer getValue() {
        return value;
    }

}
