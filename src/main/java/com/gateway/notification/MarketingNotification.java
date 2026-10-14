package com.gateway.notification;

import com.gateway.sender.MessageSender;

public class MarketingNotification extends Notification {

    public MarketingNotification(MessageSender sender) {
        super(sender);
    }

    @Override
    public void send(String recipient, String message) {
        String promoMessage = "SPECIAL OFFER: " + message + " Use code: BRIDGE2026";
        sender.sendMessage(recipient, "Exclusive Promo!", promoMessage);
    }
}