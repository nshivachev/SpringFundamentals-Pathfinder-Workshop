package org.softuni.pathfinder.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.softuni.pathfinder.model.enums.Level;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "routes")
public class Route extends BaseEntity {

    public Route() {
        this.categories = new HashSet<>();
    }

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

    public void addCategories(Set<Category> categories) {
        this.categories.addAll(categories);
    }
}
