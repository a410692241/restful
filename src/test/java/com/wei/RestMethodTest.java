package com.wei;

import com.wei.controller.Controller;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.http.MediaType.parseMediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestMethodTest {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;


    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void MockMvcTest() throws Exception {
        mockMvc.perform(get("/index/getUserUnId/1")
                .accept(parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk()).andDo(print());

    }

    @Test
    public void getUserListByUsername() throws Exception {
        mockMvc.perform(get("/index/getUserListByUsername/1")
                .accept(parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andDo(print());

    }

    @Test
    public void getByParam() throws Exception {
        mockMvc.perform(get("/index/getByParam")
//                .param("username","hu")
                .accept(parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andDo(print());

    }

    /**
     * 验证执行的控制器类和控制器方法名
     */
    @Test
    public void name() throws Exception {
        mockMvc.perform(get("/index/getByParam")
                .accept(parseMediaType("application/json;charset=UTF-8")))
                .andExpect(handler().handlerType(Controller.class))
                .andExpect(handler().methodName("getByParam")).andDo(print());
    }

}
