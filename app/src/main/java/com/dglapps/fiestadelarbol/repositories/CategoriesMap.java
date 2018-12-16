package com.dglapps.fiestadelarbol.repositories;

import com.dglapps.fiestadelarbol.services.CategoryService;

import java.util.HashMap;
import java.util.Map;

public class CategoriesMap {

    private final Map<String, Integer> categoriesMap;

    public CategoriesMap() {
        categoriesMap = new HashMap<>();
        categoriesMap.put("RAYO", CategoryService.QUICK);
        categoriesMap.put("MUSICAL", CategoryService.MUSIC);
        categoriesMap.put("V-F", CategoryService.TRUE_FALSE);
        categoriesMap.put("ABIERTA", CategoryService.QUESTION);
        categoriesMap.put("HABILIDAD", CategoryService.SKILL);
    }

    public Integer get(String category) {
        return categoriesMap.get(category);
    }
}
