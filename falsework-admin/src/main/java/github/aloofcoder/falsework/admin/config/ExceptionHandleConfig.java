package github.aloofcoder.falsework.admin.config;

import github.aloofcoder.falsework.common.util.AppException;
import github.aloofcoder.falsework.common.util.ErrorCode;
import github.aloofcoder.falsework.common.util.R;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author hanle
 * @date 2020/9/3 14:58
 */
@Slf4j
@RestControllerAdvice
public class ExceptionHandleConfig {

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String msg = bindingResult.getFieldError().getDefaultMessage();
        log.error("请求参数错误", e);
        return R.error(ErrorCode.USER_REQ_ERR.getCode(), msg);
    }

    @RequestBody
    @ExceptionHandler(BindException.class)
    public R bindExceptionHandler(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        String msg = bindingResult.getFieldError().getDefaultMessage();
        log.error("请求参数错误", e);
        return R.error(ErrorCode.USER_REQ_ERR.getCode(), msg);
    }

    @ExceptionHandler(value = AppException.class)
    public R appExceptionHandler(AppException e) {
        e.printStackTrace();
        log.error("app exception：{}", e.getMsg());
        return R.error(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(value = Exception.class)
    public R exceptionHandler(Exception e) {
        e.printStackTrace();
        log.error("system exception：{}", e.getMessage());
        return R.error(ErrorCode.SERVER_ERR.getCode(), "服务异常");
    }

    @ExceptionHandler(value = Throwable.class)
    public R errorHandle(Throwable e) {
        log.error("system error：{}", e.getMessage());
        return R.error(ErrorCode.SERVER_ERR.getCode(), "服务错误");
    }
}
