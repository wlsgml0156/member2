package com.example.member2.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BoardDTO {
    private Integer id;
    private String title;
    private String content;
    private String filename;
    private String filepath;

}
