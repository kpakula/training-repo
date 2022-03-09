package com.kpakula.trainingrepository.controller;

import com.kpakula.trainingrepository.MessageConfig;
import com.kpakula.trainingrepository.domain.User;
import com.kpakula.trainingrepository.repository.UserRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final RabbitTemplate rabbitTemplate;
    private final UserRepository userRepository;

    public UserController(UserRepository personRepository, RabbitTemplate rabbitTemplate) {
        this.userRepository = personRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping()
    public String createUser(@RequestParam String name) {
        User user = userRepository.save(new User(name));

        rabbitTemplate.convertAndSend(MessageConfig.EXCHANGE, MessageConfig.ROUTING_KEY, user);
        return userRepository.findById(user.getId()) + " saved successfully";
    }

    @GetMapping()
    public List<User> getAllPeople() {
        return (List<User>) userRepository.findAll();
    }
}
