package com.univ.algo.diffusion;

import java.util.List; // Correction de l'import

public class Superviseur implements Runnable {
    List<State> questionList;
    private int messageCount = 0;
    private long startTime = System.currentTimeMillis();
    private Runnable nextNode;

    public Superviseur(List<State> questionList) {
        this.questionList = questionList;
    }

    public void setNextNode(Runnable nextNode) {
        this.nextNode = nextNode;
    }

    public void receive() throws InterruptedException { // Correction de l'exception
        synchronized (questionList) {
            while (questionList.isEmpty()) {
                System.out.println("No Question to Answer ... Waiting for controlers to get question");
                questionList.wait();
            }
        }

        synchronized (questionList) {
            Thread.sleep(5000);
            State controleurState = questionList.remove(0);
            controleurState.setStatus("Traité par Superviseur"); // Mise à jour du statut
            messageCount++;
            long elapsedTime = System.currentTimeMillis() - startTime;
            System.out.println("Superviseur ANSWERED Question of Controleur: Name = " + controleurState.getName() + ", Message = " + controleurState.getMessage() + " | Status: " + controleurState.getStatus());
            System.out.println("Messages processed: " + messageCount);
            System.out.println("Elapsed time: " + elapsedTime + " ms");
            questionList.notify();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Réception et traitement d'un message par le superviseur
                receive();

                synchronized (questionList) {
                    // Passage au nœud suivant dans la topologie en anneau
                    if (nextNode != null) {
                        nextNode.run();
                    }
                }

                // Affichage en temps réel des statistiques
                System.out.println("[Temps réel] Messages traités: " + messageCount + ", Temps écoulé: " + (System.currentTimeMillis() - startTime) + " ms");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Superviseur interrupted!");
                break;
            }
        }
    }
}






















