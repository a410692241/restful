package com.wei.exception;

import com.sun.org.apache.xerces.internal.impl.dv.SchemaDVFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {


    /**
     * @param e
     * @return 自定义返回的json字符,以下返回特定的500状态码,json包含id和message
     */
    @ExceptionHandler(UserNotExistException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,Object> handlerUserNotExistException(UserNotExistException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", e.getId());
        map.put("message", e.getMessage());
        return map;
    }
}
