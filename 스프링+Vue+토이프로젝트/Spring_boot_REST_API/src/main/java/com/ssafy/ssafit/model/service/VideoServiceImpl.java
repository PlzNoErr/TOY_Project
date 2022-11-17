package com.ssafy.ssafit.model.service;

import com.ssafy.ssafit.model.dao.VideoDao;
import com.ssafy.ssafit.model.dto.Like;
import com.ssafy.ssafit.model.dto.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class VideoServiceImpl implements VideoService {

    private VideoDao videoDao;

    @Autowired
    public VideoServiceImpl(VideoDao videoDao) {
        this.videoDao = videoDao;
    }

    @Override
    @Transactional
    public void createVideo(Video video) {
        videoDao.insertVideo(video);
    }

    @Override
    @Transactional
    public void updateVideo(Video video) {
        videoDao.updateVideo(video);
    }

    @Override
    @Transactional
    public void deleteVideo(long videoId) {
        videoDao.deleteVideo(videoId);
    }

    @Override
    @Transactional
    public Optional<Video> getVideoDetail(long videoId) {
        videoDao.updateViewCountByVideoId(videoId);
        return videoDao.selectByVideoId(videoId);
    }

    @Override
    public List<Video> searchVideos(String mod, String keyword) {
        HashMap<String, String> condition = new HashMap<>();
        condition.put("mod", mod);
        condition.put("keyword", keyword);
        return videoDao.selectAllByCondition(condition);
    }

    @Override
    public List<Video> likedVideos(long userSeq) {
        return videoDao.selectAllByLike(userSeq);
    }

    @Override
    @Transactional
    public void likeOrDislike(long videoId, long userSeq) {
        Optional<Like> like = videoDao.selectLike(videoId, userSeq);
        if (like.isPresent()) {
            videoDao.updateLikeCountByVideoIdAndInt(videoId, -1L);
            videoDao.deleteLike(videoId, userSeq);
        } else {
            videoDao.updateLikeCountByVideoIdAndInt(videoId, 1L);
            videoDao.insertLike(videoId, userSeq);
        }
    }

    @Override
    public List<Video> followingLikeVideos(long userSeq) {
        return videoDao.selectAllByFollowingLike(userSeq);
    }
}
