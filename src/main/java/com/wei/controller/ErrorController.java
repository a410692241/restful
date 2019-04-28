package com.wei.controller;

import com.wei.bo.User;
import com.wei.exception.UserNotExistException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("error")
@RestController
public class ErrorController {

    /**
     * @param user spring默认异常返回的异常
     * @return
     */
    @PostMapping("/create")
    public Object create(@RequestBody User user) {
        throw new RuntimeException("发生了一个创建异常!");
    }

    //自定义状态码异常(404,500),就需要在src/main/resources下建立resources/error文件夹
    //再建立404.html和500.html

    /**使用方式是需要先建立一个异常handler类(ControllerExceptionHandler),
     * 建立一个拦截方法,指定返回的json类型
     * {
     *   "id": 1,
     *   "message": "用户不存在!"
     * }
     * @param id
     * @return
     */
    @PostMapping("/userNotExist/{id}")
    public Object create(@PathVariable Integer id) {
        throw new UserNotExistException(id);
    }
}
