package com.ckcks1997.boardproject.web.dto;

import com.ckcks1997.boardproject.domain.posts.Posts;
import com.ckcks1997.boardproject.domain.user.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDto {
    private String userId;
    private String nickname;
    private String userPassword;
    private String role;

    public UserInfo toUserInfo(){
        return new UserInfo(2L, this.userId, this.nickname, this.userPassword, this.role);
    }
}
