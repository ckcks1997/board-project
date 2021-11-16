package com.ckcks1997.boardproject.controller.dto;

import com.ckcks1997.boardproject.domain.user.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoDto {
    private String userId;
    private String nickname;
    private String userPassword;
    private String role;

    public UserInfo toUserInfo(){
        return new UserInfo( this.userId, this.nickname, this.userPassword, this.role);
    }
}
