package com.example.springkafka.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class KafkaTopicConfiguration implements BeanFactoryAware {

    private final TopicNamesProvider topicNamesProvider;
    private ConfigurableBeanFactory beanFactory;

    @PostConstruct
    public void registerTenantMessagesTopics() {
        log.info("Registering beans responsible for creating the topics");
        Arrays.stream(topicNamesProvider.getTopicNames())
                .map(topicName -> TopicBuilder
                        .name(topicName)
                        .partitions(6)
                        .replicas(3)
                        .build())
                .forEach(topic -> {
                    beanFactory.registerSingleton(topic.name(), topic);
                    log.info("Registered bean for the topic {}, with {} partition(s) and {} replica(s)", topic.name(), topic.numPartitions(), topic.replicationFactor());
                });
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableBeanFactory) beanFactory;
    }
}
