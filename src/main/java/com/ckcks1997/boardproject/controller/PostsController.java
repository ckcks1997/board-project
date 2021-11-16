package com.ckcks1997.boardproject.controller;


import com.ckcks1997.boardproject.controller.dto.PageRequestDTO;
import com.ckcks1997.boardproject.controller.dto.PostsDto;
import com.ckcks1997.boardproject.service.PostsService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

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

    // 디비에 있는 전체 게시글을 전부 긁어오게됨
//    @GetMapping("/posts/list")
//    public String showAllPosts(Model model){
//        List<PostsDto> allPosts = postsService.allPosts();
//        model.addAttribute("allPosts", allPosts);
//        return "showposts";
//    }

    //위 코드를 대신함. 우선 글 번호 내림차순으로 10개만 가져옴..
    @GetMapping("/posts/list") //전체 페이지 정보를 어떻게 넘기느냐가 문제ㅠㅠ..
    public String mainPage(PageRequestDTO pageRequestDTO, Model model){
        List<PostsDto> postsDtoList = postsService.getPostDtoListInPageRequestDTO(pageRequestDTO);

        model.addAttribute("allPosts", postsDtoList);
        return "showposts";
    }

    @GetMapping("/posts/list/{id}")
    public String showPost(@PathVariable("id") Long postId, Model model){
        PostsDto data = postsService.findById(postId);
        model.addAttribute("postsDto",data);
        return "post";
    }

    
    //TODO: 글 삭제기능 구현
    @DeleteMapping("/posts/list/{id}")
    public String deletePost(@PathVariable("id") Long postId){
        String delete = postsService.delete(postId);
        System.out.println("delete = " + delete);
        return "post";
    }

}
