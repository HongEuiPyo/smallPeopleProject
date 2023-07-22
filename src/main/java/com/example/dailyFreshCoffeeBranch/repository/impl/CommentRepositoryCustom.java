package com.example.dailyFreshCoffeeBranch.repository.impl;

import com.example.dailyFreshCoffeeBranch.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CommentRepositoryCustom {

    Page<Comment> findAllByBoardIdWithPage(Pageable pageable, Long boardId);

    Optional<Comment> findByBoardIdAndCommentId(Long boardId, Long commentId);

}