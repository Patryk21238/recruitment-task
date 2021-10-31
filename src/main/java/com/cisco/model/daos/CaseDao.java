package com.cisco.model.daos;

import com.cisco.model.models.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseDao extends JpaRepository<Case, Long> {
}