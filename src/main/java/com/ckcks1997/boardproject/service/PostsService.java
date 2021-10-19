package com.ckcks1997.boardproject.service;

import com.ckcks1997.boardproject.domain.posts.Posts;
import com.ckcks1997.boardproject.web.dto.PostsDto;
import com.ckcks1997.boardproject.domain.posts.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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
                .map(data -> new PostsDto(data.getId(), data.getTitle(), data.getAuthor(), data.getContent()))
                .collect(Collectors.toList());
    }

    @Transactional
    public PostsDto findById(Long id){
        Optional<Posts> data = postsRepository.findById(id);
        if(data.isPresent()) {
            Posts posts = data.get();
            return new PostsDto(posts.getCreatedDate(), posts.getModifiedDate(),
                    posts.getId(), posts.getTitle(), posts.getAuthor(), posts.getContent());
        }
        return new PostsDto();
    }

    @Transactional
    public String delete(Long id){
        Optional<Posts> data = postsRepository.findById(id);
        if(data.isEmpty()){
            return "FAIL";
        }else {
            postsRepository.delete(data.get());
            return "SUCCESS";
        }
    }

}
