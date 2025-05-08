package com.jobready.producerconsumer;

import java.util.List; // Correction de l'import

public class Consumer implements Runnable {
    List<Integer> questionList;

    public Consumer(List<Integer> questionList) {
        this.questionList = questionList;
    }

    public void answerQuestion() throws InterruptedException { // Correction de l'exception
        synchronized (questionList) {
            while (questionList.isEmpty()) {
                System.out.println("No Question to Answer ... Waiting for producer to get question");
                questionList.wait();
            }
        }

        synchronized (questionList) {
            Thread.sleep(5000);
            System.out.println("ANSWERED Question: " + questionList.remove(0));
            questionList.notify();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                answerQuestion(); // Appel de la méthode correcte
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Bonne pratique pour les interruptions
                System.out.println("Consumer interrupted!");
                break;
            }
        }
    }
}




	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

