package github.aloofcoder.falsework.common.util;

import lombok.Getter;

/**
 * @author hanle
 */
@Getter
public class AppException extends RuntimeException {

    private String code;

    private String msg;

    public AppException() {
        super();
    }

//    public AppException(String message) {
//        super(message);
//        this.msg = message;
//    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
        this.msg = message;
    }

    public AppException(String code, String message) {
        super(message);
        this.code = code;
        this.msg = message;
    }

    public AppException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.msg = message;
    }

    public AppException(ErrorCode error, Throwable cause) {
        super(error.getMsg(), cause);
        this.code = error.getCode();
        this.msg = error.getMsg();
    }

    public AppException(ErrorCode error) {
        super(error.getMsg());
        this.code = error.getCode();
        this.msg = error.getMsg();
    }

    /**
     * 解决lambda表达式里不能抛出受检异常的问题
     *
     * @param code
     * @param message
     * @throws <E>
     */
    public static <E extends Exception> void doThrow(String code, String message) throws E {
        throw (E) new AppException(code, message);
    }

}
