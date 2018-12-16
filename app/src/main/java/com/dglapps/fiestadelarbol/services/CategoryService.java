package com.dglapps.fiestadelarbol.services;

import com.dglapps.fiestadelarbol.R;
import com.dglapps.fiestadelarbol.domain.Category;

import java.util.HashMap;
import java.util.Map;

public class CategoryService {

    private static final int QUICK = 0;
    private static final int TRUE_FALSE = 1;
    private static final int SKILL = 2;
    private static final int QUESTION = 3;
    private static final int MUSIC = 4;

    private final int[] SEQUENCE = new int[] {
            TRUE_FALSE, MUSIC, QUICK, QUESTION, SKILL,
            MUSIC, TRUE_FALSE, QUESTION, SKILL,
            QUICK, QUESTION, MUSIC, TRUE_FALSE,
            TRUE_FALSE, SKILL, QUESTION, MUSIC,
            QUICK, QUESTION, MUSIC
    };


    private final Map<Integer, Category> categoryMap = new HashMap<>();

    private int i = -1;

    public CategoryService() {
        categoryMap.put(
                QUICK,
                new Category(
                    QUICK,
                    R.drawable.quick,
                    R.color.colorQuick,
                    R.string.quick,
                    true, false, true)
        );
        categoryMap.put(
                TRUE_FALSE,
                new Category(
                        TRUE_FALSE,
                        R.drawable.true_false,
                        R.color.colorTrueFalse,
                        R.string.true_false,
                        true, false, false)
        );

        categoryMap.put(
                SKILL,
                new Category(
                        SKILL,
                        R.drawable.skills,
                        R.color.colorSkill,
                        R.string.skill,
                        false, false, true)
        );

        categoryMap.put(
                QUESTION,
                new Category(
                        QUESTION,
                        R.drawable.question,
                        R.color.colorQuestion,
                        R.string.question,
                        true, false, false)
        );

        categoryMap.put(
                MUSIC,
                new Category(
                        MUSIC,
                        R.drawable.music,
                        R.color.colorMusic,
                        R.string.music,
                        false, true, false)
        );
    }

    public Category findById(int categoryId) {
        return categoryMap.get(categoryId);
    }

    public Category getRandomCategory() {
        i = (i + 1) % SEQUENCE.length;
        return findById(SEQUENCE[i]);
    }
}
