package com.moge10086.website.common.config;

import com.moge10086.website.common.constant.StatusCode;
import com.moge10086.website.common.utils.JsonResult;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
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
    @ExceptionHandler(Exception.class)
    public JsonResult<String> exception(HttpServletResponse resp, Exception ex) throws IOException {
        String result = ex.getMessage();
        return JsonResult.errorMsg(StatusCode.UNKNOWN_ERROR,"未知的错误:"+result);
    }

    /**
     * 参数校验异常
     * @param resp
     * @param ex
     * @return
     * @throws IOException
     */
    @ExceptionHandler(BindException.class)
    public JsonResult<String> handleBindException(HttpServletResponse resp, BindException ex) throws IOException {
        BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        List<String> errorMsg = new ArrayList<>();
        for (FieldError error : fieldErrors) {
            errorMsg.add(error.getDefaultMessage());
        }
        return JsonResult.errorMsg(StatusCode.ERROR_INPUT,String.join(";\n ", errorMsg)+';');
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
        String result = ex.getMessage();
        return JsonResult.errorMsg(StatusCode.ERROR_EMAIL,"邮件发送错误");
    }
}
