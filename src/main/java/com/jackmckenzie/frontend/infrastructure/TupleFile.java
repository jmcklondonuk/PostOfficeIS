package com.jackmckenzie.frontend.infrastructure;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.function.Consumer;

public class TupleFile {
    public static void read(String fileName, Consumer<String[]> consumer) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            long line = 1;
            String text;
            while ((text = bufferedReader.readLine()) != null) {
                String[] values = text.split(" ");
                if (values.length != 2) {
                    throwException(fileName, line, "expected 2 values, found " + values.length);
                    return;
                }
                try {
                    consumer.accept(values);
                } catch (InputMismatchException e) {
                    throwException(fileName, line, e.getMessage());
                }

                line++;
            }
        }
    }

    private static void throwException(String fileName, long line, String message) throws IOException {
        throw new IOException("File is corrupt: '" + fileName + "' line " + line + ": " + message);
    }
}
