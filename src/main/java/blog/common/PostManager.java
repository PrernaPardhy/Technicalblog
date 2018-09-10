package blog.common;

import blog.model.Post;

import java.io.File;
import java.util.List;


public class PostManager {

private FileOperations<Post> fileOperations;
public PostManager(){
    fileOperations=FileOperations.getInstance();

}

public List<Post> readAllPost(){
    return fileOperations.readAllFiles(Constants.POST_DIR_NAME);
}
public List<Post>getThreePost(){
    return   fileOperations.readRecentFiles(3,Constants.POST_DIR_NAME);
}
public static int numberOfPost(){
    File file=new File(Constants.POST_DIR_NAME);
    File[] files=file.listFiles();
    return files.length;
}

public boolean deletePost(final String postTitle){
    return fileOperations.deleteFile(Constants.POST_FILE_PREFIX,postTitle);

}

    public Post writeToFile(final Post post){
        JDBCConnector jdbcConnector=JDBCConnector.getInstance();

        String query="insert into posts(title,body,data) values (\'"+post.getTitle()+ "\',\'"
                +  post.getBody()+"\',\'03-03-2017\')";

        jdbcConnector.execute(query);
        return null;

      // return  (Post)FileOperations.getInstance().writeToFile(Constants.POST_FILE_PREFIX,post, post.getTitle());

    }
    public Post getPost(final String prefix) {
        return (Post) fileOperations.readFile(Constants.POST_FILE_PREFIX, prefix);
    }
}
