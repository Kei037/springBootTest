package com.example.springboottest.repository.search;

import com.example.springboottest.domain.Board;
import com.example.springboottest.domain.QBoard;
import com.example.springboottest.domain.QReply;
import com.example.springboottest.dto.BoardListReplyCountDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Log4j2
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    public BoardSearchImpl() { super(Board.class); }

    @Override
    public Page<Board> search1(Pageable pageable) {
        QBoard board = QBoard.board; // Q도메인 객체
        JPQLQuery<Board> query = from((board)); // select .. from board
        //query.where(board.title.contains("1"));  // where title like

        BooleanBuilder booleanBuilder = new BooleanBuilder(); // (
        booleanBuilder.or(board.title.contains("11")); // title like
        booleanBuilder.or(board.content.contains("11")); // content like

        query.where(booleanBuilder);
        query.where(board.bno.gt(0L));

        // paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<Board> list = query.fetch();
        long count = query.fetchCount();
        return null;
    }

    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {
        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);

        if ((types != null && types.length > 0) && keyword != null) { // 검색 조건과 키워드가 있다면
            BooleanBuilder booleanBuilder = new BooleanBuilder(); // (
            for (String type : types) {
                switch (type) {
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(board.writer.contains(keyword));
                        break;
                }
            } // end for
            query.where(booleanBuilder);
        } // end if

        // bno > 0
        query.where(board.bno.gt(0L));

        // paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<Board> list = query.fetch();
//        log.info("list : " + list);

        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);
    }

    // Querydsl을 이용한 검색 처리
    @Override
    public Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types, String keyword, Pageable pageable) {
        QBoard board = QBoard.board;
        QReply reply = QReply.reply;

        JPQLQuery<Board> query = from(board);
        query.leftJoin(reply).on(reply.board.eq(board)); // left join reply on reply.board = board
        query.groupBy(board); // board 기준으로 그룹화

        if ((types != null && types.length > 0) && keyword != null) { // 검색 조건과 키워드가 있다면
            BooleanBuilder booleanBuilder = new BooleanBuilder(); // (
            for (String type : types) {
                switch (type) {
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(board.writer.contains(keyword));
                        break;
                }
            } // end for
            query.where(booleanBuilder);
        } // end if

        query.where(board.bno.gt(0L));

        // select b.bno, b.title, b.writer, b.regDate, count(r) from Board b left join Reply r on r.board = b group by b
        JPQLQuery<BoardListReplyCountDTO> dtojpqlQuery = query.select(Projections.bean(BoardListReplyCountDTO.class,
                board.bno, board.title, board.writer, board.regDate, reply.count().as("replyCount")));

        this.getQuerydsl().applyPagination(pageable, dtojpqlQuery);
        List<BoardListReplyCountDTO> dtoList = dtojpqlQuery.fetch();
        long count = dtojpqlQuery.fetchCount();

        return new PageImpl<>(dtoList, pageable, count);
    }
}
