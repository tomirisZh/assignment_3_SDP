package com.gateway.sender;

public class KafkaEventSender implements MessageSender {
    private boolean available = true;

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public boolean sendMessage(String recipient, String title, String body) {
        if (!isAvailable()) {
            System.out.println("[Kafka] Service is unavailable!");
            return false;
        }
        System.out.println("[Kafka] Publishing event for recipient " + recipient + " | Payload: " + body);
        return true;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public String getChannelName() {
        return "Kafka";
    }
}