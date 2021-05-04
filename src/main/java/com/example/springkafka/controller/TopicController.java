package com.example.springkafka.controller;

import com.example.springkafka.input.CreateMessageInput;
import com.example.springkafka.producer.Producer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/topics")
public class TopicController {

    private final Producer producer;

    @PostMapping("/{name}/messages")
    public void createMessage(@PathVariable("name") final String topicName, @RequestBody @Valid final CreateMessageInput input) {
        producer.sendMessage(topicName, input.getKey(), input.getMessage());
    }
}
