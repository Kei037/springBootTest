package com.example.springboottest.repository;

import com.example.springboottest.domain.Board;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testInsert() {
        for (int i = 1; i <= 100; i++) {
            Board board = Board.builder()
                    .title("title...")
                    .content("content..." + i)
                    .writer("uesr" + (i % 10))
                    .build();
            Board result = boardRepository.save(board);
            log.info("BNO: " + result.getBno());
        }
    }

    @Test
    public void testSelect() {
        Long bno = 100L;
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();
        log.info(board);
    }

    @Test
    public void testUpdate() {
        Long bno = 100L;
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();

        board.change("update... title 100", "update content 100");
        boardRepository.save(board);
    }
    // JDBC, MyBatis 방식으로 처리하면 에러
    @Test
    public void testUpdate2() {
        Long bno = 100L;
        Board board = Board.builder().bno(bno)
                .title("title...")
                .content("content...update2")
                .build();
        boardRepository.save(board);
    }
    // 없는 ID값을 지정하면 update가 아니라 insert가 실행
    @Test
    public void testUpdate3() {
        // 없는 bno를 지정한 경우
        Long bno = 1000L;
        Board board = Board.builder().bno(bno)
                .title("title...")
                .content("content...update3")
                .writer("user..update")
                .build();
        boardRepository.save(board);
    }
    @Test
    public void testDelete() {
        Long bno = 1L;

        boardRepository.deleteById(bno);
    }
    @Test
    public void testPaging() {
        // 1 page order by bno desc
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<Board> result = boardRepository.findAll(pageable);

        log.info("total count: " + result.getTotalElements());
        log.info("total page: " + result.getTotalPages());
        log.info("total number: " + result.getNumber());
        log.info("total size: " + result.getSize());
        // prev next
        log.info(result.hasPrevious() + ": " + result.hasNext());

        List<Board> boardList = result.getContent();

        boardList.forEach(board -> log.info(board));

    }
}
