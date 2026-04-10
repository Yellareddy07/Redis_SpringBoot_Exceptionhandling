package com.example.Redis_Sb.Service;

import com.example.Redis_Sb.Exceptions.UserNotFoundException;
import com.example.Redis_Sb.Model.User;
import com.example.Redis_Sb.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    //@Autowired
    //private UserRepository userRepo;
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }


    //get user      travels from cache to db to fetch user
    @Cacheable(value = "users", key = "#id")
    public User getUser(Long id){
        System.out.println("retrieving user by id from mysql");
       return userRepo.findById(id)
               .orElseThrow(()->new UserNotFoundException("user not found with id "+id));
    }

    @Cacheable(value = "users", key = "'all'")
    public List<User> getAllUsers() {
        System.out.println("Fetching ALL users from DB...");
        return userRepo.findAll();
    }


    //save or update user       cache+db
    @CachePut(value = "users", key = "#result.id")
    @CacheEvict(value = "users", key = "'all'")
    public User saveUser(User user){
        return userRepo.save(user);
    }


    //delete user       cache +db remove
    @Caching(evict = {                  //caching used for multiple operations
            @CacheEvict(value = "users", key = "#id"),
            @CacheEvict(value = "users", key = "'all'")
    })
    public void deleteUser(Long id){
        User user=userRepo.findById(id)
                .orElseThrow(()->new UserNotFoundException("user not found with id "+id));

        userRepo.deleteById(id);
    }






}
