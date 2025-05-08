package com.univ.algo.diffusion;

public class State {
    private String message;
    private String name;

    // Constructor
    public State(String message, String name) {
        this.message = message;
        this.name = name;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "State{" +
                "message='" + message + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

