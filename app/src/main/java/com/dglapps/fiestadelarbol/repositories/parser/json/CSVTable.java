package com.dglapps.fiestadelarbol.repositories.parser.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CSVTable {

    List<CSVRow> rows;

    public List<CSVRow> getRows() {
        return rows;
    }
}
