package com.myWeb.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    // JpaRepository<Entity, PK> 상속하면 기본 CRUD 메소드 자동 생성.

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC") // (findAllDesc()가) 해당 쿼리의 결과값을 반환하게 함
    List<Posts> findAllDesc();

}
