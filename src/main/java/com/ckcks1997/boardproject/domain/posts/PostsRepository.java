package com.ckcks1997.boardproject.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PostsRepository extends JpaRepository<Posts, Long>{


}
