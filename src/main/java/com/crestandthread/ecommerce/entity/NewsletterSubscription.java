// AI Generated Code by Deloitte + Cursor (BEGIN)
package com.crestandthread.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Entity representing a newsletter subscription.
 */
@Entity
@Table(name = "newsletter_subscriptions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsletterSubscription extends BaseEntity {

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false)
    @Builder.Default
    private Boolean active = true;

    @Column(name = "subscribed_at", nullable = false)
    @Builder.Default
    private LocalDateTime subscribedAt = LocalDateTime.now();

    @Column(name = "unsubscribed_at")
    private LocalDateTime unsubscribedAt;

    @Column(length = 50)
    private String source;

    /**
     * Unsubscribe from the newsletter.
     */
    public void unsubscribe() {
        this.active = false;
        this.unsubscribedAt = LocalDateTime.now();
    }

    /**
     * Resubscribe to the newsletter.
     */
    public void resubscribe() {
        this.active = true;
        this.unsubscribedAt = null;
        this.subscribedAt = LocalDateTime.now();
    }
}
// AI Generated Code by Deloitte + Cursor (END)