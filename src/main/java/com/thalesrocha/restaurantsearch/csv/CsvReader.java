package com.thalesrocha.restaurantsearch.csv;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by ThalesRocha
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class CsvReader <T> {

    public static <T> List<T> from(String fileName, Class clazz) throws IOException {
        InputStream inputStream = CsvReader.class.getResourceAsStream("/"+fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        }
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

        CsvTransfer csvTransfer = new CsvTransfer();
        ColumnPositionMappingStrategy<Object> ms = new ColumnPositionMappingStrategy<>();
        ms.setType(clazz);

        CsvToBean cb = new CsvToBeanBuilder(reader).withSeparator(',')
                .withType(clazz)
                .withOrderedResults(true)
                .withIgnoreLeadingWhiteSpace(true)
                .withThrowExceptions(false)
                .build();

        csvTransfer.setCsvList(cb.parse());

        final List<CsvException> capturedExceptions = cb.getCapturedExceptions();
        capturedExceptions.forEach(e -> {
            System.out.printf("Inconsistent data: %s - %s%n", e.getLineNumber(), e.getMessage());
        });

        reader.close();
        return csvTransfer.getCsvList();
    }


}
