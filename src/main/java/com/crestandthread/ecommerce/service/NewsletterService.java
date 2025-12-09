// AI Generated Code by Deloitte + Cursor (BEGIN)
package com.crestandthread.ecommerce.service;

import com.crestandthread.ecommerce.dto.NewsletterSubscriptionDTO;

/**
 * Service interface for Newsletter operations.
 */
public interface NewsletterService {

    NewsletterSubscriptionDTO subscribe(String email);

    void unsubscribe(String email);

    boolean isSubscribed(String email);

    long getSubscriptionCount();
}
// AI Generated Code by Deloitte + Cursor (END)