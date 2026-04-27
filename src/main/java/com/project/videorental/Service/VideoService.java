package com.project.videorental.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.project.videorental.Exchange.VideoDTO.VideoDTO;
import com.project.videorental.Model.User;
import com.project.videorental.Model.Video;
import com.project.videorental.Repository.UserRepository;
import com.project.videorental.Repository.VideoRepository;

@Service
public class VideoService {
    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private UserRepository userRepository;

    public Video createVideo(VideoDTO request){
        Video video = Video.builder()
        .title(request.getTitle())
        .director(request.getDirector())
        .genre(request.getGenre())
        .isAvailableForRent(true).build();
        videoRepository.save(video);
        return video;
    }

    public Video updateVideo(Long id, VideoDTO request){

        Video video = videoRepository.findById(id).orElseThrow(()->new RuntimeException("Video with id : "+id + " doesn't exist"));
        video.setAvailableForRent(request.isAvailableForRent());
        video.setTitle(request.getTitle());
        video.setDirector(request.getDirector());
        video.setGenre(request.getGenre());
        videoRepository.save(video);
        return video;
    }

    public void deleteVideo(Long id){
        Video video = videoRepository.findById(id).orElseThrow(()->new RuntimeException("Video with id : "+id + " doesn't exist"));
        videoRepository.delete(video);
    }

    
    public List<Video> listVideos(){
        return videoRepository.findAll().stream().collect(Collectors.toList());
    }

    public String rentVideo(Long videoId){
        Video video = videoRepository.findById(videoId).get();
        if(!video.isAvailableForRent()){
            throw new RuntimeException("The video with id : "+ videoId+" is not available for rent");
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String useranme = auth.getName();
        User user = userRepository.findUserByEmail(useranme);
        List<Video> renteVideos= user.getRentedVideos();
        if(renteVideos.size()>=2){
            throw new RuntimeException("The user with user id :"+user.getId()+"has already rented 2 videos, can't rent more");
        }
        renteVideos.add(video);
        user.setRentedVideos(renteVideos);
        userRepository.save(user);
        video.setAvailableForRent(false);
        videoRepository.save(video);
        return "Video with id : "+videoId+" has been rented to user with id : "+user.getId();
    }

    public String returnVideo(Long videoId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findUserByEmail(username);
        List<Video> userRentedAllVideos = user.getRentedVideos();
        List<Video> currentVideo = userRentedAllVideos.stream().filter(v->v.getId()==videoId).collect(Collectors.toList());
        if(currentVideo.size()==1){
            Video video = videoRepository.findById(videoId).orElseThrow(()-> new RuntimeException("The video with id : "+videoId+" doens't exist"));
            video.setAvailableForRent(true);
            videoRepository.save(video);
            userRentedAllVideos.remove(currentVideo.getFirst());
            user.setRentedVideos(userRentedAllVideos);
            userRepository.save(user);
            return "Video with Id "+videoId+" has been return succesfully";
        } else {
            throw new RuntimeException("The following video is not rented by you: can't return ");
        }

    }

}
