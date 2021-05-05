package com.example.springkafka.controller;

import com.example.springkafka.input.BatchCreateMessageInput;
import com.example.springkafka.input.CreateMessageInput;
import com.example.springkafka.producer.Producer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/topics")
public class TopicController {

    private final Producer producer;

    @PostMapping("/{name}/messages")
    public void createMessage(@PathVariable("name") final String topicName, @RequestBody @Valid final CreateMessageInput input) throws ExecutionException, InterruptedException {
        final var result = producer.sendMessage(topicName, input.getKey(), input.getMessage()).get();
        log.info(result.toString());
    }

    @PostMapping("/messages/_batch-create")
    public void batchCreateMessages(@RequestBody @Valid final BatchCreateMessageInput input) {
        IntStream.rangeClosed(input.getFromTenant(), input.getToTenant())
                .mapToObj(tenantNumber ->
                        IntStream.range(0, input.getCount())
                                .mapToObj(i -> producer.sendMessage(String.format("tenant%d_messages", tenantNumber), UUID.randomUUID().toString(), UUID.randomUUID().toString()))
                ).flatMap(Function.identity())
                .map(future -> {
                    try {
                        return future.get();
                    } catch (final Exception exception) {
                        throw new RuntimeException(exception);
                    }
                }).forEach(result -> log.info(result.toString()));
    }
}
