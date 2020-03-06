package com.jackmckenzie.backend.pricing;

import java.io.IOException;
import java.math.BigDecimal;

public interface PricingService {
    BigDecimal getPriceByWeight(BigDecimal totalWeight);

    boolean isPricingLoaded();

    void load(String fileName) throws IOException;
}
