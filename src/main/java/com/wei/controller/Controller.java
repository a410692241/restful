package com.wei.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.wei.bo.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/index")
public class Controller {


    @JsonView(User.UserUnId.class)
    @GetMapping(value = "/getUserUnId/{id}")
    public User getUserUnId(@PathVariable Integer id) {
        return new User();
    }


    /**
     * @param id
     * @return
     */
    @JsonView(User.UserAddSex.class)
    @GetMapping(value = "/getUserAddSex/{id}")
    public User getUserAddSex(@PathVariable Integer id) {
        return new User();
    }


    @PutMapping(value = "/updateUser")
    public Object updateUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(item -> System.out.println(item.getDefaultMessage()));
        }
        System.out.println(user);
        return "success";
    }

    @DeleteMapping(value = "/deleteUser/{id}")
    public Object deleteUser(@PathVariable Integer id) {
        System.out.println(id);
        return "success";
    }

    @PostMapping(value = "/queryUser/{id}")
    public User queryUser(@PathVariable Integer id) {
        System.out.println(id);
        return new User();
    }


    /**
     * @param username 检验参数
     * @return
     */
    @GetMapping("/getUserListByUsername/{username}")
    public List<User> getUserListByUsername(@PathVariable String username) {
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setUsername(username);
        users.add(user);
        users.add(user);
        users.add(user);
        return users;
    }


    /**设置默认值
     * @param username
     * @return
     */
    @GetMapping("/getByParam")
    public List<User> getByParam(@RequestParam(required = false, defaultValue = "我是老王啊") String username) {
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setUsername(username);
        users.add(user);
        users.add(user);
        users.add(user);
        return users;
    }


    /**对username禁止传入空进行判断,实体类加上 @NotBlank ,在控制器的@responseBody前面加上@Valid
     * @param user
     * @param bindingResult 对错误进行处理的对象,可以允许接口继续访问,但是错误可以收集打印结果
     *
     * @return
     */
    @PostMapping(value = "/paramValid")
    public List<User> paramValid(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(item -> System.out.println(item.getDefaultMessage()));
        }
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user);
        users.add(user);
        return users;
    }

}
