package com.ckcks1997.boardproject.controller.dto;

import com.ckcks1997.boardproject.domain.posts.Posts;
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

    public Posts toPost(){
        return new Posts(this.title, this.author, this.content);
    }
}
