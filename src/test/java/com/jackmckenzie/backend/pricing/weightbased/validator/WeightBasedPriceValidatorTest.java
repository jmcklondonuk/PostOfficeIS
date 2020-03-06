package com.jackmckenzie.backend.pricing.weightbased.validator;

import com.jackmckenzie.backend.pricing.validator.WeightBasedPriceValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WeightBasedPriceValidatorTest {
    @Test
    void isValidWeight() {
        Assertions.assertFalse(WeightBasedPriceValidator.isValidWeight("0.00"));
        Assertions.assertFalse(WeightBasedPriceValidator.isValidWeight("0.000"));
        Assertions.assertFalse(WeightBasedPriceValidator.isValidWeight("0"));
        Assertions.assertFalse(WeightBasedPriceValidator.isValidWeight("0.0"));
        Assertions.assertFalse(WeightBasedPriceValidator.isValidWeight("."));
        Assertions.assertFalse(WeightBasedPriceValidator.isValidWeight(".1"));
        Assertions.assertFalse(WeightBasedPriceValidator.isValidWeight(".11"));
        Assertions.assertFalse(WeightBasedPriceValidator.isValidWeight(".111"));
        Assertions.assertFalse(WeightBasedPriceValidator.isValidWeight(".1111"));
        Assertions.assertTrue(WeightBasedPriceValidator.isValidWeight("1"));
        Assertions.assertFalse(WeightBasedPriceValidator.isValidWeight("1."));
        Assertions.assertTrue(WeightBasedPriceValidator.isValidWeight("1.1"));
        Assertions.assertTrue(WeightBasedPriceValidator.isValidWeight("1.11"));
        Assertions.assertTrue(WeightBasedPriceValidator.isValidWeight("1.111"));
        Assertions.assertFalse(WeightBasedPriceValidator.isValidWeight("1.1111"));
        Assertions.assertFalse(WeightBasedPriceValidator.isValidWeight("1.1110"));
    }

    @Test
    void isValidPrice() {
        Assertions.assertFalse(WeightBasedPriceValidator.isValidWeight("0.00"));
        Assertions.assertFalse(WeightBasedPriceValidator.isValidWeight("0.000"));
        Assertions.assertFalse(WeightBasedPriceValidator.isValidWeight("0"));
        Assertions.assertFalse(WeightBasedPriceValidator.isValidWeight("0.0"));
        Assertions.assertFalse(WeightBasedPriceValidator.isValidPrice("."));
        Assertions.assertFalse(WeightBasedPriceValidator.isValidPrice(".1"));
        Assertions.assertFalse(WeightBasedPriceValidator.isValidPrice("0.1"));
        Assertions.assertFalse(WeightBasedPriceValidator.isValidPrice("0.111"));
        Assertions.assertFalse(WeightBasedPriceValidator.isValidPrice("0.100"));
        Assertions.assertTrue(WeightBasedPriceValidator.isValidPrice("0.10"));
        Assertions.assertTrue(WeightBasedPriceValidator.isValidPrice("0.10"));
        Assertions.assertTrue(WeightBasedPriceValidator.isValidPrice("0.99"));
        Assertions.assertTrue(WeightBasedPriceValidator.isValidPrice("0.01"));
        Assertions.assertTrue(WeightBasedPriceValidator.isValidPrice("0.11"));
    }
}