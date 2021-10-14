package com.ckcks1997.boardproject.controller;


import com.ckcks1997.boardproject.controller.dto.PostsDto;
import com.ckcks1997.boardproject.domain.posts.Posts;
import com.ckcks1997.boardproject.domain.posts.PostsRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;


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
    public String savePosts(@ModelAttribute PostsDto postsDto, BindingResult bindingResult){
        if(!StringUtils.hasText(postsDto.getTitle())){
            bindingResult.addError(new FieldError("postsDto", "title", "제목은 필수 입니다."));
        }
        if(!StringUtils.hasText(postsDto.getAuthor())){
            bindingResult.addError(new FieldError("postsDto", "author", "작성자 입력은 필수 입니다."));
        }
        if(!StringUtils.hasText(postsDto.getContent())){
            bindingResult.addError(new FieldError("postsDto", "content", "내용을 입력하세요."));
        }
        if (bindingResult.hasErrors()) {
            return "/postsform";
        }

        Posts posts = new Posts(postsDto.getTitle(), postsDto.getContent(), postsDto.getAuthor());
        postsRepository.save(posts);
        return "redirect:/posts/list";
    }

    @GetMapping("/posts/list")
    public String showAllPosts(Model model){
        List<PostsDto> allPosts = postsRepository.findAll().stream()
                .map(data -> new PostsDto(data.getTitle(), data.getAuthor(), data.getContent()))
                .collect(Collectors.toList());
        model.addAttribute("allPosts", allPosts);
        return "showposts";
    }

}
