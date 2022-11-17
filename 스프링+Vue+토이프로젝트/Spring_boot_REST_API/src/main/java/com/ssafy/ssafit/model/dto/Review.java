package com.ssafy.ssafit.model.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Review {
    private long reviewId;
    private long userSeq;
    private long videoId;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String title;
    private String content;
    private String userId;
    private String nickname;
}
