package com.univ.algo.diffusion;

import java.util.List;

public class Controleur2 implements Runnable {
    private List<State> questionList;
    private Runnable nextNode;

    public Controleur2(List<State> questionList) {
        this.questionList = questionList;
    }

    public void setNextNode(Runnable nextNode) {
        this.nextNode = nextNode;
    }

    public void send(State question) throws InterruptedException {
        synchronized (questionList) {
            // Attendre si la liste est pleine (ex: limite à 5 questions)
            while (questionList.size() == 5) {
                System.out.println("Too many questions! Waiting for answers...");
                questionList.wait();
            }
        }

        synchronized (questionList) {
            // Ajouter une question
            Thread.sleep(1000); // Simule un délai de production
            question.setStatus("Envoyé par Controleur2"); // Mise à jour du statut
            questionList.add(question);
            System.out.println("Controleur2 sent: " + question.getMessage() + " | Status: " + question.getStatus());
            questionList.notify(); // Notifie le Consumer
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (questionList) {
                    // Attente d'un message dans la liste partagée
                    while (questionList.isEmpty()) {
                        questionList.wait();
                    }
                    // Réception d'un message par le contrôleur 2
                    State question = questionList.remove(0);
                    question.setStatus("Reçu par Controleur2"); // Mise à jour du statut
                    System.out.println("Controleur2 received: " + question.getMessage() + " | Status: " + question.getStatus());

                    // Traitement du message et envoi au nœud suivant
                    send(new State("Processed by Controleur2", "Controleur2", "En cours de traitement"));

                    // Passage au nœud suivant dans la topologie en anneau
                    if (nextNode != null) {
                        nextNode.run();
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Controleur2 interrupted!");
                break;
            }
        }
    }
}
