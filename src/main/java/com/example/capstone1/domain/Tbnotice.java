package com.example.capstone1.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Tbnotice{
    @Id
    @Setter
    @Column(nullable = false) private String id; //PK!!!!

    @Setter @Column(nullable = false, length=400) private String title;

    @Setter @Column(nullable = true, length=10000) @Lob private String content; // 본문
}