package com.univ.algo.diffusion;

import java.util.List;

public class Controleur1 implements Runnable {
    private List<State> questionList;
    private int questionNo = 0; // Compteur de questions
    private Runnable nextNode;

    public Controleur1(List<State> questionList) {
        this.questionList = questionList;
    }

    public void setNextNode(Runnable nextNode) {
        this.nextNode = nextNode;
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
            question.setStatus("Envoyé par Controleur1"); // Mise à jour du statut
            questionList.add(question);
            System.out.println("Controleur1 sent: " + question.getMessage() + " | Status: " + question.getStatus());
            questionList.notify(); // Notifie le Consumer
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Création d'un nouveau message avec un statut initial
                State question = new State("Question message " + questionNo++, "Controleur1", "En cours de traitement");
                send(question); // Envoi du message au nœud suivant

                // Passage au nœud suivant dans la topologie en anneau
                if (nextNode != null) {
                    nextNode.run();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Controleur1 interrupted!");
                break;
            }
        }
    }
}

