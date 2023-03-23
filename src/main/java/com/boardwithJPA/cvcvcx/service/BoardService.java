package com.boardwithJPA.cvcvcx.service;

import com.boardwithJPA.cvcvcx.domain.dto.BoardDTO;
import com.boardwithJPA.cvcvcx.domain.dto.PageListRequestDto;
import com.boardwithJPA.cvcvcx.domain.dto.PageResultDTO;
import com.boardwithJPA.cvcvcx.domain.entity.Board;

public interface BoardService {
    Long register(BoardDTO boardDTO);
    PageResultDTO<BoardDTO> getList(PageListRequestDto pageListRequestDto);

    default Board dtoToEntity(BoardDTO boardDTO){
        Board result = Board.builder()
                           .id(boardDTO.getId())
                           .title(boardDTO.getTitle())
                           .content(boardDTO.getContent())
                           .writer(boardDTO.getWriter())
                           .build();
        return result;
    }

    default BoardDTO entityToDto(Board board){
        BoardDTO result = BoardDTO.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .build();
        return result;
    }
}
