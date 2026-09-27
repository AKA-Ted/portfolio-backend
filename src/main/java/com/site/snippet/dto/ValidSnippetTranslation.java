package com.site.snippet.dto;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SnippetTranslationValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSnippetTranslation {
    String message() default "Invalid translation format. Expected JSON with language keys containing 'description' and 'code'.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
