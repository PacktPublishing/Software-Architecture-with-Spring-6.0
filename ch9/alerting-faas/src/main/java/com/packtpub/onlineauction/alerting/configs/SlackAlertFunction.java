package com.packtpub.onlineauction.alerting.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
public class SlackAlertFunction {

    @Value("${slack.webhook.url}")
    private String slackWebhookUrl;

    private final RestClient restClient;

    public SlackAlertFunction(RestClient.Builder restClient) {
        this.restClient = restClient.build();
    }

    @Bean
    public Consumer<Message> alertSlackChannelConsumer() {
        return message -> {
            try {
                restClient.post()
                        .uri(slackWebhookUrl)
                        .header("Content-Type", "application/json")
                        .body(message)
                        .retrieve();

                System.out.println("Message sent to Slack successfully. (Consumer)");
            } catch (Exception e) {
                System.out.println("Error alerting Slack: " + e.getMessage());
            }
        };
    }

    @Bean
    public Supplier<String> alertSlackChannelSupplier() {
        return () -> {
            try {
                String messageContent = "The ETL process is completed! (Supplier)";

                String payload = String.format("{\"text\":\"%s\"}", messageContent);

                restClient.post()
                        .uri(slackWebhookUrl)
                        .header("Content-Type", "application/json")
                        .body(payload)
                        .retrieve();

                System.out.println("Message sent to Slack successfully. (Supplier)");

                return "Message sent successfully";
            } catch (Exception e) {
                System.out.println("Error alerting Slack: " + e.getMessage());
                return "Failed to send message";
            }
        };
    }

    @Bean
    public Function<Message, String> alertSlackChannelFunction() {
        return message -> {
            try {
                restClient.post()
                        .uri(slackWebhookUrl)
                        .header("Content-Type", "application/json")
                        .body(message)
                        .retrieve();
                System.out.println("Message sent to Slack successfully. (Function)");
                return "Message sent successfully (Function) - " + message.getText().toUpperCase();
            } catch (Exception e) {
                return "Failed to send message";
            }
        };
    }


}