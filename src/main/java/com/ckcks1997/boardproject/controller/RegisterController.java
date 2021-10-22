package com.ckcks1997.boardproject.controller;

import com.ckcks1997.boardproject.domain.user.UserInfo;
import com.ckcks1997.boardproject.domain.user.UserInfoRepository;
import com.ckcks1997.boardproject.controller.dto.UserInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class RegisterController {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String addUser(Model model){
        model.addAttribute("userInfoDto", new UserInfoDto());
        return "register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute UserInfoDto userInfoDto, BindingResult bindingResult){
        UserInfo userInfo = userInfoDto.toUserInfo();
        log.info("---"+userInfoDto.getNickname());
        if(userInfoRepository.findByUserId(userInfo.getUserId()) != null){
            bindingResult.addError(new FieldError("userInfoDto", "userId", "아이디 중복"));
        }
        if (bindingResult.hasErrors()) {
            return "/register";
        }
        userInfo.setUserPassword(passwordEncoder.encode(userInfo.getUserPassword()));
        userInfoRepository.save(userInfo);
        return "redirect:/";
    }

}
