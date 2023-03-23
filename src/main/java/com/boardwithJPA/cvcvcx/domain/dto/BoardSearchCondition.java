package com.boardwithJPA.cvcvcx.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardSearchCondition {
    private String type;
    private String keyword;
    private BoardSearchConditionSort sort;
}
