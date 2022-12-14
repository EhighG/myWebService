package com.myWeb.springboot.service.posts;

import com.myWeb.springboot.domain.posts.Posts;
import com.myWeb.springboot.domain.posts.PostsRepository;
import com.myWeb.springboot.web.dto.PostsListResponseDto;
import com.myWeb.springboot.web.dto.PostsResponseDto;
import com.myWeb.springboot.web.dto.PostsSaveRequestDto;
import com.myWeb.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                // 값이 없으면 function 실행(오류 throw) / 인자로 왜 Exception 아니고 function??
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));
                // Optional이 대상
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList()); // List<PostsListResponseDto>
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(posts); // deleteById(id)로 대체 가능
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        return new PostsResponseDto(entity);
    }
}
