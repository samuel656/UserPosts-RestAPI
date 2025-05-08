package com.webservices.restful_web_services.service;

import com.webservices.restful_web_services.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {
    private static List<User> users=new ArrayList<>();
    private static int count=0;
    static{
        users.add(new User(++count,"Samuel", LocalDate.now().minusYears(23)));
        users.add(new User(++count,"Swetha", LocalDate.now().minusYears(20)));
        users.add(new User(++count,"Sampath", LocalDate.now().minusYears(24)));
    }

    public List<User> findALl()
    {
        return users;
    }

    public User findById(int id) {
            Predicate<? super User> predicate = user -> {
                return user.getId()==id;
            };
            return users.stream().filter(predicate).findFirst().orElse(null);

    }

    public User save(User user)
    {
        user.setId(++count);
        users.add(user);
        return user;
    }


    public void deleteById(int id) {
        Predicate<? super User> predicate = user -> {
            return user.getId()==id;
        };
        users.removeIf(predicate);
    }
}
