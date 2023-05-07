package org.example.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * @Auther: hzy
 * @Date: 2022/4/28
 * @Description: 全局异常处理
 * 处理整个web controller的异常
 */
@Slf4j
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Object handler(Exception e) {
        log.error("异常是:{}", e.getMessage());
        return new HashMap<String, Object>() {{
            put("status", "error");
        }};
    }
}
