package com.example.springboottest.repository.search;

import com.example.springboottest.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {
    Page<BoardSearch> search1(Pageable pageable);
    Page<Board> searchALL(String[] types, String keyword, Pageable pageable);
}
