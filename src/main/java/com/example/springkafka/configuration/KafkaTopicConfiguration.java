package com.example.springkafka.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

//@Configuration
public class KafkaTopicConfiguration {

    @Bean
    public NewTopic tenant1Topic() {
        return TopicBuilder.name("tenant1_messages")
                .partitions(6)
                .replicas(3)
                .build();
    }

    @Bean
    public NewTopic tenant2Topic() {
        return TopicBuilder.name("tenant2_messages")
                .partitions(6)
                .replicas(3)
                .build();
    }
}
