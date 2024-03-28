package org.example.lab11.Repository;
import java.util.List;
import org.example.lab11.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
    Comment findCommentByID(Integer Id);
    List<Comment> findCommentsByPostId(Integer Id);
}

