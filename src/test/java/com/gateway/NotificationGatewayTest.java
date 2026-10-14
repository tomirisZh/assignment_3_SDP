package com.gateway;

import com.gateway.notification.*;
import com.gateway.sender.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class NotificationGatewayTest {

    @Test
    void testSuccessfulDelivery() {
        TelegramMessageSender sender = new TelegramMessageSender();
        Notification notification = new UrgentNotification(sender, null);
        assertDoesNotThrow(() -> notification.send("user", "Test message"));
        assertTrue(sender.isAvailable());
    }

    @Test
    void testUnavailableProvider() {
        TelegramMessageSender sender = new TelegramMessageSender();
        sender.setAvailable(false);
        assertFalse(sender.isAvailable());
    }

    @Test
    void testFallbackMechanism() {
        TelegramMessageSender telegram = new TelegramMessageSender();
        telegram.setAvailable(false); // Делаем Telegram недоступным
        SmsMessageSender sms = new SmsMessageSender();

        UrgentNotification notification = new UrgentNotification(telegram, sms);
        assertDoesNotThrow(() -> notification.send("user", "Fallback test"));
    }

    @Test
    void testSmsLengthRestriction() {
        SmsMessageSender sms = new SmsMessageSender();
        String longMessage = "A".repeat(200);
        boolean result = sms.sendMessage("12345", "Title", longMessage);
        assertTrue(result);
    }

    @Test
    void testEncryptedMessage() {
        EmailMessageSender email = new EmailMessageSender();
        Notification encrypted = new EncryptedNotification(email);
        assertDoesNotThrow(() -> encrypted.send("test@mail.com", "Secret Data"));
    }

    @Test
    void testBatchNotification() {
        SmsMessageSender sms = new SmsMessageSender();
        ScheduledBatchNotification batch = new ScheduledBatchNotification(
                sms, Arrays.asList("111", "222")
        );
        assertDoesNotThrow(() -> batch.sendBatch("Batch message text"));
    }

    @Test
    void testRuntimeSenderSwitching() {
        TelegramMessageSender telegram = new TelegramMessageSender();
        EmailMessageSender email = new EmailMessageSender();

        Notification notification = new UrgentNotification(telegram, null);
        assertEquals("Telegram", notification.getSender().getChannelName());

        notification.setSender(email);
        assertEquals("Email", notification.getSender().getChannelName());
    }

    @Test
    void testNewPushNotificationSender() {
        PushNotificationSender push = new PushNotificationSender();
        Notification notification = new MarketingNotification(push);
        assertDoesNotUrlThrow:
        assertDoesNotThrow(() -> notification.send("user", "Push promo"));
        assertEquals("PushNotification", push.getChannelName());
    }

    @Test
    void testMarketingNotificationType() {
        KafkaEventSender kafka = new KafkaEventSender();
        Notification marketing = new MarketingNotification(kafka);
        assertDoesNotThrow(() -> marketing.send("analytics-topic", "New event lead"));
    }

    @Test
    void testFailureScenario() {
        TelegramMessageSender telegram = new TelegramMessageSender();
        telegram.setAvailable(false);
        Notification notification = new UrgentNotification(telegram, null);
        assertDoesNotThrow(() -> notification.send("user", "Will fail silently/gracefully"));
    }
}