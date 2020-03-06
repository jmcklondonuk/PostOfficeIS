package com.jackmckenzie.backend.parcels.validator;

import com.jackmckenzie.backend.infrastructure.service.BigDecimalValidator;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ParcelValidator {
    private static final Pattern WEIGHT_PATTERN = Pattern.compile("^[0-9]{1,10}(\\.[0-9]{1,3})?$");
    private static final Pattern POST_CODE_PATTERN = Pattern.compile("^([0-9]{5})$");

    public static boolean isValidWeight(String parcelWeight) {
        return WEIGHT_PATTERN.matcher(parcelWeight).matches()
                && BigDecimalValidator.isBigDecimalGreaterThanZero(parcelWeight);
    }

    public static boolean isValidPostCode(String postCode) {
        return POST_CODE_PATTERN.matcher(postCode).matches()
                && !"00000".equals(postCode);
    }
}
