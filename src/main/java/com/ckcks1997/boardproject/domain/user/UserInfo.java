package com.ckcks1997.boardproject.domain.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String nickname;
    private String userPassword;

    private String role;

    public UserInfo(Long id, String userId, String nickname, String userPassword, String role) {
        this.id = id;
        this.userId = userId;
        this.nickname = nickname;
        this.userPassword = userPassword;
        this.role = role;
    }
}
