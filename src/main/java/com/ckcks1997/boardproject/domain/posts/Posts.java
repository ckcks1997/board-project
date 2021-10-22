package com.ckcks1997.boardproject.domain.posts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity

public class Posts extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    private String author;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    public Posts(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

}
