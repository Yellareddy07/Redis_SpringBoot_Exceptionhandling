package com.example.Redis_Sb.Controller;

import com.example.Redis_Sb.Model.User;
import com.example.Redis_Sb.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Users")
public class Usercontroller {

    //@Autowired
    //private UserService userService;

    private final UserService userService;

    public Usercontroller(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUser(id);
    }


    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }


    @PostMapping
    public ResponseEntity<String> save(@RequestBody User user){

       // public User save(@RequestBody User user){
            // return userService.saveUser(user)  //returns JSON response with User type

        User uid=userService.saveUser(user);
        return ResponseEntity.ok("User with id "+uid.getId()+" Added Successfully");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted Successfully");
    }

}
