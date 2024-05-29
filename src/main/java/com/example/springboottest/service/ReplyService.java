package com.example.springboottest.service;

import com.example.springboottest.dto.PageRequestDTO;
import com.example.springboottest.dto.PageResponseDTO;
import com.example.springboottest.dto.ReplyDTO;

public interface ReplyService {
    Long register(ReplyDTO replyDTO);
    ReplyDTO read(Long rno);
    void modify(ReplyDTO replyDTO);
    void remove(Long rno);

    PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO);
}
