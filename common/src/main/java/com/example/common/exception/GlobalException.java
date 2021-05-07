package com.example.common.exception;

import com.example.common.web.ResultCode;
import com.example.common.web.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@RestControllerAdvice
@Slf4j
public class GlobalException {
//  为啥在 common 里写的 全局异常处理，捕获不到异常：在使用模块启动注解需要修改为 @SpringBootApplication(scanBasePackages={"com.example.*"})


    @ExceptionHandler(RuntimeException.class)
    public ResultVO<String> RunTimeExceptionHandle(RuntimeException e){
        log.error("----捕获到了RuntimeException异常----:{}",e.getMessage(),e.fillInStackTrace());
        return new ResultVO(ResultCode.ERROR,e.getMessage(),null);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO<String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error("----捕获到了参数校验异常----");
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 然后提取错误提示信息进行返回
        return new ResultVO(ResultCode.VALIDATE_FAILED, objectError.getDefaultMessage(),null);
    }


    @ExceptionHandler(APIException.class)
    public ResultVO<String> APIExceptionHandler(APIException e) {
        // 注意哦，这里返回类型是自定义响应体
        return new ResultVO<>(ResultCode.FAILED, e.getMsg(),null);
    }


}
