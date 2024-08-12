package com.example.capstone1.repository;

import com.example.capstone1.domain.Tbnotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbnoticeRepository extends JpaRepository<Tbnotice, String> {
}