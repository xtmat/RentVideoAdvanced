package com.project.videorental.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.videorental.Exchange.VideoDTO.VideoDTO;
import com.project.videorental.Model.Video;
import com.project.videorental.Repository.VideoRepository;

@Service
public class VideoService {
    @Autowired
    private VideoRepository videoRepository;

    public Video createVideo(VideoDTO request){
        Video video = Video.builder()
        .title(request.getTitle())
        .director(request.getDirector())
        .genre(request.getGenre())
        .isAvailableForRent(true).build();
        videoRepository.save(video);
        return video;
    }

    public Video updateVideo(String id, VideoDTO request){

        Video video = videoRepository.findById(id).orElseThrow(()->new RuntimeException("Video with id : "+id + " doesn't exist"));
        video.setAvailableForRent(request.isAvailableForRent());
        video.setTitle(request.getTitle());
        video.setDirector(request.getDirector());
        video.setGenre(request.getGenre());
        videoRepository.save(video);
        return video;
    }

    public void deleteVideo(String id){
        Video video = videoRepository.findById(id).orElseThrow(()->new RuntimeException("Video with id : "+id + " doesn't exist"));
        videoRepository.delete(video);
    }

    
    public List<Video> listVideos(){
        return videoRepository.findAll().stream().collect(Collectors.toList());
    }

}
