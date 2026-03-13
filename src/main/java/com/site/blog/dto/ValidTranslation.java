package com.site.blog.dto;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TranslationValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTranslation {
    String message() default "Invalid translation format. Expected JSON with language keys containing 'title' and 'content'.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
