package main.controllers;

import main.dto.DtoMessage;
import main.dto.MessageMapper;
import main.model.Message;
import main.model.User;
import main.repository.MessageRepository;
import main.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class MainController {
    @Autowired
    private UserRepository repository;
    @Autowired
    private MessageRepository messageRepository;

    private MessageMapper messageMapper;
    private Log log = LogFactory.getLog(MainController.class);
    @GetMapping("/init")
    public HashMap<String, Boolean> unit(){
        //TODO: check sessionId. If found => true, if not => false

        HashMap<String, Boolean> response = new HashMap<>();
        String sessionId = RequestContextHolder.getRequestAttributes().getSessionId();
        Optional<User> user = repository.findBySessionId(sessionId);
        response.put("result", user.isPresent());
        return response;
    }
    @PostMapping("/auth")
    public HashMap<String, Boolean> auth(@RequestParam String name){
        HashMap<String, Boolean> response = new HashMap<>();
        if (Strings.isEmpty(name)){
            response.put("result", false);
            return response;
        }
        String sessionId = RequestContextHolder.getRequestAttributes().getSessionId();
        User user = new User();
        user.setName(name);
        user.setSessionId(sessionId);
        repository.save(user);
        response.put("result", true);
        return response;
    }
    @PostMapping("/message")
    public HashMap<String, Boolean> sendMessage(@RequestParam String message){
        HashMap<String, Boolean> response = new HashMap<>();
        String sessionId = RequestContextHolder.getRequestAttributes().getSessionId();
        User user =  repository.findBySessionId(sessionId).get();
        if (Strings.isEmpty(message)) {
           response.put("result", false);
           log.info("Пустое сообщение");
           return response;
        }

        Message m = new Message();
        m.setLocalDateTime(LocalDateTime.now());
        m.setMessage(message);
        m.setUser(user);
        messageRepository.saveAndFlush(m);
        response.put("result", true);
        log.info("Вы отправили сообщение " + message);
        return response;
    }
    @GetMapping("/message")
    public List<DtoMessage> getMessageList(){
        return messageRepository
                .findAll(Sort.by(Sort.Direction.ASC, "localDateTime"))
                .stream()
                .map(message -> MessageMapper.map(message))
                .collect(Collectors.toList());
    }
    @GetMapping("/user")
    public HashMap<Integer, String> getUsersList(){
        return null;
    }


}
