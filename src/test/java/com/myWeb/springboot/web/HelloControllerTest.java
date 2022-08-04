package com.myWeb.springboot.web;

import com.myWeb.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) // 테스트 진행시 JUnit말고 외부 runner 실행
@WebMvcTest(HelloController.class)
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void hello반환() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) // MockMvc(임시 Mvc)인 mvc에 "/hello" 라고 get방식 요청을 보내고 받은 응답이
                .andExpect(status().isOk()) // 정상적인 상태의 응답인지 확인(40X, 50X 말고 20X) 하고,
                .andExpect(content().string(hello)); // 그 응답의 content가 "hello" 인지 확인
    }

    @Test
    public void helloDto반환() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) // jsonPath() : 반환된(응답으로 온) json값을 필드별로 검증
                .andExpect(jsonPath("$.amount", is(amount)));

    }

}