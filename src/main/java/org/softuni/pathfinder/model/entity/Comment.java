package org.softuni.pathfinder.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {

    @Column(nullable = false)
    private boolean approved;

    @Column(nullable = false)
    private LocalDateTime created;

    @Column(name = "text_content", nullable = false, columnDefinition = "text")
    private String textContent;

    @ManyToOne
    private User author;

    @ManyToOne
    private Route route;
}
