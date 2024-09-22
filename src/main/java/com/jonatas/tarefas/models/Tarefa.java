package com.jonatas.tarefas.models;

public class Tarefa {

    private int id;
    private String title;
    private String body;
    private boolean done;

    
    public Tarefa() {
    }


    public Tarefa(int id, String title, String body, boolean done) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.done = done;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getBody() {
        return body;
    }


    public void setBody(String body) {
        this.body = body;
    }


    public boolean isDone() {
        return done;
    }


    public void setDone(boolean done) {
        this.done = done;
    }

    
}
