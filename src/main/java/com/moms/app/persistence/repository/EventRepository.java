package com.moms.app.persistence.repository;

import com.moms.app.persistence.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventEntity, Long> {
}
