package com.dglapps.fiestadelarbol.repositories.parser.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CSVRow {

    @JsonProperty("c")
    List<CSVValue> cells;

    public List<CSVValue> getCells() {
        return cells;
    }

    @Override
    public String toString() {
        return "CSVRow{" +
                "cells=" + cells +
                '}';
    }
}
