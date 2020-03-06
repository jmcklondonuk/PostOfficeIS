package com.jackmckenzie.backend.parcels.entity;

import java.math.BigDecimal;

public class ParcelAggregation implements Comparable<ParcelAggregation> {
    private BigDecimal totalWeight;
    private String postCode;

    public BigDecimal getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(BigDecimal totalWeight) {
        this.totalWeight = totalWeight;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    @Override
    public int compareTo(ParcelAggregation parcelAggregation) {
        return parcelAggregation.getTotalWeight().compareTo(totalWeight);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ParcelAggregation) {
            ParcelAggregation aggregation = (ParcelAggregation)o;
            return aggregation.postCode.equals(postCode);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return postCode.hashCode();
    }
}
