package blog.controller;

import blog.model.Post;
import blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
public class HomeController {//controller class


    @Autowired
    private  PostService postService;
    @RequestMapping("/")

    /*
    The index() method is a comtroller method. This method is mapped to the url "/".
    This method redirects to the index.html page. which displays Hello World
     */
    public String index(Model model){//controller method. This method is mapped to url "/"

       List<Post> list= postService.firstThreePost();
       model.addAttribute("posts",list);

        return "index";
    }


}
