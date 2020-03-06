package com.jackmckenzie.backend.parcels.entity;

import com.jackmckenzie.backend.parcels.validator.ParcelValidator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Parcel {
    private BigDecimal weight;
    private String postCode;

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public List<String> validate() {
        List<String> errors = new ArrayList<>();
        if (!ParcelValidator.isValidPostCode(postCode))
            errors.add("Invalid post code.");

        if (!ParcelValidator.isValidWeight(weight.toString()))
            errors.add("Invalid weight.");

        return errors;
    }
}
