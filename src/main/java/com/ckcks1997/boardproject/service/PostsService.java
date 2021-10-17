package com.ckcks1997.boardproject.service;

import com.ckcks1997.boardproject.controller.dto.PostsDto;
import com.ckcks1997.boardproject.domain.posts.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public void save(PostsDto requestDto){
         postsRepository.save(requestDto.toPost());
    }

    @Transactional
    public List<PostsDto> allPosts(){
        return postsRepository.findAll().stream()
                .map(data -> new PostsDto(data.getTitle(), data.getAuthor(), data.getContent()))
                .collect(Collectors.toList());
    }

}
