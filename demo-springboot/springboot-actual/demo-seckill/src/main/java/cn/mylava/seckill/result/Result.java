package cn.mylava.seckill.result;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 20/08/2018
 */
public class Result<T> {
    private int code;
    private String message;
    private T data;

    private Result(T data) {
        this.code = 0;
        this.message = "sucess";
        this.data = data;
    }

    public Result(CodeMessage codeMessage) {
        if (null == codeMessage) {
            return;
        }
        this.code = codeMessage.getCode();
        this.message = codeMessage.getMessage();
    }

    /**
     * 成功
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data) {
        return  new Result<T>(data);
    }

    /**
     * 失败
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(CodeMessage codeMessage) {
        return new Result<T>(codeMessage);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

}
