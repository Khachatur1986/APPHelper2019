package com.example.apphelper2019.ex.db_ex.todo_ex.model;

public class ToDo {
    private long id;
    private String toDo;

    public ToDo() {
    }

    public ToDo(long id, String toDo) {
        this.id = id;
        this.toDo = toDo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToDo() {
        return toDo;
    }

    public void setToDo(String toDo) {
        this.toDo = toDo;
    }
}
