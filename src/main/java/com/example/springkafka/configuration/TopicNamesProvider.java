package com.example.springkafka.configuration;

import com.example.springkafka.tenant.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TopicNamesProvider {

    private final TenantService tenantService;

    public String[] getTopicNames() {
        return tenantService.getAllTenantIds()
                .stream()
                .map(tenantId -> String.format("%s_messages", tenantId))
                .toArray(String[]::new);
    }
}
