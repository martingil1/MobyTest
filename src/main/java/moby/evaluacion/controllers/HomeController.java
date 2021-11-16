package moby.evaluacion.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String login(){
        return "Welcome to TipsToCode Site!";
    }

    @GetMapping("/user")
    public String user(){
        return "Welcome user to TipsToCode Site!";
    }

    @GetMapping("/admin")
    public String admin(){
        return "Welcome admin to TipsToCode Site!";
    }
}