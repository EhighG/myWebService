package com.myWeb.springboot.web;

import com.myWeb.springboot.service.posts.PostsService;
import com.myWeb.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller // 이부분이 없으면 index페이지는 잘 되지만 등록화면만 안됨. 왜?? (직접 들어가도 안됨)
public class IndexController {

    private final PostsService postsService;

    // index.html은 기본값인가?? 이 메서드 없어도 호출됨!
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc()); // findAllDesc() 결과를 index.mustache에 posts라는 이름으로 전달
        return "index"; // index.mustache 호출(웹 mvc 강의때처럼, ViewResolver가  처리)
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save"; // --> posts-save.mustache
    }

    @GetMapping("posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
