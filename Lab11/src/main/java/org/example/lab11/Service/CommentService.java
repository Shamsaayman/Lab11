package org.example.lab11.Service;

import lombok.RequiredArgsConstructor;
import org.example.lab11.API.ApiException;
import org.example.lab11.Model.Comment;
import org.example.lab11.Model.Post;
import org.example.lab11.Model.User;
import org.example.lab11.Repository.CommentRepository;
import org.example.lab11.Repository.PostRepository;
import org.example.lab11.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository ;
    private final UserRepository userRepository ;
    private final PostRepository postRepository ;



    public List<Comment> getAll(){
        return commentRepository.findAll();
    }


    public void addComment( Comment comment){

        User u = userRepository.findUserByID(comment.getUserId());
        Post p = postRepository.findPostByID(comment.getPostId());

        if (u == null || p == null){
            throw new ApiException("Invalid id");
        }

        comment.setCommentDate(LocalDate.now());
        commentRepository.save(comment);
    }


    public void updateComment(Integer Id , Comment comment){

        Comment c = commentRepository.findCommentByID(Id);

        if (c == null){
            throw new ApiException("Invalid Id");
        }

        c.setContent(comment.getContent());
        c.setCommentDate(comment.getCommentDate());
        c.setPostId(comment.getPostId());
        c.setUserId(comment.getUserId());

        commentRepository.save(c);

    }



    public void deleteComment(Integer Id){

        Comment c = commentRepository.findCommentByID(Id);

        if (c == null){
            throw new ApiException("Invalid Id");
        }

        commentRepository.delete(c);

    }


    //3 Get all comment by Post ID
    public List<Comment> getAllByPostID(Integer Id){

        List<Comment> c = commentRepository.findCommentsByPostId(Id);

        if (c == null){
            throw new ApiException("Invalid post Id");
        }

        return c ;

    }
}
