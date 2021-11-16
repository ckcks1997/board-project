package com.ckcks1997.boardproject.controller.dto;

import com.ckcks1997.boardproject.domain.posts.Posts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostsDto {

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private long id;
    private String title;
    private String author;
    private String content;

    public PostsDto(long id, String title, String author, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public Posts toPost(){
        return new Posts(this.title, this.author, this.content);
    }
}
