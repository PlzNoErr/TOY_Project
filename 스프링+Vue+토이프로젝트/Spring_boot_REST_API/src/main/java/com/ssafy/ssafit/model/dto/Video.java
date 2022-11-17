package com.ssafy.ssafit.model.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Video {
    private long videoId;
    private String videoUrl;
    private String title;
    private String description;
    private int likeCount;
    private long viewCount;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String category;

}
