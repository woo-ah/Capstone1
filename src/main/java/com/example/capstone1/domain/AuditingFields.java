package com.example.capstone1.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

//모든 엔티티들의 공통 필드
@Getter
@ToString
@EntityListeners(AuditingFields.class) //엔티티의 변화를 감지하고 데이블의 데이터를 조작
@MappedSuperclass //다른 엔티티들이 상속받을 수 있게 해줌
public abstract class AuditingFields {

    @Id
    private String id;

    @Column(nullable = false)
    @Setter
    protected String deleted;

    @Column(nullable = true)
    @Setter
    protected String process;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @CreatedDate //엔티티가 처음 생성될 때 자동으로 현재 시간으로 설정
    @Column(nullable = false, updatable = false)
    protected LocalDateTime createdAt;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @LastModifiedDate //엔티티가 수정될 때 자동으로 현재 시간으로 업데이트
    @Column(nullable = false)
    protected LocalDateTime modifiedAt;

    //엔티티가 처음 데이터베이스에 저장되기 전에 자동으로 호출
    @PrePersist
    public void onPrePersist() {
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.deleted = "N";
    }


}
