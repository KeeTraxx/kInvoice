package ch.compile.kinvoice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class UserController {
    @RequestMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }

}
