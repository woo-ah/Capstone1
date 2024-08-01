package com.example.capstone1.domain;

import com.example.capstone1.dto.TbpostfileDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
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
public class Tbpostfile extends AuditingFields{
    @Setter @Column(nullable = false) private String tbpostId;
    @Setter @Column(nullable = false) private String type;
    @Setter @Column(nullable = false, length=400) private String url;

    protected Tbpostfile(){}
    private Tbpostfile(String tbpostId, String type, String url) {
        this.tbpostId = tbpostId;
        this.type = type;
        this.url = url;
    }
    public static Tbpostfile of(String tbpostId, String type, String url) {
        return new Tbpostfile(tbpostId, type, url);
    }

    public TbpostfileDto.CreateResDto toCreateResDto() {
        return TbpostfileDto.CreateResDto.builder().id(this.getId()).build();
    }
}
