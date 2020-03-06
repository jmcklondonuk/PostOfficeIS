package com.jackmckenzie.backend.parcels.service;

import com.jackmckenzie.backend.parcels.WarehouseService;
import com.jackmckenzie.backend.parcels.entity.Parcel;
import com.jackmckenzie.backend.parcels.entity.ParcelAggregation;
import com.jackmckenzie.backend.parcels.validator.ParcelValidator;
import com.jackmckenzie.frontend.infrastructure.TupleFile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Service
public class ParcelWarehouseServiceImpl implements WarehouseService {
    private SortedSet<ParcelAggregation> sortedAggregationSet = new TreeSet<>();
    private Map<String, ParcelAggregation> postCodeIndex = new HashMap<>();

    @Override
    public List<String> add(Parcel parcel) {
        List<String> errors = parcel.validate();
        if (errors.isEmpty()) {
            ParcelAggregation aggregation = postCodeIndex.get(parcel.getPostCode());
            if (aggregation == null) {
                aggregation = new ParcelAggregation();
                aggregation.setPostCode(parcel.getPostCode());
                aggregation.setTotalWeight(parcel.getWeight());
                sortedAggregationSet.add(aggregation);
                postCodeIndex.put(aggregation.getPostCode(), aggregation);
            } else {
                sortedAggregationSet.remove(aggregation);
                aggregation.setTotalWeight(aggregation.getTotalWeight().add(parcel.getWeight()));
                sortedAggregationSet.add(aggregation);
            }
        }

        return errors;
    }

    @Override
    public SortedSet<ParcelAggregation> list() {
        return sortedAggregationSet;
    }

    @Override
    public void load(String fileName) throws IOException {
        TupleFile.read(fileName, values -> {
            if (!ParcelValidator.isValidWeight(values[0]))
                throwException("invalid weight '" + values[0] + "'");

            if (!ParcelValidator.isValidPostCode(values[1]))
                throwException("invalid post code '" + values[1] + "'");

            Parcel parcel = new Parcel();
            parcel.setWeight(new BigDecimal(values[0]));
            parcel.setPostCode(values[1]);

            List<String> errors = add(parcel);
            if (!errors.isEmpty()) {
                sortedAggregationSet.clear();
                throw new InputMismatchException(String.join(", ", errors));
            }
        });
    }

    private void throwException(String message) {
        sortedAggregationSet.clear();
        throw new InputMismatchException(message);
    }
}
