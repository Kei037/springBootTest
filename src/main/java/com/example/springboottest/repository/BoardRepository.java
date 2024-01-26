package com.example.springboottest.repository;

import com.example.springboottest.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);
}
