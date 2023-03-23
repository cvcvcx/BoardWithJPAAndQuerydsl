package com.boardwithJPA.cvcvcx.service;

import com.boardwithJPA.cvcvcx.domain.dto.BoardDTO;
import com.boardwithJPA.cvcvcx.domain.dto.PageListRequestDto;
import com.boardwithJPA.cvcvcx.domain.dto.PageResultDTO;
import com.boardwithJPA.cvcvcx.domain.entity.Board;
import com.boardwithJPA.cvcvcx.domain.repository.BoardRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository repository;
    @Override
    public Long register(BoardDTO boardDTO) {
        Board board = dtoToEntity(boardDTO);

        return repository.save(board).getId();
    }

    @Override
    public PageResultDTO<BoardDTO> getList(PageListRequestDto pageListRequestDto) {

        Page<BoardDTO> allByTypeAndKeyword = repository.findAllByTypeAndKeyword(pageListRequestDto);
        PageResultDTO<BoardDTO> result = new PageResultDTO<>(allByTypeAndKeyword);
        return result;
    }


}
