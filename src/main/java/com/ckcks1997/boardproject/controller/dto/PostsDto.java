package com.ckcks1997.boardproject.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostsDto {
    private String title;
    private String author;
    private String content;


}
