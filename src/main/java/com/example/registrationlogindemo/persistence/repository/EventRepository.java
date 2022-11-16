package com.example.registrationlogindemo.persistence.repository;

import com.example.registrationlogindemo.persistence.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventEntity, Long> {
}
