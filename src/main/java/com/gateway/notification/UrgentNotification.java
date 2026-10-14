package com.gateway.notification;

import com.gateway.sender.MessageSender;

public class UrgentNotification extends Notification {
    private MessageSender fallbackSender;

    public UrgentNotification(MessageSender sender, MessageSender fallbackSender) {
        super(sender);
        this.fallbackSender = fallbackSender;
    }

    public void setFallbackSender(MessageSender fallbackSender) {
        this.fallbackSender = fallbackSender;
    }

    @Override
    public void send(String recipient, String message) {
        String urgentMessage = "[URGENT] " + message;
        boolean success = false;

        if (sender != null && sender.isAvailable()) {
            success = sender.sendMessage(recipient, "Urgent Notice", urgentMessage);
        }

        if (!success && fallbackSender != null) {
            System.out.println("[UrgentNotification] Primary sender (" + (sender != null ? sender.getChannelName() : "None") +
                    ") is unavailable. Switching to fallback: " + fallbackSender.getChannelName());
            fallbackSender.sendMessage(recipient, "Urgent Notice (Fallback)", urgentMessage);
        }
    }
}