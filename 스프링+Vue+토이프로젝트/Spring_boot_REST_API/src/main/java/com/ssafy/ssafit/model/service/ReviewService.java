package com.ssafy.ssafit.model.service;

import com.ssafy.ssafit.model.dto.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    void createReview(Review review);

    void updateReview(Review review);

    void deleteReview(long reviewId);

    List<Review> getVideoReviews(long videoId);

    Optional<Review> getDetailReview(long reviewId);
}
