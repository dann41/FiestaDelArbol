package com.dglapps.fiestadelarbol.domain;

public class Category {

    private final int id;
    private final int iconId;
    private final int colorId;
    private final int nameId;
    private final boolean answerRequired;
    private final boolean repetitionAllowed;
    private final boolean allPlay;


    public Category(int id, int iconId, int colorId, int nameId,
                    boolean answerRequired, boolean repetitionAllowed, boolean allPlay) {
        this.id = id;
        this.iconId = iconId;
        this.colorId = colorId;
        this.nameId = nameId;
        this.answerRequired = answerRequired;
        this.repetitionAllowed = repetitionAllowed;
        this.allPlay = allPlay;
    }

    public int getId() {
        return id;
    }

    public int getIconId() {
        return iconId;
    }

    public int getColorId() {
        return colorId;
    }

    public int getNameId() {
        return nameId;
    }

    public boolean isAnswerRequired() {
        return answerRequired;
    }

    public boolean isRepetitionAllowed() {
        return repetitionAllowed;
    }

    public boolean isAllPlay() {
        return allPlay;
    }

}
