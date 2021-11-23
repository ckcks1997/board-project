package com.ckcks1997.boardproject.service;

import com.ckcks1997.boardproject.controller.dto.PageRequestDTO;
import com.ckcks1997.boardproject.controller.dto.PostsDto;
import com.ckcks1997.boardproject.domain.posts.Posts;
import com.ckcks1997.boardproject.domain.posts.PostsRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostsServiceTest {

    @Autowired
    private PostsRepository postsRepository;

    @Test
    void 데이터추가(){
        Posts post = new Posts("title", "author", "hello");

        postsRepository.save(post);

        Assertions.assertThat(post.getTitle()).isEqualTo(postsRepository.findAll().get(0).getTitle());
    }

    @Test
    void 데이터삭제(){
        Posts post = new Posts("title", "author", "hello");

        postsRepository.save(post);
        postsRepository.deleteById(1L);

        Assertions.assertThat(postsRepository.findAll().size()).isEqualTo(0);
    }

    @Test
    public void 페이징테스트(){

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("id").descending());

        Page<Posts> all = postsRepository.findAll(pageable);
        System.out.println("-----------------------");
        for (Posts posts : all) {
            System.out.println(posts.getTitle());
            System.out.println(posts.getContent());
        }


    }
}