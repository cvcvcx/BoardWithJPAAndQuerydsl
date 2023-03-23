package com.boardwithJPA.cvcvcx.domain.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Board extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100,nullable = false)
    private String title;
    @Column(length = 1500,nullable = false)
    private String content;
    @Column(length = 100,nullable = false)
    private String writer;

}
