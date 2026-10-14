package com.gateway.notification;

import com.gateway.sender.MessageSender;
import java.util.Base64;

public class EncryptedNotification extends Notification {

    public EncryptedNotification(MessageSender sender) {
        super(sender);
    }

    @Override
    public void send(String recipient, String message) {
        String encrypted = Base64.getEncoder().encodeToString(message.getBytes());
        System.out.println("[EncryptedNotification] Encrypted message payload.");
        sender.sendMessage(recipient, "Secure Message", encrypted);
    }
}