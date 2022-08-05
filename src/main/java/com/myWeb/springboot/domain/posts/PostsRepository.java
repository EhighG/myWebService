package com.myWeb.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    // JpaRepository<Entity, PK> 상속하면 기본 CRUD 메소드 자동 생성.


}
