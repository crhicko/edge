package com.dom.edge.repository;

import com.dom.edge.model.SportEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportRepository extends JpaRepository<SportEvent, Long> {
}
