package com.example.springboottest.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Reply", indexes = {
        @Index(name = "idx_board_bno", columnList = "board_bno")
})
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "board")
public class Reply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    private String replyText;
    private String replyWriter;

    public void changeText(String text) {
        this.replyText = text;
    }

    public void changeWriter(String writer) {
        this.replyWriter = writer;
    }
}
