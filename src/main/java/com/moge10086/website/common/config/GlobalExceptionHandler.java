package com.moge10086.website.common.config;

import com.moge10086.website.common.constant.StatusCode;
import com.moge10086.website.common.utils.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
}
