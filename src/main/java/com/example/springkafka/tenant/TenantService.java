package com.example.springkafka.tenant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class TenantService {

    @Min(1)
    @Value("${service.number-of-tenants}")
    private int numberOfTenants;

    public List<String> getAllTenantIds() {
        return IntStream.rangeClosed(1, numberOfTenants)
            .mapToObj(i -> String.format("tenant%d", i))
            .collect(Collectors.toList());
    }
}
