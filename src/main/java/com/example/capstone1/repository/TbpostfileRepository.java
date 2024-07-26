package com.example.capstone1.repository;
import com.example.capstone1.domain.Tbpostfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbpostfileRepository extends JpaRepository<Tbpostfile, String> {
}
