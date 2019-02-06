package com.example.apphelper2019.ex.design_pattern.builder_pattern_ex;

/**
 * from https://www.youtube.com/watch?v=mFCk31FoUg4&list=PLZlGOBonMjFVq9FF0eiS_emcFmP-0fSBe&index=35
 */
public class Main {
    public static void main(String[] args) {
        GameBuilder gameBuilder = new GameBuilder()
                .homeTeam("test")
                .startDate();
        Game game = gameBuilder.build();
        game.startGame();
    }
}
