package blog.services;

import blog.common.Constants;
import blog.common.FileOperations;
import blog.common.PostManager;
import blog.model.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PostServiceImp implements PostService {

    PostManager postManager;

    public PostServiceImp() {
         postManager=new PostManager();

    }

    @Override
    public Post create(Post post) {
        postManager.writeToFile(post);
        return post;
    }

    @Override
    public List<Post> findAll() {

        return postManager.readAllPost();
    }

    @Override
    public List<Post> firstThreePost() {
        return postManager.getThreePost();
    }

    @Override
    public Post findByTitle(String id) {
      return   postManager.getPost(id);

    }

    @Override
    public Post editPost(Post post) {
        return null;
    }

    @Override
    public void deleteById(Post post) {
        postManager.deletePost(post.getTitle());
    }
}
