package com.lcwd.user.service.services;
import com.lcwd.user.service.entities.User;
import java.util.List;

public interface UserService {

    //create
    User saveUser(User user);

    //get all users
    List<User> getAllUser();

    //getSingle User
    User getUser(String userId);

}
