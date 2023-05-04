package com.jojoldu.book.springboot.domain.posts;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
// ***Entity 클래스에서는 절대 Setter 메소드 만들지 않음. 값 변경 필요 시 명확히 목적,의도 나타내는 메소드 추가 필요
@NoArgsConstructor // 기본 생성자 자동 추가
@Entity // 실제 DB 테이블과 매칭될 클래스. DB 작업 시 entity 클래스 수정으로 작업
public class Posts extends BaseTimeEntity {

    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pk 생성 규칙. IDENTITY=auto_increment
    private Long id;

    @Column(length = 500, nullable = false) // 컬럼. 굳이 선언하지 않아도 되지만 옵션 변경 시 선언.
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false) // 컬럼타입=TEXT
    private String content;
    
    private String author;

    @Builder // 생성자 선언 시 생성자에 포함된 필드만 포함
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
