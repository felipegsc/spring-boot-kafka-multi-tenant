package com.example.springkafka.input;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateMessageInput {
    @NotBlank
    private String key;
    @NotBlank
    private String message;
}
