package com.myWeb.springboot.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller // 이부분이 없으면 index페이지는 잘 되지만 등록화면만 안됨. 왜?? (직접 들어가도 안됨)
public class IndexController {

    // index.html은 기본값인가?? 이 메서드 없어도 호출됨!
    @GetMapping("/")
    public String index() {
        return "index"; // index.mustache 호출(웹 mvc 강의때처럼, ViewResolver가  처리)
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save"; // --> posts-save.mustache
    }
}
