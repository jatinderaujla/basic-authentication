package com.jatinder.develop.api;

import com.jatinder.develop.model.AppUser;
import com.jatinder.develop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
/***
 * @author Jatinder
 * @since Nov-2020
 * @version 0.0.1
 * @apiNote UserController is responsible for user <b>CRUD</b> operations
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping
    public AppUser createUser(@RequestBody AppUser user){
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @DeleteMapping("/{user-id}")
    public String deleteUser(@PathVariable("user-id") Long userId){
        boolean user = userRepository.existsById(userId);
        if(user){
            userRepository.deleteById(userId);
            return "User deleted successfully!";
        }else{
            throw new UsernameNotFoundException("User you are trying to delete does not exist");
        }
    }

    @PatchMapping
    public AppUser updateUser(@RequestBody AppUser user){
        return userRepository.save(user);
    }

    @GetMapping("/{username}")
    public AppUser fetchUserByUsername(@PathVariable String username){
        return  userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
    }

}
