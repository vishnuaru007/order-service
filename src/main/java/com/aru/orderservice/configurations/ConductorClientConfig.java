package com.aru.orderservice.configurations;

import com.netflix.conductor.client.automator.TaskRunnerConfigurer;
import com.netflix.conductor.client.http.TaskClient;
import com.netflix.conductor.client.http.WorkflowClient;
import com.netflix.conductor.client.worker.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class ConductorClientConfig {
    
    @Value("${conductor.server.url}")
    private String conductorServerUrl;

    @Autowired
    private Worker[] workers;

    @Bean
    public TaskClient taskClient() {
        TaskClient client = new TaskClient();
        client.setRootURI(conductorServerUrl);
        return client;
    }

    @Bean
    public WorkflowClient workflowClient() {
        WorkflowClient client = new WorkflowClient();
        client.setRootURI(conductorServerUrl);
        return client;
    }

    @Bean
    public TaskRunnerConfigurer taskRunnerConfigurer(TaskClient taskClient) {
        return new TaskRunnerConfigurer
                .Builder(taskClient, Arrays.asList(workers))
                .withThreadCount(10)
                .build();
    }
}