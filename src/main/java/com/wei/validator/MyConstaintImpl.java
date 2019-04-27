package com.wei.validator;

import com.wei.service.UsernameServicce;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义的验证,根据Myconstraint注解进行验证
 */
public class MyConstaintImpl implements ConstraintValidator<Myconstraint,Object> {
    @Autowired
    private UsernameServicce usernameServicce;

    @Override
    public void initialize(Myconstraint constraintAnnotation) {
        System.out.println("my constrain-validator initing!");
    }

    /**
     * @param o 具体的验证逻辑,不同的是实现ConstraintValidator接口,那么可以直接使用spring中的bean
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        //这里意思一下,就当调用了某个业务
        usernameServicce.greeting("hello");
        System.out.println(o);
        return false;
    }

}
