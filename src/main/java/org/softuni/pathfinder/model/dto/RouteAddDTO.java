package org.softuni.pathfinder.model.dto;

import jakarta.validation.constraints.Size;
import org.softuni.pathfinder.model.enums.CategoryNames;
import org.softuni.pathfinder.model.enums.Level;

import java.util.Set;

public class RouteAddDTO {
        @Size(min = 3, message = "Name length must be more than 3 characters")
        private String name;

        @Size(min = 5, message = "Description length must be more than 5 characters")
        private String description;

//        private String gpxCoordinates;

        private Level level;

        private String videoUrl;

        private Set<CategoryNames> categories;

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

//        public String getGpxCoordinates() {
//                return gpxCoordinates;
//        }

//        public void setGpxCoordinates(String gpxCoordinates) {
//                this.gpxCoordinates = gpxCoordinates;
//        }

        public Level getLevel() {
                return level;
        }

        public void setLevel(Level level) {
                this.level = level;
        }

        public String getVideoUrl() {
                return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
                this.videoUrl = videoUrl;
        }

        public Set<CategoryNames> getCategories() {
                return categories;
        }

        public void setCategories(Set<CategoryNames> categories) {
                this.categories = categories;
        }
}
