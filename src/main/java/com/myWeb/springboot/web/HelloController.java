package com.myWeb.springboot.web;

import com.myWeb.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // @Controller + @ResponseBody (json 객체 반환)
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello"; // 반환값(View name)은 View resolver가 처리
    }

    @GetMapping("hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }
}
