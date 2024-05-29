package com.example.springboottest.service;

import com.example.springboottest.dto.BoardDTO;
import com.example.springboottest.dto.BoardListReplyCountDTO;
import com.example.springboottest.dto.PageRequestDTO;
import com.example.springboottest.dto.PageResponseDTO;

public interface BoardService {
    Long register(BoardDTO boardDTO);
    BoardDTO readOne(Long bno);
    void modify(BoardDTO boardDTO);
    void remove(Long bno);
    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);

    // 댓글의 숫자까지 처리
    PageResponseDTO<BoardListReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO);
}
