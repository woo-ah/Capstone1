package com.example.capstone1.domain;

import com.example.capstone1.dto.TbpostDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Table(indexes = {
        @Index(columnList = "deleted")
        ,@Index(columnList = "process")
        ,@Index(columnList = "createdAt")
        ,@Index(columnList = "modifiedAt")
})
@Entity
public class Tbpost extends AuditingFields{

    @Setter @Column(nullable = false) private String TbuserId; //fk이지만, 따로 설정은 안함
    @Setter @Column(nullable = false, length=400) private String title;
    @Setter @Column(nullable = true, length=1000) @Lob private String content;
    //Lob은 대용량 저장

    protected Tbpost(){}

    public Tbpost(String tbuserId, String title, String content) {
        TbuserId = tbuserId;
        this.title = title;
        this.content = content;
    }

    public static Tbpost of(String tbuserId, String title, String content){
        return new Tbpost(tbuserId, title, content);
    }

    //엔티티를 dto로 변환하는 함수
    public TbpostDto.CreateResDto toCreateResDto(){
       return TbpostDto.CreateResDto.builder().id(this.getId()).build();
    }
}
