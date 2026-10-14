# Assignment 3 — Multi-Channel Notification Gateway (Bridge Pattern)

## Architecture Overview
This project implements the **Bridge Design Pattern** to decouple the **Abstraction** (Notification types) from the **Implementor** (Message delivery providers/channels).

### Answers to Mandatory Questions:
1. **What are the two independent dimensions?**
    - Dimension 1: Notification Types (Urgent, Encrypted, ScheduledBatch, Marketing).
    - Dimension 2: Delivery Channels/Providers (Telegram, Email, SMS, Kafka, PushNotification).
2. **Where exactly is the Bridge?**
    - The bridge is established through the protected reference field `protected MessageSender sender;` inside the abstract `Notification` class, pointing to the `MessageSender` interface.
3. **Why is inheritance problematic here?**
    - Without Bridge, combining $N$ notification types and $M$ providers leads to a combinatorial explosion of subclasses ($N \times M$ classes like `UrgentTelegramNotification`, `EncryptedEmailNotification`, etc.). Inheritance tightly couples them.
4. **What happens when a new provider is added?**
    - We simply implement the `MessageSender` interface (e.g., `PushNotificationSender`). Existing notification classes remain entirely unmodified (Open/Closed Principle).
5. **What happens when a new Notification type is added?**
    - We extend the abstract `Notification` class (e.g., `MarketingNotification`). It can immediately work with any existing or future `MessageSender` implementation.
6. **Why is this Bridge rather than Adapter?**
    - **Adapter** is used to make incompatible existing classes work together by wrapping them. **Bridge** is designed upfront to let abstractions and implementations vary independently along separate hierarchies.

## UML Class Diagram
![UML Diagram](images\uml-diagram.png")