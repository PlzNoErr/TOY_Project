package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.model.dto.Review;
import com.ssafy.ssafit.model.dto.Video;
import com.ssafy.ssafit.model.service.ReviewService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/{reviewId}")
    @ApiOperation(value = "{reviewId}에 일치하는 리뷰를 받아온다.")
    public ResponseEntity<?> getDetailReview(@PathVariable long reviewId) {
        try {
            Optional<Review> review = reviewService.getDetailReview(reviewId);
            if (review.isPresent()) {
                return new ResponseEntity<>(review.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/video/{videoId}")
    @ApiOperation(value = "{videoId}로 작성된 모든 리뷰들의 리스트를 받아온다.")
    public ResponseEntity<?> getReviewList(@PathVariable long videoId) {
        try {
            List<Review> reviewList = reviewService.getVideoReviews(videoId);
            if (reviewList != null && reviewList.size() > 0) {
                return new ResponseEntity<>(reviewList, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    @ApiOperation(value = "리뷰를 새로 생성한다.")
    public ResponseEntity<Void> createReview(@RequestBody Review review) {
        try {
            reviewService.createReview(review);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/")
    @ApiOperation(value = "리뷰를 수정한다.")
    public ResponseEntity<Void> updateReview(@RequestBody Review review) {
        try {
            reviewService.updateReview(review);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{reviewId}")
    @ApiOperation(value = "{reviewId}를 가진 리뷰를 삭제한다.")
    public ResponseEntity<Void> deleteReview(@PathVariable long reviewId) {
        try {
            reviewService.deleteReview(reviewId);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

