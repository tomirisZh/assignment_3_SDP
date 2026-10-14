package com.gateway.notification;

import com.gateway.sender.MessageSender;
import java.util.List;

public class ScheduledBatchNotification extends Notification {
    private List<String> recipients;

    public ScheduledBatchNotification(MessageSender sender, List<String> recipients) {
        super(sender);
        this.recipients = recipients;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    @Override
    public void send(String recipient, String message) {
        sender.sendMessage(recipient, "Scheduled Batch", message);
    }

    public void sendBatch(String message) {
        int total = recipients.size();
        int successful = 0;
        int failed = 0;

        for (String rec : recipients) {
            boolean res = sender.sendMessage(rec, "Batch Notification", message);
            if (res) {
                successful++;
            } else {
                failed++;
            }
        }

        System.out.println("\n--- Batch Summary ---");
        System.out.println("Total: " + total);
        System.out.println("Successful: " + successful);
        System.out.println("Failed: " + failed);
        System.out.println("---------------------\n");
    }
}