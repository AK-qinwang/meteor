package cn.org.meteor.comp.result;


import lombok.Data;

@Data
public class Result {

    private String code;

    private Object data;


    public static Result success(Object data) {
        return new Result("success", data);
    }

    public static Result fail(Object data) {
        return new Result("fail", data);
    }

    public Result(String code, Object data) {
        this.code = code;
        this.data = data;
    }
}
