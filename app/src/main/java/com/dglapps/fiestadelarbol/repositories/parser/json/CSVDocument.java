package com.dglapps.fiestadelarbol.repositories.parser.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CSVDocument {

    CSVTable table;

    public CSVTable getTable() {
        return table;
    }
}
