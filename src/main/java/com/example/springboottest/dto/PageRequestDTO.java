package com.example.springboottest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {
    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 10;

    private String type; // 검색의 종류 t, c, w, tc, tw, twc

    private String keyword;

    public String[] getTypes() {
        if (this.type == null || getType().isEmpty()) {
            return null;
        }
        return this.type.split("");
    }
/*
    public Pageable getPageable(String...props) {
        return PageRequest.of(this.page - 1, this.size, Sort.by(props).descending());
    }
 */
}
