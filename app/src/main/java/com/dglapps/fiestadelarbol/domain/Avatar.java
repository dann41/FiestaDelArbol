package com.dglapps.fiestadelarbol.domain;

public class Avatar {

    private final int id;
    private final int resourceId;

    public Avatar(int id, int resourceId) {
        this.id = id;
        this.resourceId = resourceId;
    }

    public int getId() {
        return id;
    }

    public int getResourceId() {
        return resourceId;
    }
}
