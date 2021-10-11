package com.ckcks1997.boardproject.domain.posts;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @Test
    public void 글생성(){
        Posts posts1 = new Posts("hello", "hello world!!", "admin");
        Posts posts2 = new Posts("hello2", "hello world!!2", "admin");
        postsRepository.save(posts1);
        postsRepository.save(posts2);

        Assertions.assertThat(postsRepository.findAll().size()).isEqualTo(2);

    }

}