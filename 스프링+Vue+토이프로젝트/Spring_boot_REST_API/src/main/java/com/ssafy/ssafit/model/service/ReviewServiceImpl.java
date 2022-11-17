package com.ssafy.ssafit.model.service;

import com.ssafy.ssafit.model.dao.ReviewDao;
import com.ssafy.ssafit.model.dto.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewDao reviewDao;

    @Autowired
    public ReviewServiceImpl(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    @Override
    public void createReview(Review review) {
        reviewDao.insertReview(review);
    }

    @Override
    public void updateReview(Review review) {
        reviewDao.updateReview(review);
    }

    @Override
    public void deleteReview(long reviewId) {
        reviewDao.deleteReview(reviewId);
    }

    @Override
    public List<Review> getVideoReviews(long videoId) {
        return reviewDao.selectAllByVideoId(videoId);
    }

    @Override
    public Optional<Review> getDetailReview(long reviewId) {
        return reviewDao.selectByReviewId(reviewId);
    }
}
