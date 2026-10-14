package com.gateway.sender;

public class SmsMessageSender implements MessageSender {
    private boolean available = true;

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public boolean sendMessage(String recipient, String title, String body) {
        if (!isAvailable()) {
            System.out.println("[SMS] Service is unavailable!");
            return false;
        }
        String finalBody = body;
        if (finalBody.length() > 160) {
            finalBody = finalBody.substring(0, 157) + "...";
            System.out.println("[SMS] Warning: Message truncated to 160 characters.");
        }
        System.out.println("[SMS] Sending SMS to " + recipient + ": " + finalBody);
        return true;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public String getChannelName() {
        return "SMS";
    }
}