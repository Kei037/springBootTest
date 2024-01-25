package com.example.springboottest.repository;

import com.example.springboottest.domain.Board;
import org.springframework.data.jpa.repository.*;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
