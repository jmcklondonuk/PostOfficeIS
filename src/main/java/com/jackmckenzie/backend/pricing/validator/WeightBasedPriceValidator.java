package com.jackmckenzie.backend.pricing.validator;

import com.jackmckenzie.backend.infrastructure.service.BigDecimalValidator;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class WeightBasedPriceValidator {
    private static final Pattern WEIGHT_PATTERN = Pattern.compile("^[0-9]{1,20}(\\.[0-9]{1,3})?$");
    private static final Pattern PRICE_PATTERN = Pattern.compile("^[0-9]{1,10}(\\.[0-9]{2})?$");

    public static boolean isValidWeight(String weight) {
        return WEIGHT_PATTERN.matcher(weight).matches()
                && BigDecimalValidator.isBigDecimalGreaterThanZero(weight);
    }

    public static boolean isValidPrice(String price) {
        return PRICE_PATTERN.matcher(price).matches()
                && BigDecimalValidator.isBigDecimalGreaterThanZero(price);
    }
}
