package org.example.lab11.Service;

import lombok.RequiredArgsConstructor;
import org.example.lab11.API.ApiException;
import org.example.lab11.Model.Category;
import org.example.lab11.Model.Post;
import org.example.lab11.Model.User;
import org.example.lab11.Repository.CategoryRepository;
import org.example.lab11.Repository.PostRepository;
import org.example.lab11.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {


    private final PostRepository postRepository ;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;


    public List<Post> getAll(){
        return postRepository.findAll();
    }


    public void addPost(Post post){

        Category c = categoryRepository.findCategoryByID(post.getCategoryId());
        User u = userRepository.findUserByID(post.getUserId());

        if (c == null ) {
            if (u == null) {
                throw new ApiException("Invalid user Id");
            }
            throw new ApiException("Invalid category Id");
        }

        post.setPublishDate(LocalDate.now());
        postRepository.save(post);
    }


    public void updatePost(Integer Id , Post post){

        Post p = postRepository.findPostByID(Id);

        if (p == null){
            throw new ApiException("Invalid Id");
        }

        p.setContent(post.getContent());
        p.setTitle(post.getTitle());
        p.setCategoryId(post.getCategoryId());
        p.setUserId(post.getUserId());
        p.setPublishDate(post.getPublishDate());


        postRepository.save(p);

    }



    public void deletePost(Integer Id){

        Post p = postRepository.findPostByID(Id);

        if (p == null){
            throw new ApiException("Invalid Id");
        }

        postRepository.delete(p);

    }



//1 Get all post by user id
    public List<Post> getAllPost(Integer id){

        List<Post> p = postRepository.findPostsByUserId(id);

        if (p == null ){
            throw new ApiException("Invalid Id");
        }


        return p ;

    }


//2 get post by title
    public Post getPostByTitle(String title){
        Post p = postRepository.findPostByTitle(title);

        if (p == null){
            throw new ApiException("Invalid title");
        }

        return p ;

    }


//4 get all post before date
    public List<Post> getAllBeforeDate(LocalDate date){

        List<Post> posts = postRepository.findPostsByPublishDateBefore(date);

        if (posts == null){
            throw new ApiException("Invalid date");
        }

        return posts ;

    }

   //5 get post by 2 ids
    public Post getPostByUIdAndCId(Integer CId , Integer UId ){

        Post p = postRepository.findPostsByCategoryIdAndUserId(CId , UId);
        if (p == null){
            throw new ApiException("Invalid user id or category id");
        }

        return p ;

    }

    //6 get post by title and date
    public List<Post> getPostBytitleAndDate (String title , LocalDate date){

        List<Post> p = postRepository.findPostsByTitleAndPublishDate(title, date);

        if (p == null){
            throw new ApiException("Invalid");
        }

        return p ;

    }


    //8 get posts published today
    public List<Post> getPostPublishedToday (LocalDate date){

        List<Post> p = postRepository.findPostsByPublishDateIs(date.now());

        if (p == null){
            throw new ApiException("Invalid");
        }

        return p ;

    }
}
