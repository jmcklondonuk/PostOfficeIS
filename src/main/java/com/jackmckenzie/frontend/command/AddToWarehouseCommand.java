package com.jackmckenzie.frontend.command;

import com.jackmckenzie.backend.parcels.WarehouseService;
import com.jackmckenzie.backend.parcels.entity.Parcel;
import com.jackmckenzie.frontend.commandframework.CommandBase;
import com.jackmckenzie.frontend.commandframework.CommandRegExp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

@Component
public class AddToWarehouseCommand implements CommandBase {
    private WarehouseService warehouseService;

    @Autowired
    public AddToWarehouseCommand(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @CommandRegExp("[0-9]{1,10}(\\.[0-9]{1,3})? ([0-9]{5})")
    @Override
    public void execute(String input) {
        try (Scanner parcelScanner = new Scanner(input)) {
            Parcel parcel = new Parcel();
            parcel.setWeight(parcelScanner.nextBigDecimal());
            parcel.setPostCode(parcelScanner.next());

            List<String> result = warehouseService.add(parcel);
            if (!result.isEmpty()) {
                System.err.println("Invalid input: " + String.join(", ", result));
            } else
                System.out.println("Parcel added");
        } catch (NoSuchElementException e) {
            System.err.println("Invalid input. Try again.");
        }
    }
}
