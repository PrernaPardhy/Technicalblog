package blog.controller;

import blog.forms.RegisterNewUser;
import blog.model.Post;
import blog.model.User;
import blog.services.PostService;

import blog.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserServiceImp userServiceImp;

    @Autowired
    private PostService postService;


    @RequestMapping("users/login")
    private String loginPage(User user){
        return "users/login";

    }

    @RequestMapping(value = "/users/login", method = RequestMethod.POST)
    private String login(User user, Model model){

        if(userServiceImp.authenticate(user.getUsername(),user.getPasswordHash())){
            return "redirect:/posts";
        }
        else
            return "users/login";

    }

    @RequestMapping("/users/logout")
    private String logout(Model model){
        List<Post> list= postService.firstThreePost();
        model.addAttribute("posts",list);
        return "index";
    }

    @RequestMapping("/users/register")
    public String register(RegisterNewUser registerNewUser){
        return "/users/register";
    }


    @RequestMapping(value = "/users/register",method = RequestMethod.POST)
    public String registerUser(RegisterNewUser registerNewUser){
        User user=new User(registerNewUser.getUserName(), registerNewUser.getPasswordHash(), registerNewUser.getFullName());

        userServiceImp.registerNewUser(user);
        return "redirect:/";

    }
}
