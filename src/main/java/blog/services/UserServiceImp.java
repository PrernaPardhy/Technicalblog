package blog.services;

import blog.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Override
    public boolean authenticate(String userName, String password) {
        if(userName.equals("test") && password.equals("test1"))
        return true;
        else
            return false;
    }

    @Override
    public boolean registerNewUser(User user) {
        return true;
    }
}
