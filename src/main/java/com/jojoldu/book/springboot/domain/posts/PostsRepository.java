package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Post 클래스로 DB 접근하게 해줌. DAO같은 DB Layer 접근자 역할
// JpaRepository<Entity 클래스, PK 타입> 상속 시 기본 CRUD 메소드 자동 생성
// Entity 클래스와 기본 Entiry Repository는 함께 위치해야 함 - 도메인 패키지에서 함께 관리

public interface PostsRepository extends JpaRepository<Posts, Long> {
}
