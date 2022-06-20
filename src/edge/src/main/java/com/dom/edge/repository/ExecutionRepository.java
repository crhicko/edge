package com.dom.edge.repository;

import com.dom.edge.model.ExecutionEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExecutionRepository extends JpaRepository<ExecutionEvent, Long> {
}
