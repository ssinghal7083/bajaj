package com.example.webhooksolver.repository;

import com.example.webhooksolver.entity.SqlSolution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlSolutionRepository extends JpaRepository<SqlSolution, Long> {
}
