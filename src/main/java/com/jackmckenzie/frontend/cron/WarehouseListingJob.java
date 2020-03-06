package com.jackmckenzie.frontend.cron;

import com.jackmckenzie.frontend.PostOfficeApp;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WarehouseListingJob {
    private static final long ONE_MINUTE_IN_MS = 60_000;

    @Scheduled(fixedRate = ONE_MINUTE_IN_MS)
    private void listingNotificationTrigger() {
        PostOfficeApp.notifyPrintWarehouseList();
    }
}
