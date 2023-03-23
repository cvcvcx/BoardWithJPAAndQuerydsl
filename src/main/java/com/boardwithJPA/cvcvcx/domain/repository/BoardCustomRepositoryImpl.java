package com.boardwithJPA.cvcvcx.domain.repository;

import com.boardwithJPA.cvcvcx.domain.dto.*;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.boardwithJPA.cvcvcx.domain.entity.QBoard.board;

@Repository
@RequiredArgsConstructor
public class BoardCustomRepositoryImpl implements BoardCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<BoardDTO> findAllByTypeAndKeyword(PageListRequestDto requestDto) {

        PageRequest pageable = PageRequest.of(requestDto.getPage()-1, requestDto.getSize());

        BoardSearchCondition boardSearchCondition = getBoardSearchCondition(requestDto);


        List<BoardDTO> fetch = jpaQueryFactory.select(new QBoardDTO(board.id, board.title, board.content, board.writer, board.regDate,board.modDate))
                                              .from(board)
                                              .offset(pageable.getOffset())
                                              .limit(pageable.getPageSize())
                                              .where(createPredicate(boardSearchCondition))
                                              .orderBy(createOrderSpecifier(boardSearchCondition))
                                              .fetch();


        JPAQuery<Long> countQuery = countQuery(boardSearchCondition);

        return PageableExecutionUtils.getPage(fetch, pageable, () -> countQuery.fetchOne());
    }

    private JPAQuery<Long> countQuery(BoardSearchCondition boardSearchCondition) {
        JPAQuery<Long> countQuery = jpaQueryFactory
                .select(board.count())
                .from(board)
                .where(createPredicate(boardSearchCondition));
        return countQuery;
    }

    private BoardSearchCondition getBoardSearchCondition(PageListRequestDto requestDto) {
        BoardSearchCondition boardSearchCondition = BoardSearchCondition.builder()
                                                                        .type(requestDto.getType())
                                                                        .keyword(requestDto.getKeyword())
                                                                        .sort(requestDto.getSort())
                                                                        .build();
        return boardSearchCondition;
    }

    private OrderSpecifier[] createOrderSpecifier(BoardSearchCondition boardSearchCondition) {
        List<OrderSpecifier> orderSpecifiers = new ArrayList<>();
        if (Objects.isNull(boardSearchCondition)) {
            orderSpecifiers.add(new OrderSpecifier<>(Order.DESC, board.id));
        } else if (boardSearchCondition.getSort()
                                       .equals(BoardSearchConditionSort.TITLE_ASC)) {
            orderSpecifiers.add(new OrderSpecifier<>(Order.ASC, board.title));
        } else if (boardSearchCondition.getSort()
                                       .equals(BoardSearchConditionSort.TITLE_DESC)) {
            orderSpecifiers.add(new OrderSpecifier<>(Order.DESC, board.title));
        } else if (boardSearchCondition.getSort()
                                       .equals(BoardSearchConditionSort.CONTENT_ASC)) {
            orderSpecifiers.add(new OrderSpecifier<>(Order.ASC, board.content));
        } else if (boardSearchCondition.getSort()
                                       .equals(BoardSearchConditionSort.CONTENT_DESC)) {
            orderSpecifiers.add(new OrderSpecifier<>(Order.DESC, board.content));
        } else if (boardSearchCondition.getSort()
                                       .equals(BoardSearchConditionSort.WRITER_ASC)) {
            orderSpecifiers.add(new OrderSpecifier<>(Order.ASC, board.writer));
        } else if (boardSearchCondition.getSort()
                                       .equals(BoardSearchConditionSort.WRITER_DESC)) {
            orderSpecifiers.add(new OrderSpecifier<>(Order.DESC, board.writer));
        }
        return orderSpecifiers.toArray(new OrderSpecifier[orderSpecifiers.size()]);
    }

    private BooleanBuilder createPredicate(BoardSearchCondition boardSearchCondition) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (Objects.isNull(boardSearchCondition)) {
            return null;
        } else {
            String type = boardSearchCondition.getType();
            String keyword = boardSearchCondition.getKeyword();
            if(keyword==null){
                return null;
            }
            if (type.contains("t")) {
                booleanBuilder.or(board.title.contains(keyword));
            }
            if (type.contains("c")) {
                booleanBuilder.or(board.content.contains(keyword));
            }
            if (type.contains("w")) {
                booleanBuilder.or(board.writer.contains(keyword));
            }

            return booleanBuilder;
        }

    }
}
