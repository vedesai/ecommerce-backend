// AI Generated Code by Deloitte + Cursor (BEGIN)
package com.crestandthread.ecommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for Newsletter Subscription.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsletterSubscriptionDTO {

    private Long id;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;

    private Boolean active;

    private LocalDateTime subscribedAt;
}
// AI Generated Code by Deloitte + Cursor (END)