package com.gateway;

import com.gateway.notification.*;
import com.gateway.sender.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== 1. UrgentNotification + Telegram ===");
        TelegramMessageSender telegram = new TelegramMessageSender();
        SmsMessageSender sms = new SmsMessageSender();

        Notification urgentTelegram = new UrgentNotification(telegram, sms);
        urgentTelegram.send("alice_dev", "Server is down!");

        System.out.println("\n=== 2. EncryptedNotification + Email ===");
        EmailMessageSender email = new EmailMessageSender();
        Notification encryptedEmail = new EncryptedNotification(email);
        encryptedEmail.send("bob@company.com", "Confidential API Key: 12345");

        System.out.println("\n=== 3. ScheduledBatchNotification + SMS ===");
        ScheduledBatchNotification batchNotification = new ScheduledBatchNotification(
                sms, Arrays.asList("+77011234567", "+77029876543", "+77055555555")
        );
        batchNotification.sendBatch("System maintenance scheduled at midnight.");

        System.out.println("\n=== 4. Runtime Provider Switching (Telegram -> Email) ===");
        Notification flexibleNotification = new UrgentNotification(telegram, sms);
        System.out.println("Sending via Telegram:");
        flexibleNotification.send("charlie", "Check this update.");

        System.out.println("Switching provider to Email at runtime:");
        flexibleNotification.setSender(email);
        flexibleNotification.send("charlie@mail.com", "Check this update via email.");

        System.out.println("\n=== 5. Telegram unavailable -> Fallback to SMS ===");
        telegram.setAvailable(false); // Выключаем Telegram
        urgentTelegram.send("alice_dev", "Database response time is high!");

        System.out.println("\n=== 6. MarketingNotification + PushNotificationSender ===");
        PushNotificationSender pushSender = new PushNotificationSender();
        Notification marketingPush = new MarketingNotification(pushSender);
        marketingPush.send("user123", "Get 50% off on all software courses!");
    }
}