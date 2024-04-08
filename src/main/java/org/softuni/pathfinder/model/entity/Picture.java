package org.softuni.pathfinder.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "pictures")
public class Picture extends BaseEntity {

    @Column
    private String title;

    @Column(columnDefinition = "text")
    private String url;

    @ManyToOne
    private User author;

    @ManyToOne
    private Route route;
}
