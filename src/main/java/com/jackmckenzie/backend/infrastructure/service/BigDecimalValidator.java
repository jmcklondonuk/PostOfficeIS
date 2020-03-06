package com.jackmckenzie.backend.infrastructure.service;

import java.math.BigDecimal;

public class BigDecimalValidator {
    public static boolean isBigDecimalGreaterThanZero(String bigDecimalString) {
        return new BigDecimal(bigDecimalString).compareTo(BigDecimal.ZERO) > 0;
    }
}
