// AI Generated Code by Deloitte + Cursor (BEGIN)
package com.crestandthread.ecommerce.service;

import com.crestandthread.ecommerce.dto.NewsletterSubscriptionDTO;
import com.crestandthread.ecommerce.entity.NewsletterSubscription;
import com.crestandthread.ecommerce.exception.DuplicateResourceException;
import com.crestandthread.ecommerce.exception.ResourceNotFoundException;
import com.crestandthread.ecommerce.mapper.NewsletterMapper;
import com.crestandthread.ecommerce.repository.NewsletterSubscriptionRepository;
import com.crestandthread.ecommerce.service.impl.NewsletterServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NewsletterServiceTest {

    @Mock
    private NewsletterSubscriptionRepository subscriptionRepository;

    @Mock
    private NewsletterMapper newsletterMapper;

    @InjectMocks
    private NewsletterServiceImpl newsletterService;

    private NewsletterSubscription subscription;
    private NewsletterSubscriptionDTO subscriptionDTO;

    @BeforeEach
    void setUp() {
        subscription = new NewsletterSubscription();
        subscription.setId(1L);
        subscription.setEmail("test@example.com");
        subscription.setActive(true);
        subscription.setSubscribedAt(LocalDateTime.now());

        subscriptionDTO = NewsletterSubscriptionDTO.builder()
                .id(1L)
                .email("test@example.com")
                .active(true)
                .subscribedAt(LocalDateTime.now())
                .build();
    }

    @Nested
    @DisplayName("subscribe")
    class Subscribe {

        @Test
        @DisplayName("should subscribe new email successfully")
        void shouldSubscribeNewEmailSuccessfully() {
            String email = "new@example.com";
            when(subscriptionRepository.existsByEmailAndActiveTrue(email)).thenReturn(false);
            when(subscriptionRepository.findByEmail(email)).thenReturn(Optional.empty());
            when(subscriptionRepository.save(any(NewsletterSubscription.class))).thenReturn(subscription);
            when(newsletterMapper.toDTO(subscription)).thenReturn(subscriptionDTO);

            NewsletterSubscriptionDTO result = newsletterService.subscribe(email);

            assertThat(result).isNotNull();
            verify(subscriptionRepository).save(any(NewsletterSubscription.class));
        }

        @Test
        @DisplayName("should throw exception when email already subscribed")
        void shouldThrowExceptionWhenEmailAlreadySubscribed() {
            String email = "active@example.com";
            when(subscriptionRepository.existsByEmailAndActiveTrue(email)).thenReturn(true);

            assertThatThrownBy(() -> newsletterService.subscribe(email))
                    .isInstanceOf(DuplicateResourceException.class)
                    .hasMessageContaining("already exists");
        }
    }

    @Nested
    @DisplayName("unsubscribe")
    class Unsubscribe {

        @Test
        @DisplayName("should unsubscribe email successfully")
        void shouldUnsubscribeEmailSuccessfully() {
            String email = "test@example.com";
            when(subscriptionRepository.findByEmail(email)).thenReturn(Optional.of(subscription));
            when(subscriptionRepository.save(any(NewsletterSubscription.class))).thenReturn(subscription);

            newsletterService.unsubscribe(email);

            assertThat(subscription.getActive()).isFalse();
            verify(subscriptionRepository).save(subscription);
        }

        @Test
        @DisplayName("should throw exception when email not found")
        void shouldThrowExceptionWhenEmailNotFound() {
            String email = "notfound@example.com";
            when(subscriptionRepository.findByEmail(email)).thenReturn(Optional.empty());

            assertThatThrownBy(() -> newsletterService.unsubscribe(email))
                    .isInstanceOf(ResourceNotFoundException.class);
        }
    }

    @Nested
    @DisplayName("isSubscribed")
    class IsSubscribed {

        @Test
        @DisplayName("should return true when email is subscribed")
        void shouldReturnTrueWhenEmailIsSubscribed() {
            String email = "test@example.com";
            when(subscriptionRepository.existsByEmailAndActiveTrue(email)).thenReturn(true);

            boolean result = newsletterService.isSubscribed(email);

            assertThat(result).isTrue();
        }
    }
}
// AI Generated Code by Deloitte + Cursor (END)