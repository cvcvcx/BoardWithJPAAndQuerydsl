package com.boardwithJPA.cvcvcx.domain.repository;

import com.boardwithJPA.cvcvcx.domain.dto.*;
import com.boardwithJPA.cvcvcx.domain.entity.Board;
import com.boardwithJPA.cvcvcx.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardCustomRepositoryImplTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardService boardService;
    @Test
    public void insertData(){
        IntStream.range(1,100).forEach((i)->{
            Board item = Board.builder()
                               .title("제목입니다.." + i)
                               .content("내용입니다." + i)
                               .writer("작성자" + i)
                               .build();
            boardRepository.save(item);
        });
    }
//
//    @Test
//    public void findAllTest(){
//        BoardSearchCondition boardSearchCondition = new BoardSearchCondition("title","2", BoardSearchConditionSort.TITLE_DESC);
//        Pageable pageable = PageRequest.of(0, 15);
//        Page<BoardDTO> allByTypeAndKeyword = boardRepository.findAllByTypeAndKeyword(boardSearchCondition,pageable);
//        long totalElements = allByTypeAndKeyword.getTotalElements();
//        System.out.println("totalElements = " + totalElements);
//        int size = allByTypeAndKeyword.getSize();
//        System.out.println("size = " + size);
//        allByTypeAndKeyword.forEach(item-> System.out.println(item));
//
//    }
    @Test
    public void findByPageRequestTest(){
        PageListRequestDto pageListRequestDto = PageListRequestDto.builder()
                                                  .page(1)
                                                  .size(15)
                                                  .sort(BoardSearchConditionSort.TITLE_DESC)
                                                  .type("w")
                .keyword("2")
                                                  .build();
        Page<BoardDTO> allByTypeAndKeyword = boardRepository.findAllByTypeAndKeyword(pageListRequestDto);
        long totalElements = allByTypeAndKeyword.getTotalElements();
        System.out.println("totalElements = " + totalElements);
        int size = allByTypeAndKeyword.getSize();
        System.out.println("size = " + size);
        allByTypeAndKeyword.forEach(item-> System.out.println(item));

    }

    @Test
    public void 서비스로페이지결과테스트(){
        PageListRequestDto pageListRequestDto = PageListRequestDto.builder()
                                                                  .page(1)
                                                                  .size(15)
                                                                  .sort(BoardSearchConditionSort.TITLE_DESC)
                                                                  .type("w")
                                                                  .keyword("2")
                                                                  .build();

        PageResultDTO<BoardDTO> list = boardService.getList(pageListRequestDto);
    }

}