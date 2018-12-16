package com.dglapps.fiestadelarbol.services;

import com.dglapps.fiestadelarbol.R;
import com.dglapps.fiestadelarbol.domain.Category;

import java.util.HashMap;
import java.util.Map;

public class CategoryService {

    public static final int QUICK = 0;
    public static final int TRUE_FALSE = 1;
    public static final int SKILL = 2;
    public static final int QUESTION = 3;
    public static final int MUSIC = 4;


    private final Map<Integer, Category> categoryMap = new HashMap<>();

    public CategoryService() {
        categoryMap.put(
                QUICK,
                new Category(
                    QUICK,
                    R.drawable.quick,
                    R.color.colorQuick,
                    R.string.quick
                )
        );
        categoryMap.put(
                TRUE_FALSE,
                new Category(
                        TRUE_FALSE,
                        R.drawable.true_false,
                        R.color.colorTrueFalse,
                        R.string.true_false
                )
        );

        categoryMap.put(
                SKILL,
                new Category(
                        SKILL,
                        R.drawable.skills,
                        R.color.colorSkill,
                        R.string.skill
                )
        );

        categoryMap.put(
                QUESTION,
                new Category(
                        QUESTION,
                        R.drawable.question,
                        R.color.colorQuestion,
                        R.string.quick
                )
        );

        categoryMap.put(
                MUSIC,
                new Category(
                        MUSIC,
                        R.drawable.music,
                        R.color.colorMusic,
                        R.string.music
                )
        );
    }

    public Category findById(int categoryId) {
        return categoryMap.get(categoryId);
    }
}
