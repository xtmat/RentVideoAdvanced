package com.project.videorental.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Table(name = "video")
public class Video {
    @GeneratedValue
    @Id
    @Column(name = "video_id")
    private Long id;
    private String title;
    private String director;
    private String genre;
    private boolean isAvailableForRent;
}
