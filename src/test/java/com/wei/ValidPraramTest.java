package com.wei;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 以下是对hibernate-validator对对象json的验证
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest

public class ValidPraramTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;



    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }



    /**
     * 验证请求参数username不能为null
     * @throws Exception
     */
    @Test
    public void paramValid() throws Exception {
        Date date = new Date();
        System.out.println(date.getTime());
        String context = "{\"username\":null,\"password\":\"a123456\",\"sex\":\"男\",\"id\":1,\"birthDay\": " + date.getTime()+"}";
        String result = mockMvc.perform(post("/index/paramValid").content(context).contentType(APPLICATION_JSON_UTF8)).
                andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }


    /**paster验证,故意提供一个大于当前的时间,观察must be a past date
     * @throws Exception
     */
    @Test
    public void updateUser() throws Exception {
        String context = "{\"username\":\"huwei\",\"password\":\"a123456\",\"sex\":\"男\",\"id\":1,\"birthDay\": " + new Date(LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()).getTime() + "}";
        System.out.println(context);
        String result = mockMvc.perform(put("/index/updateUser").content(context).contentType(APPLICATION_JSON_UTF8)).
                andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }


    /**
     *使用自定义的Myconstrain注解进行验证,使用方式,重写constrainValidator,接口上使用@valid
     * @throws Exception
     */
    @Test
    public void myConstrainValid() throws Exception {
        Date date = new Date();
        System.out.println(date.getTime());
        String context = "{\"username\":\"huwei\",\"password\":\"a123456\",\"sex\":\"男\",\"id\":1,\"birthDay\": " + date.getTime()+"}";
        String result = mockMvc.perform(post("/index/paramValid").content(context).contentType(APPLICATION_JSON_UTF8)).
                andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

}
