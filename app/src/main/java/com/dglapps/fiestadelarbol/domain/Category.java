package com.dglapps.fiestadelarbol.domain;

public class Category {

    private final int id;
    private final int iconId;
    private final int colorId;
    private final int nameId;


    public Category(int id, int iconId, int colorId, int nameId) {
        this.id = id;
        this.iconId = iconId;
        this.colorId = colorId;
        this.nameId = nameId;
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
}
