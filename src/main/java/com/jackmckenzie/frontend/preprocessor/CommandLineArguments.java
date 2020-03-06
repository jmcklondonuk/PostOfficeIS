package com.jackmckenzie.frontend.preprocessor;

import com.jackmckenzie.backend.parcels.WarehouseService;
import com.jackmckenzie.backend.pricing.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class CommandLineArguments {
    private PricingService pricingService;

    private WarehouseService warehouseService;

    private enum Options {
        prices,
        parcels
    }

    @Autowired
    public CommandLineArguments(PricingService pricingService, WarehouseService warehouseService) {
        this.pricingService = pricingService;
        this.warehouseService = warehouseService;
    }

    public boolean process(ApplicationArguments args) {
        Set<String> options = new HashSet<>(Options.values().length);
        Arrays.stream(Options.values()).forEach(option -> options.add(option.name().toLowerCase()));

        if (args.getSourceArgs().length > 2 || !args.getNonOptionArgs().isEmpty() || !options.containsAll(args.getOptionNames())) {
            System.err.println("Invalid arguments! Supported are --prices=prices.txt --parcels=list.txt");
            return false;
        }

        assert Options.values().length == 2;
        if (args.getOptionNames().size() == 2 && args.getOptionNames().toArray()[0].equals(args.getOptionNames().toArray()[1])) {
            System.err.println("Invalid arguments! The same argument is defined twice.");
            return false;
        }

        if (args.containsOption(Options.prices.name())) {
            try {
                pricingService.load(args.getOptionValues(Options.prices.name()).get(0));
            } catch (IOException e) {
                System.err.println(e.getMessage() + " Prices won't be available.");
            }
        }

        if (args.containsOption(Options.parcels.name())) {
            try {
                warehouseService.load(args.getOptionValues(Options.parcels.name()).get(0));
            } catch (IOException e) {
                System.err.println(e.getMessage() + " Warehouse will start empty.");
            }
        }

        return true;
    }
}
