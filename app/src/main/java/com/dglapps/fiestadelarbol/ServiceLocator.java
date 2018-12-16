package com.dglapps.fiestadelarbol;

import com.dglapps.fiestadelarbol.services.AvatarService;
import com.dglapps.fiestadelarbol.services.CategoryService;
import com.dglapps.fiestadelarbol.services.PlayerService;

public final class ServiceLocator {

    private static ServiceLocator instance;
    private final PlayerService playerService;
    private final AvatarService avatarService;
    private final CategoryService categoryService;

    public static ServiceLocator getInstance() {
        if (instance == null) {
            instance = new ServiceLocator();
        }
        return instance;
    }

    private ServiceLocator() {
        this.playerService = new PlayerService();
        this.avatarService = new AvatarService();
        this.categoryService = new CategoryService();
    }

    public PlayerService getPlayerService() {
        return playerService;
    }

    public AvatarService getAvatarService() {
        return avatarService;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }
}
