package com.ckcks1997.boardproject.web;


import com.ckcks1997.boardproject.web.dto.PostsDto;
import com.ckcks1997.boardproject.service.PostsService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class PostsController {

    @Autowired
    private PostsService postsService;

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

        postsService.save(postsDto);
        return "redirect:/posts/list";
    }

    @GetMapping("/posts/list")
    public String showAllPosts(Model model){
        List<PostsDto> allPosts = postsService.allPosts();
        model.addAttribute("allPosts", allPosts);
        return "showposts";
    }

    @GetMapping("/posts/list/{id}")
    public String showPost(@PathVariable("id") Long postId, Model model){
        PostsDto data = postsService.findById(postId);
        model.addAttribute("postsDto",data);
        return "post";
    }

}
