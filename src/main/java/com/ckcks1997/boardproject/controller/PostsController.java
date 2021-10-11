package com.ckcks1997.boardproject.controller;


import com.ckcks1997.boardproject.controller.dto.PostsDto;
import com.ckcks1997.boardproject.domain.posts.Posts;
import com.ckcks1997.boardproject.domain.posts.PostsRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class PostsController {

    @Autowired
    private PostsRepository postsRepository;

    @GetMapping("/posts/save")
    public String addPosts(Model model){
        model.addAttribute("postsDto", new PostsDto());
        return "postsform";
    }



    @PostMapping("/posts/save")
    public String savePosts(PostsDto postsDto){
        System.out.println("postsDto = " + postsDto);
        Posts posts = new Posts(postsDto.getTitle(), postsDto.getContent(), postsDto.getAuthor());
        postsRepository.save(posts);
        return "redirect:/";
    }

}