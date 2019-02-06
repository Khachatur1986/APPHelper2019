package com.example.apphelper2019.ex.design_pattern.builder_pattern_ex;

import java.util.Date;

public class GameBuilder {
    private String homeTeam;
    private Date startDate;

    public GameBuilder homeTeam(String name){
        homeTeam = name;
        return this;
    }
    public GameBuilder startDate(){
        startDate = new Date();
        return this;
    }

    public Game build() {
        if (homeTeam == null){
            throw  new IllegalStateException("Missing home team");
        }
        return new Game(this);
    }
}
