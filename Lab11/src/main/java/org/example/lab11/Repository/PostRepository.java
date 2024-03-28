package org.example.lab11.Repository;

import org.example.lab11.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Post findPostByID(Integer Id);
    List<Post> findPostsByUserId(Integer Id);
    Post findPostByTitle(String title);
    List<Post> findPostsByPublishDateBefore(LocalDate date);
    Post findPostsByCategoryIdAndUserId(Integer cid , Integer uid);
    List<Post> findPostsByTitleAndPublishDate ( String title , LocalDate date);
    List<Post> findPostsByPublishDateIs(LocalDate date);
}
