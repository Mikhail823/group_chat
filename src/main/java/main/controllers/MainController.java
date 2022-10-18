package main.controllers;

import main.model.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.HashMap;
import java.util.List;

@RestController
public class MainController {

    @GetMapping("/init")
    public HashMap<String, Boolean> unit(){
        //TODO: check sessionId. If found => true, if not => false

        HashMap<String, Boolean> response = new HashMap<>();
        response.put("result", false);
        return response;
    }
    @PostMapping("/auth")
    public HashMap<String, Boolean> auth(@RequestParam String name){
        HashMap<String, Boolean> response = new HashMap<>();
        response.put("result", true);
        String sessionId = RequestContextHolder.getRequestAttributes().getSessionId();
        User user = new User();
        user.setName(name);
        user.setSessionId(sessionId);

        return response;
    }
    @PostMapping("/message")
    public Boolean sendMessage(@RequestParam String message){
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
