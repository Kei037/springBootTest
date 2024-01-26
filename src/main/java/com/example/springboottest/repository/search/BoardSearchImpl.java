package com.example.springboottest.repository.search;

import com.example.springboottest.domain.Board;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

@Log4j2
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    public BoardSearchImpl() { super(Board.class); }

    @Override
    public Page<BoardSearch> search1(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Board> searchALL(String[] types, String keyword, Pageable pageable) {
        return null;
    }
}
