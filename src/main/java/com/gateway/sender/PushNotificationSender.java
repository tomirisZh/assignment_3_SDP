package com.gateway.sender;

public class PushNotificationSender implements MessageSender {
    private boolean available = true;

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public boolean sendMessage(String recipient, String title, String body) {
        if (!isAvailable()) {
            System.out.println("[Push] Service is unavailable!");
            return false;
        }
        System.out.println("[Push] Sending Push Notification to device of " + recipient + " -> " + title + ": " + body);
        return true;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public String getChannelName() {
        return "PushNotification";
    }
}