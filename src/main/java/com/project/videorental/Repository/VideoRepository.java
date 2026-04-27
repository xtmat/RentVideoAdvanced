package com.project.videorental.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.videorental.Model.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {
    public Video getVideoById(String id);
}
