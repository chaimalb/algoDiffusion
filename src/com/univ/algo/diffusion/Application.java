package com.univ.algo.diffusion;
import java.util.ArrayList; 
import java.util.List;

public class Application {
    public static void main(String[] args) {
        List<State> questionList = new ArrayList<State>();
        Thread t1 = new Thread(new Controleur1(questionList)); 
        Thread t2 = new Thread(new Superviseur(questionList)); 
        Thread t3 = new Thread(new Controleur2(questionList));

        t1.start();
        t2.start();
        t3.start();
    }
}
