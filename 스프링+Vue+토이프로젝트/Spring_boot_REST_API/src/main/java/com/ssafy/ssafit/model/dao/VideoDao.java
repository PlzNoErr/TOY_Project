package com.ssafy.ssafit.model.dao;

import com.ssafy.ssafit.model.dto.Like;
import com.ssafy.ssafit.model.dto.Video;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface VideoDao {
    void insertVideo(Video video);

    void updateVideo(Video video);

    void updateLikeCountByVideoIdAndInt(@Param("videoId") long videoId, @Param("i") long i);

    void updateViewCountByVideoId(long videoId);

    void deleteVideo(long videoId);

    Optional<Video> selectByVideoId(long videoId);

    List<Video> selectAllByCondition(HashMap<String, String> condition);

    List<Video> selectAllByLike(long userSeq);

    void insertLike(@Param("videoId") long videoId, @Param("userSeq") long userSeq);

    void deleteLike(@Param("videoId") long videoId, @Param("userSeq") long userSeq);

    Optional<Like> selectLike(@Param("videoId") long videoId, @Param("userSeq") long userSeq);

    List<Video> selectAllByFollowingLike(long userSeq);
}
