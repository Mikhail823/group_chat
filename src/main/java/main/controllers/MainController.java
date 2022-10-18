package main.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class MainController {
    @GetMapping("/init")
    public Boolean unit(){
        //TODO: check sessionId. If found => true, if not => false
        return true;
    }
    @PostMapping("/message")
    public Boolean sendMessage(String message){
        return true;
    }
    @GetMapping("/message")
    public List<String> getMessageList(){
        return null;
    }
    @GetMapping("/user")
    public HashMap<Integer, String> getUsersList(){
        return null;
    }


}
