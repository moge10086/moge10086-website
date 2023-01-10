package com.moge10086.website.config;

import com.moge10086.website.common.constant.StatusCode;
import com.moge10086.website.common.utils.JsonResult;
import io.jsonwebtoken.JwtException;
import org.springframework.mail.MailException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.List;

/**
 * ClassName: GlobalExceptionHandler
 * Description:
 *
 * @author sq
 * @date 2022/12/16 20:20
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 顶级异常
     * @param resp
     * @param ex
     * @return
     * @throws IOException
     */
//    @ExceptionHandler(Exception.class)
//    public JsonResult<String> handleException(HttpServletResponse resp, Exception ex) throws IOException {
//        String result = ex.getMessage();
//        return JsonResult.errorMsg(StatusCode.UNKNOWN_ERROR,"未知的错误:"+result);
//    }

    /**
     * JWT解析异常：无效、过期
     * @param resp
     * @param ex
     * @return
     * @throws IOException
     */
    @ExceptionHandler(JwtException.class)
    public JsonResult<String> handleJwtException(HttpServletResponse resp, Exception ex) throws IOException {
        return JsonResult.errorMsg(StatusCode.ERROR_TOKEN,"用户信息无效或过期");
    }
    /**
     * 参数为实体类,
     * @param resp
     * @param ex
     * @return
     * @throws IOException
     */
    @ExceptionHandler(BindException.class)
    public JsonResult<String> handleBindException(HttpServletResponse resp, BindException ex) throws IOException {
        StringBuilder result = new StringBuilder();
        // 从异常对象中拿到ObjectError对象
        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        //todo，使用流式运算优化
        for (ObjectError objectError: objectErrors) {
            result.append(objectError.getDefaultMessage()).append(";");
        }
        // 然后提取错误提示信息进行返回
        return JsonResult.errorMsg(StatusCode.INVALID_ARGUMENT,result.toString());
    }

    /**
     * 参数为实体类
     * @param ex
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public JsonResult<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        StringBuilder result = new StringBuilder();
        // 从异常对象中拿到ObjectError对象
        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        //todo，使用流式运算优化
        for (ObjectError objectError: objectErrors) {
            result.append(objectError.getDefaultMessage()).append(";");
        }
        // 然后提取错误提示信息进行返回
        return JsonResult.errorMsg(StatusCode.INVALID_ARGUMENT,result.toString());
    }
    /**
     * 参数为单个参数或多个参数
     * @param resp
     * @param ex
     * @return
     * @throws IOException
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public JsonResult<String> constraintViolationException(HttpServletResponse resp, ConstraintViolationException ex) throws IOException {
        StringBuilder result = new StringBuilder();
        //取出自定义的错误提示，不带自动生成的参数
        List<String> messages = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).toList();
        //todo，使用流式运算优化
        for (String message: messages) {
            result.append(message).append(";");
        }
        return JsonResult.errorMsg(StatusCode.UNKNOWN_ERROR,result.toString());
    }

    /**
     * 邮件异常
     * @param resp
     * @param ex
     * @return
     * @throws IOException
     */
    @ExceptionHandler(MailException.class)
    public JsonResult<String> mailException(HttpServletResponse resp, Exception ex) throws IOException {
        return JsonResult.errorMsg(StatusCode.ERROR_EMAIL,"邮件发送错误");
    }
}
