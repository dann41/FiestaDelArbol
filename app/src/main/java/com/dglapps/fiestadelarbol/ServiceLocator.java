package com.dglapps.fiestadelarbol;

import com.dglapps.fiestadelarbol.services.*;

public final class ServiceLocator {

    private static ServiceLocator instance;
    private final PlayerService playerService;
    private final AvatarService avatarService;
    private final CategoryService categoryService;
    private final GameService gameService;
    private final QuestionService questionService;

    public static ServiceLocator getInstance() {
        if (instance == null) {
            instance = new ServiceLocator();
        }
        return instance;
    }

    private ServiceLocator() {
        playerService = new PlayerService();
        avatarService = new AvatarService();
        categoryService = new CategoryService();
        gameService = new GameService();
        questionService = new QuestionService();
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

    public GameService getGameService() {
        return gameService;
    }

    public QuestionService getQuestionService() {
        return questionService;
    }
}
