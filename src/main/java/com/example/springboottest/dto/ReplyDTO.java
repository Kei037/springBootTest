package com.example.springboottest.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO {
    private Long rno;                       // 리플 고유번호

    @NotNull
    private Long bno;                       // 게시판 글 고유번호

    @NotEmpty
    private String replyText;               // 리플 내용

    @NotEmpty
    private String replyWriter;             // 리플 작성자

    private LocalDateTime regDate, modDate; // 리플 작성일, 수정일
}
