package com.wei.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.wei.validator.Myconstraint;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

public class User {
    public interface UserUnId{}

    public interface UserAddSex extends UserUnId{}
    private Integer id;
//    @Myconstraint(message = "无论如何都报错")
    @NotBlank(message = "用户名不能为空")
    private String username;
    private String password;
    private String sex;
    /*表示是过去的时间*/
    @Past(message = "生日必须是过去的时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthDay;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonView(UserUnId.class)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonView(UserUnId.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonView(UserAddSex.class)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
}
