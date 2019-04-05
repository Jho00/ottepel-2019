package org.pet.social.BLL.implementation;

import org.pet.social.BLL.contracts.CommentsServiceInterface;
import org.pet.social.DAL.contracts.CommentInterface;
import org.pet.social.DAL.contracts.ProblemInterface;
import org.pet.social.DAL.contracts.UserInterface;
import org.pet.social.common.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Service
public class CommentsService implements CommentsServiceInterface {

    @Autowired
    CommentInterface comments;
    @Autowired
    ProblemInterface problems;
    @Autowired
    UserInterface users;

    public boolean Add(String text, Integer problemId, Integer userId){

        if(!problems.findById(problemId).isPresent()) return false;
        if(!users.findById(userId).isPresent()) return false;
        if(text == null || text.trim().isEmpty()) return false;

        Comment c = new Comment();
        c.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        c.setProblemId(problemId);
        c.setText(text);
        c.setUserId(userId);
        c.setLikes(0);
        c.setDislikes(0);
        comments.save(c);
        return true;
    }

    public Comment Get(Integer commentId){
        if(commentId == null) return null;
        Optional<Comment> c = comments.findById(commentId);
        return c.orElse(null);
    }

    public boolean Like(Integer commentId, Integer userId){
        if(commentId == null || userId == null) return false;
        Optional<Comment> c = comments.findById(commentId);
        if(!c.isPresent()) return false;
        c.get().setLikes(c.get().getLikes() + 1);
        return true;
    }
    public boolean Dislike(Integer commentId, Integer userId){
        if(commentId == null || userId == null) return false;
        Optional<Comment> c = comments.findById(commentId);
        if(!c.isPresent()) return false;
        c.get().setDislikes(c.get().getDislikes() + 1);
        return true;
    }
}
