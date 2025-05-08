package com.univ.algo.diffusion;

import java.util.List; // Correction de l'import

public class Superviseur implements Runnable {
    List<State> questionList;

    public Superviseur(List<State> questionList) {
        this.questionList = questionList;
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
            System.out.println("Superviseur ANSWERED Question of Controleur: Name = " + controleurState.getName() + ", Message = " + controleurState.getMessage());
            questionList.notify();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                receive(); // Appel de la m�thode correcte
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Bonne pratique pour les interruptions
                System.out.println("Consumer interrupted!");
                break;
            }
        }
    }
}






















