package org.softuni.pathfinder.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.softuni.pathfinder.model.enums.Level;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "routes")
public class Route extends BaseEntity {

    @Column(columnDefinition = "text")
    private String description;

    @Column(name = "gpx_coordinates", columnDefinition = "longtext")
    private String gpxCoordinates;

    @Enumerated(EnumType.STRING)
    private Level level;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "video_url")
    private String videoUrl;

    @ManyToOne
    private User author;

    @ManyToMany
    private Set<Category> categories;
}
