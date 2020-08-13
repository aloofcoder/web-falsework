package github.aloofcoder.falsework.common.util;

/**
 * @author hanle
 */
public class AppException extends RuntimeException {

    private int code = 500;
    private String msg;

    public AppException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
        this.msg = message;
    }

    public AppException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public AppException(int code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
