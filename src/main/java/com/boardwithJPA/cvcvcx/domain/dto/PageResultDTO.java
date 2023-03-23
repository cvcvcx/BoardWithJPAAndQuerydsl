package com.boardwithJPA.cvcvcx.domain.dto;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//댓글 등 페이지네이션이 필요한 곳에서 사용 가능하게끔 제네릭타입으로 생성
@Data
public class PageResultDTO<DTO> {
    private List<DTO> dtoList;

    private int totalPage;
    private int page;
    private int size;
    private int start,end;
    private boolean prev,next;

    private List<Integer> pageList;
    //fn은 EN 즉 엔티티를 DTO로 변환하는 함수이다.
    //여기서 참고할 수 있는 부분은 BoardService 의 default Function 부분이다.
    //하지만 지금 이 프로젝트에서는 Repository에서 Entity가 반환이 되는 것이 아니라, DTO가 반환이 되고있기때문에
    //변환할 필요가 없다.
    public PageResultDTO(Page<DTO> result){
        dtoList = result.stream().toList();
        totalPage = result.getTotalPages();
        makePageList(result.getPageable());
    }
    private void makePageList(Pageable pageable){
        this.page = pageable.getPageNumber()+1;
        this.size = pageable.getPageSize();

        int tempEnd = (int)(Math.ceil(page/10.0))*10;
        start = tempEnd-9;
        prev = start>1;
        next = totalPage>tempEnd;
        end = totalPage > tempEnd ? tempEnd : totalPage;
        pageList = IntStream.rangeClosed(start,end).boxed().collect(Collectors.toList());
    }
}
