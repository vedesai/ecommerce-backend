// AI Generated Code by Deloitte + Cursor (BEGIN)
package com.crestandthread.ecommerce.mapper;

import com.crestandthread.ecommerce.dto.NewsletterSubscriptionDTO;
import com.crestandthread.ecommerce.entity.NewsletterSubscription;
import org.springframework.stereotype.Component;

@Component
public class NewsletterMapper {

    public NewsletterSubscriptionDTO toDTO(NewsletterSubscription subscription) {
        if (subscription == null) {
            return null;
        }

        return NewsletterSubscriptionDTO.builder()
                .id(subscription.getId())
                .email(subscription.getEmail())
                .active(subscription.getActive())
                .subscribedAt(subscription.getSubscribedAt())
                .build();
    }

    public NewsletterSubscription toEntity(NewsletterSubscriptionDTO dto) {
        if (dto == null) {
            return null;
        }

        NewsletterSubscription subscription = new NewsletterSubscription();
        subscription.setEmail(dto.getEmail());
        subscription.setActive(dto.getActive() != null ? dto.getActive() : true);
        
        return subscription;
    }
}
// AI Generated Code by Deloitte + Cursor (END)