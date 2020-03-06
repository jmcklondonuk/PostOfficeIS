package com.jackmckenzie.frontend.view;

import com.jackmckenzie.backend.parcels.WarehouseService;
import com.jackmckenzie.backend.pricing.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainView {
    private PricingService pricingService;

    private WarehouseService warehouseService;

    @Autowired
    public MainView(PricingService pricingService, WarehouseService warehouseService) {
        this.pricingService = pricingService;
        this.warehouseService = warehouseService;
    }

    public void printBanner() {
        System.out.println("Post Office Warehouse");
        System.out.println("---------------------");
        System.out.println("Note: every minute, parcels in the system will be printed below.");
        System.out.println("1) Enter [kg] parcel weight followed by a recipient post code (i.e. \"3.4 08801\"), or");
        System.out.println("2) Type 'quit' (without apostrophes) to exit");
    }

    public void printWarehouseList() {
        System.out.println("Warehouse listing:");
        if (pricingService.isPricingLoaded()) {
            warehouseService.list().forEach(
                    aggregation ->
                            System.out.printf("%s %.3f %.2f%n",
                                    aggregation.getPostCode(),
                                    aggregation.getTotalWeight(),
                                    pricingService.getPriceByWeight(aggregation.getTotalWeight())
                            )
            );
        } else
            warehouseService.list().forEach(
                    aggregation -> System.out.printf("%s %.3f",
                            aggregation.getPostCode(),
                            aggregation.getTotalWeight()
                    )
            );
    }
}
