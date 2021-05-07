package com.jatinder.develop.api;

import com.jatinder.develop.model.AppUser;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/***
 * @author Jatinder
 * @since Nov-2020
 * @version 0.0.1
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @GetMapping
    public String authenticateUser(Principal principal){
        return "You have been authenticated";
    }

    @PostMapping
    public String generateToken(@RequestBody AppUser user){
        if(user!=null){
            System.out.println(user.getUsername() +" "+ user.getPassword());
            return "your token is";
        }
        return "username or password invalid";
    }
}
