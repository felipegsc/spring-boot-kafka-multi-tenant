package com.example.springkafka.input;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BatchCreateMessageInput {
    @NotNull private Integer fromTenant;
    @NotNull private Integer toTenant;
    @NotNull private Integer count;
}
