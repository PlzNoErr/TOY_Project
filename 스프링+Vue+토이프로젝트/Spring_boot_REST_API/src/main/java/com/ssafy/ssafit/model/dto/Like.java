package com.ssafy.ssafit.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Like {
    private long likeId;
    private long userSeq;
    private long videoId;
}
