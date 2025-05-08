package com.jobready.producerconsumer;

import java.util.List;

public class Producer implements Runnable {
    private List<Integer> questionList;
    private int questionNo = 0; // Compteur de questions

    public Producer(List<Integer> questionList) {
        this.questionList = questionList;
    }

    public void addQuestion(int questionNo) throws InterruptedException {
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
            questionList.add(questionNo);
            System.out.println("New Question: " + questionNo);
            questionList.notify(); // Notifie le Consumer
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                addQuestion(questionNo++); // Incrémente le numéro de question
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Producer interrupted!");
                break;
            }
        }
    }
}

