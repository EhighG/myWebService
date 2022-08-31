package com.myWeb.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 이 클래스를 상속한 JPA Entity 클래스가 이 클래스의 필드도 Column으로 인식하게 함.w
@EntityListeners(AuditingEntityListener.class) // @EntityListeners : entity에 이벤트가 발생하는지 체크
// Auditing기능 추가 : entity가 생성(영속성 컨텍스트에 추가..?), 업데이트될 때 시간 정보 추가해줌.
public class BaseTimeEntity {

    @CreatedDate // 생성될 때 시간정보 추가
    private LocalDateTime createdDate;

    @LastModifiedDate // 업데이트 시 시간정보 추가
    private LocalDateTime modifiedDate;
}
