package com.ssafy.ssafit.model.service;

import com.ssafy.ssafit.model.dto.Video;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface VideoService {
    void createVideo(Video video);

    void updateVideo(Video video);

    void deleteVideo(long videoId);

    Optional<Video> getVideoDetail(long videoId);

    List<Video> searchVideos(String mod, String keyword);

    List<Video> likedVideos(long userSeq);

    void likeOrDislike(long videoId, long userSeq);

    List<Video> followingLikeVideos(long userSeq);
}
