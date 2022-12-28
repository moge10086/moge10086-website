package com.moge10086.website.common.config;

import com.moge10086.website.common.constant.StatusCode;
import com.moge10086.website.common.utils.JsonResult;
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
    @ExceptionHandler(Exception.class)
    public JsonResult<String> exception(HttpServletResponse resp, Exception ex) throws IOException {
        String result = ex.getMessage();
        return JsonResult.errorMsg(StatusCode.UNKNOWN_ERROR,"未知的错误:"+result);
    }
    @ExceptionHandler(BindException.class)
    public JsonResult handleBindException(HttpServletResponse resp, BindException ex) throws IOException {
        BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        List<String> errorMsg = new ArrayList<>();
        for (FieldError error : fieldErrors) {
            errorMsg.add(error.getDefaultMessage());
        }
        return JsonResult.errorMsg(StatusCode.ERROR_INPUT,String.join(";\n ", errorMsg)+';');
    }
}
