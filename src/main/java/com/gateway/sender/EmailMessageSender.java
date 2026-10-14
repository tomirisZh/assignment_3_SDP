package com.gateway.sender;

public class EmailMessageSender implements MessageSender {
    private boolean available = true;

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public boolean sendMessage(String recipient, String title, String body) {
        if (!isAvailable()) {
            System.out.println("[Email] Service is unavailable!");
            return false;
        }
        System.out.println("[Email] Sending Email to " + recipient + " | Title: " + title + " | Body: " + body + " [With Signature]");
        return true;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public String getChannelName() {
        return "Email";
    }
}