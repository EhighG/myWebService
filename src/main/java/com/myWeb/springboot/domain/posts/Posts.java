package com.myWeb.springboot.domain.posts;

import com.myWeb.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity // DB의 table
public class Posts extends BaseTimeEntity {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // for auto increment (자동 sequence 생성)
    private Long id;

    @Column(length = 500, nullable = false) // 기본값 변경 시 사용.(length: 255 --> 500)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // Posts.builder().title(arg).content(arg).author(arg).build() 식의 인스턴스 생성을 가능하게 함.
    // 클래스에 사용 시 그 클래스의 모든 필드를, 생성자에 사용 시 해당 생성자에 포함된 필드만.
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title; // this --> update 대상 게시글
        this.content = content;
    }
}
