package com.fullstack_java_spring_boot.website.controllers;

import com.fullstack_java_spring_boot.website.dataaccessobject.UserDao;
import com.fullstack_java_spring_boot.website.models.User;
import com.fullstack_java_spring_boot.website.utils.JWT;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @RequestMapping(value = "api/user/{id}")
    public User getUser(@PathVariable Long id){
        User user = new User();
        user.setId(id);
        user.setName("John");
        user.setSurname("Doe");
        user.setEmail("email@email.com");
        user.setPassword("123456");
        user.setPhone("123456");

        return user;
    }
    @Autowired
    private UserDao userDao;

    @Autowired
    private JWT jwt;

    @RequestMapping(value = "api/users", method = RequestMethod.GET)
    public List<User> getUsers(@RequestHeader(value="Authorization") String token) {
        //if (!validateToken(token)) { return null;}
        return userDao.getUsers();
    }
    private boolean validateToken(String token) {
        String userId = jwt.getKey(token);
        return userId != null;
    }

    @RequestMapping(value = "api/users", method = RequestMethod.POST)
    public void registerUser(@RequestBody User user){
        System.out.print(user);
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, user.getPassword());
        user.setPassword(hash);
        userDao.registerUser(user);
    }

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody User user){
        User existingUser = userDao.ifExitsReturnUserObject(user);

        if(existingUser!=null){
            System.out.print("USER EXISTS IN DB");
            String token = jwt.create(String.valueOf(existingUser.getId()),existingUser.getEmail());
            System.out.print("TOKEN"+token);
            return token;
        }else{
            return "FAIL";
        }
    }

    @RequestMapping(value = "api/user/{id}", method = RequestMethod.DELETE)
    public void delete(@RequestHeader(value="Authorization") String token,@PathVariable Long id){
        if (validateToken(token)) { userDao.delete(id);}

    }

}
