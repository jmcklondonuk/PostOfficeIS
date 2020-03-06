package com.jackmckenzie.backend.parcels;

import com.jackmckenzie.backend.parcels.entity.Parcel;
import com.jackmckenzie.backend.parcels.entity.ParcelAggregation;

import java.io.IOException;
import java.util.List;
import java.util.SortedSet;

public interface WarehouseService {
    List<String> add(Parcel parcel);

    SortedSet<ParcelAggregation> list();

    void load(String fileName) throws IOException;
}
