package com.silviucanton.bugreportingsystem.domain;

public class UpdateMessage {
    private String name;

    public UpdateMessage() {
    }

    public UpdateMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
