package com.univ.algo.diffusion;

public class State {
    private String message;
    private String name;
    private String status; // Nouvel attribut pour consigner l'état des contrôleurs

    // Constructeur
    public State(String message, String name) {
        this.message = message;
        this.name = name;
        this.status = "En attente"; // Valeur par défaut
    }

    // Constructeur avec statut
    public State(String message, String name, String status) {
        this.message = message;
        this.name = name;
        this.status = status;
    }

    // Getters et Setters
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "State{" +
                "message='" + message + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

