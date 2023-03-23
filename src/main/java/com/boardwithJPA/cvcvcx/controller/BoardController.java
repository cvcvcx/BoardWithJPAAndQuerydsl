package com.boardwithJPA.cvcvcx.controller;


import com.boardwithJPA.cvcvcx.domain.dto.BoardSearchConditionSort;
import com.boardwithJPA.cvcvcx.domain.dto.PageListRequestDto;
import com.boardwithJPA.cvcvcx.domain.dto.PageResultDTO;
import com.boardwithJPA.cvcvcx.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("board")
@Slf4j
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public PageResultDTO showBoard(String type, String keyword, BoardSearchConditionSort sort, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "15") int size) {
        PageListRequestDto pageListRequestDto = PageListRequestDto.builder()
                                                     .type(type)
                                                     .page(page)
                                                     .keyword(keyword)
                                                     .sort(sort)
                                                     .size(size)
                                                     .build();
        log.info(pageListRequestDto.toString());

        return boardService.getList(pageListRequestDto);
    }

}
