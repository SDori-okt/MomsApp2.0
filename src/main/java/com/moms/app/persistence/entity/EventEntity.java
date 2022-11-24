package com.moms.app.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "events")
@Builder
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id", nullable = false)
    @EqualsAndHashCode.Exclude
    private Long id;

    @Column(name = "event_name", nullable = false)
    private String eventName;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "event_date_time", nullable = false)
    private LocalDateTime eventDateTime;

    @ManyToMany(mappedBy = "events_taken", fetch = FetchType.LAZY)
    private List<UserEntity> user;
}
