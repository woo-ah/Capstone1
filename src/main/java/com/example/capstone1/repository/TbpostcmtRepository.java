package com.example.capstone1.repository;

import com.example.capstone1.domain.Tbpostcmt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbpostcmtRepository extends JpaRepository<Tbpostcmt, String> {
}
