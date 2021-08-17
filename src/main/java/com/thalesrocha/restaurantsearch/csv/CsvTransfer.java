package com.thalesrocha.restaurantsearch.csv;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThalesRocha
 */
public class CsvTransfer<T> {

    private List<T> csvList;

    public CsvTransfer() {
    }

    public List<T> getCsvList() {
        if (csvList != null)
            return csvList;

        return new ArrayList<T>();
    }

    public void setCsvList(List<T> csvList) {
        this.csvList = csvList;
    }

}
