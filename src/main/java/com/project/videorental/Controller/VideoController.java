package com.project.videorental.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.project.videorental.Exchange.VideoDTO.VideoDTO;
import com.project.videorental.Model.Video;
import com.project.videorental.Service.VideoService;

@RestController
@RequestMapping("/videos")
public class VideoController {
    
    @Autowired
    private VideoService videoService;


    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createVideo(@RequestBody VideoDTO request){
        Video video = videoService.createVideo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(video);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateVideo(@PathVariable String id, @RequestBody VideoDTO request){
        Video video = videoService.updateVideo(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(video);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteVideo(@RequestParam String id){
        videoService.deleteVideo(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/list")
    public ResponseEntity<?> listVideos(){
        return ResponseEntity.ok(videoService.listVideos());
    }


}
