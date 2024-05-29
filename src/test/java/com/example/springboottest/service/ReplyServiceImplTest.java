package com.example.springboottest.service;

import com.example.springboottest.dto.ReplyDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@SpringBootTest
class ReplyServiceImplTest {
    @Autowired
    private ReplyService replyService;

    @Test
    void testRegister() {
        Long bno = 217L;
        ReplyDTO replyDTO = ReplyDTO.builder()
                .bno(bno)
                .replyText("댓글임...")
                .replyWriter("user1")
                .build();

        Long rno = replyService.register(replyDTO);
        log.info("rno === " + rno);
    }

    @Test
    void testRead() {
        Long rno = 1L;
        ReplyDTO replyDTO = replyService.read(rno);
        log.info(replyDTO);
    }

    @Test
    public void testModify() {
        Long rno = 1L;

        ReplyDTO replyDTO = ReplyDTO.builder()
                .rno(rno)
                .replyText("수정된 댓글임...")
                .build();
        replyService.modify(replyDTO);
    }

    @Test
    public void testRemove() {
        Long rno = 2L;
        replyService.remove(rno);
    }
}