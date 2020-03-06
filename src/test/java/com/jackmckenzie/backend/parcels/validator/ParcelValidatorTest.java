package com.jackmckenzie.backend.parcels.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ParcelValidatorTest {
    @Test
    void isValidPostCode() {
        Assertions.assertFalse(ParcelValidator.isValidPostCode("0000a"));
        Assertions.assertFalse(ParcelValidator.isValidPostCode("00000"));
        Assertions.assertFalse(ParcelValidator.isValidPostCode("1"));
        Assertions.assertFalse(ParcelValidator.isValidPostCode("000000"));
        Assertions.assertTrue(ParcelValidator.isValidPostCode("00001"));
        Assertions.assertTrue(ParcelValidator.isValidPostCode("99999"));
    }
}