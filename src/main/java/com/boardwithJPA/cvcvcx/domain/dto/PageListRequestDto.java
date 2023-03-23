package com.boardwithJPA.cvcvcx.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.domain.PageRequest;

import java.awt.print.Pageable;

@Data
@ToString
@Builder
public class PageListRequestDto {
    private String type;
    private String keyword;
    private BoardSearchConditionSort sort;
    private Integer page;
    private Integer size;


    public PageListRequestDto() {
        this.sort = BoardSearchConditionSort.TITLE_DESC;
        this.page = 1;
        this.size = 15;
        this.type = "";
        this.keyword = "";

    }

    public PageListRequestDto(String type, String keyword, BoardSearchConditionSort sort, Integer reqPage, Integer reqSize) {
        if(reqPage!=null){
            this.page = reqPage;
        }else {
            this.page = 1;
        }
        if(reqSize!=null){
            this.size = reqSize;
        }else {
            this.size = 15;
        }
        this.type = type;
        this.keyword = keyword;
        this.sort = sort;
    }
}
