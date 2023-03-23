package com.boardwithJPA.cvcvcx.domain.repository;

import com.boardwithJPA.cvcvcx.domain.dto.BoardDTO;
import com.boardwithJPA.cvcvcx.domain.dto.BoardSearchCondition;
import com.boardwithJPA.cvcvcx.domain.dto.PageListRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardCustomRepository {


    Page<BoardDTO> findAllByTypeAndKeyword(PageListRequestDto requestDto);
}
