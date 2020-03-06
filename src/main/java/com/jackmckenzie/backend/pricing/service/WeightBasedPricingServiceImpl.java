package com.jackmckenzie.backend.pricing.service;

import com.jackmckenzie.backend.pricing.PricingService;
import com.jackmckenzie.backend.pricing.validator.WeightBasedPriceValidator;
import com.jackmckenzie.frontend.infrastructure.TupleFile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.NavigableMap;
import java.util.TreeMap;

@Service
public class WeightBasedPricingServiceImpl implements PricingService {
    private NavigableMap<BigDecimal, BigDecimal> weightPriceMap = new TreeMap<>();

    @Override
    public BigDecimal getPriceByWeight(BigDecimal weight) {
        BigDecimal priceListWeight = weightPriceMap.floorKey(weight);
        if (priceListWeight == null)
            priceListWeight = weightPriceMap.firstKey();

        return weightPriceMap.get(priceListWeight);
    }

    @Override
    public boolean isPricingLoaded() {
        return weightPriceMap.size() > 0;
    }

    @Override
    public void load(String fileName) throws IOException {
        TupleFile.read(fileName, values -> {
            if (!WeightBasedPriceValidator.isValidWeight(values[0]))
                throwException("invalid weight '" + values[0] + "'");

            if (!WeightBasedPriceValidator.isValidPrice(values[1]))
                throwException("invalid price '" + values[1] + "'");

            weightPriceMap.put(new BigDecimal(values[0]), new BigDecimal(values[1]));
        });
    }

    private void throwException(String message) {
        weightPriceMap.clear();
        throw new InputMismatchException(message);
    }
}
