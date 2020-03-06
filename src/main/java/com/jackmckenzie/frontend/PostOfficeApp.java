package com.jackmckenzie.frontend;

import com.jackmckenzie.frontend.commandframework.CommandProcessor;
import com.jackmckenzie.frontend.preprocessor.CommandLineArguments;
import com.jackmckenzie.frontend.view.MainView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Scanner;

@SpringBootApplication(scanBasePackages = "com.jackmckenzie")
@EnableScheduling
public class PostOfficeApp implements ApplicationRunner {
    private CommandLineArguments commandLineArguments;

    private CommandProcessor commandProcessor;

    private MainView mainView;

    private static volatile boolean printWarehouseListing = true;

    @Autowired
    public PostOfficeApp(CommandLineArguments commandLineArguments, CommandProcessor commandProcessor, MainView mainView) {
        this.commandLineArguments = commandLineArguments;
        this.commandProcessor = commandProcessor;
        this.mainView = mainView;
    }

    public static void notifyPrintWarehouseList() {
        printWarehouseListing = true;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (!commandLineArguments.process(args)) {
            System.err.println("Exiting due to invalid arguments...");
            System.exit(100);
        }

        mainView.printBanner();
        Scanner scanner = new Scanner(System.in);
        String input = null;

        while (true) {
            if (printWarehouseListing) {
                printWarehouseListing = false;
                mainView.printWarehouseList();
            }

            if (input != null) {
                if (!commandProcessor.execute(input)) {
                    System.err.println("Unknown command.");
                    mainView.printBanner();
                }
            }

            input = scanner.nextLine();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(PostOfficeApp.class, args);
    }
}
