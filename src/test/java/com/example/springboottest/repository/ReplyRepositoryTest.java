package com.example.springboottest.repository;

import com.example.springboottest.domain.Board;
import com.example.springboottest.domain.Reply;
import com.example.springboottest.dto.BoardListReplyCountDTO;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

@Log4j2
@SpringBootTest
class ReplyRepositoryTest {
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private BoardRepository boardRepository;

    @Test
    void testInsert() {
        Long bno = 217L;
        Optional<Board> board = boardRepository.findById(bno);

        Reply reply = Reply.builder()
                .board(board.orElseThrow())
                .replyText("댓글임...")
                .replyWriter("user1")
                .build();
        Reply result = replyRepository.save(reply);
        log.info(result);
    }

    @Test
    public void testSelect() {
        Long rno = 1L;
        Optional<Reply> result = replyRepository.findById(rno);
        Reply reply = result.orElseThrow();
        log.info(reply);
    }

    @Test
    @Transactional
    public void listOfBoardTest() {
        Long bno = 217L;
        Pageable pageable = PageRequest.of(0, 10, Sort.by("rno").descending());
        Page<Reply> replies = replyRepository.listOfBoard(bno, pageable);
        replies.getContent().forEach(reply -> {
            log.info("reply == " + reply.getRno());
            log.info("board == " + reply.getBoard().toString()); // Board 정보 출력
        });
    }

    @Test
    public void searchReplyCountTest() {
        String[] types = {"t", "c", "w"};
        String keyword = "1";

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<BoardListReplyCountDTO> result = boardRepository.searchWithReplyCount(types, keyword, pageable);

        // total pages
        log.info(result.getTotalPages());
        // page size
        log.info(result.getSize());
        // pageNumber
        log.info(result.getNumber());
        // prev next

        log.info(result.hasPrevious() + ": " + result.hasNext());
        result.getContent().forEach(board -> log.info(board));
    }
}