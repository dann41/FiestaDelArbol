package com.dglapps.fiestadelarbol.repositories.parser.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CSVValue {

    @JsonProperty("v")
    String value;

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "CSVValue{" +
                "value='" + value + '\'' +
                '}';
    }
}
