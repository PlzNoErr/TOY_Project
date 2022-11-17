package com.ssafy.ssafit.model.dao;

import com.ssafy.ssafit.model.dto.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewDao {
    void insertReview(Review review);

    void updateReview(Review review);

    void deleteReview(long reviewId);

    List<Review> selectAllByVideoId(long videoId);

    Optional<Review> selectByReviewId(long reviewId);

}
