package com.ckcks1997.boardproject.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    public UserInfo findByUserId(String userId);
}
