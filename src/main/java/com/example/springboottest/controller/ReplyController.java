package com.example.springboottest.controller;

import com.example.springboottest.dto.ReplyDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/api/replies")
public class ReplyController {
    @Operation(summary = "Replies Post", description = "POST 방식으로 댓글 등록") // Operation으로 설명 추가
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE) // consumes로 JSON 형식만 받음
    public Map<String, Long> register(@RequestBody ReplyDTO replyDTO) { // RequestBody로 replyDTO를 받음
        log.info("ReplyDTO: {}", replyDTO);
        Map<String, Long> map = new HashMap<>();
        map.put("rno", 111L);

        return map;
    }
}
