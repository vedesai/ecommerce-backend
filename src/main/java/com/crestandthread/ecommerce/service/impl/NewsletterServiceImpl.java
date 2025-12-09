// AI Generated Code by Deloitte + Cursor (BEGIN)
package com.crestandthread.ecommerce.service.impl;

import com.crestandthread.ecommerce.dto.NewsletterSubscriptionDTO;
import com.crestandthread.ecommerce.entity.NewsletterSubscription;
import com.crestandthread.ecommerce.exception.DuplicateResourceException;
import com.crestandthread.ecommerce.exception.ResourceNotFoundException;
import com.crestandthread.ecommerce.mapper.NewsletterMapper;
import com.crestandthread.ecommerce.repository.NewsletterSubscriptionRepository;
import com.crestandthread.ecommerce.service.NewsletterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NewsletterServiceImpl implements NewsletterService {

    private final NewsletterSubscriptionRepository subscriptionRepository;
    private final NewsletterMapper newsletterMapper;

    @Override
    @Transactional
    public NewsletterSubscriptionDTO subscribe(String email) {
        if (subscriptionRepository.existsByEmailAndActiveTrue(email)) {
            throw new DuplicateResourceException("Newsletter subscription", "email", email);
        }

        NewsletterSubscription subscription = subscriptionRepository.findByEmail(email)
                .map(existing -> {
                    existing.setActive(true);
                    return existing;
                })
                .orElseGet(() -> {
                    NewsletterSubscription newSubscription = new NewsletterSubscription();
                    newSubscription.setEmail(email);
                    newSubscription.setActive(true);
                    return newSubscription;
                });

        NewsletterSubscription savedSubscription = subscriptionRepository.save(subscription);
        return newsletterMapper.toDTO(savedSubscription);
    }

    @Override
    @Transactional
    public void unsubscribe(String email) {
        NewsletterSubscription subscription = subscriptionRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Newsletter subscription", "email", email));

        subscription.setActive(false);
        subscriptionRepository.save(subscription);
    }

    @Override
    public boolean isSubscribed(String email) {
        return subscriptionRepository.existsByEmailAndActiveTrue(email);
    }

    @Override
    public long getSubscriptionCount() {
        return subscriptionRepository.countByActiveTrue();
    }
}
// AI Generated Code by Deloitte + Cursor (END)