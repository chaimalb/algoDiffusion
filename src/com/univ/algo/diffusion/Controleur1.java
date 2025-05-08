package com.univ.algo.diffusion;

import java.util.List;

public class Controleur1 implements Runnable {
    private List<State> questionList;
    private int questionNo = 0; // Compteur de questions

    public Controleur1(List<State> questionList) {
        this.questionList = questionList;
    }

    public void send(State question) throws InterruptedException {
        synchronized (questionList) {
            // Attendre si la liste est pleine (ex: limite � 5 questions)
            while (questionList.size() == 5) {
                System.out.println("Too many questions! Waiting for answers...");
                questionList.wait();
            }
        }

        synchronized (questionList) {
            // Ajouter une question
            Thread.sleep(1000); // Simule un d�lai de production
            questionList.add(question);
            System.out.println("New Question: " + question);
            questionList.notify(); // Notifie le Consumer
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                send(new State("Question message "+questionNo++, "node1")); // Example usage
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Producer interrupted!");
                break;
            }
        }
    }
}

