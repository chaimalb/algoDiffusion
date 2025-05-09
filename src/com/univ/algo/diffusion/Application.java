package com.univ.algo.diffusion;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        // Liste partagée pour stocker les messages échangés entre les nœuds
        List<State> questionList = new ArrayList<>();

        // Création des nœuds de la topologie en anneau
        Controleur1 controleur1 = new Controleur1(questionList);
        Controleur2 controleur2 = new Controleur2(questionList);
        Superviseur superviseur = new Superviseur(questionList);

        // Configuration de la topologie en anneau : chaque nœud connaît son successeur
        controleur1.setNextNode(controleur2);
        controleur2.setNextNode(superviseur);
        superviseur.setNextNode(controleur1);

        // Création et démarrage des threads pour chaque nœud
        Thread t1 = new Thread(controleur1);
        Thread t2 = new Thread(controleur2);
        Thread t3 = new Thread(superviseur);

        t1.start();
        t2.start();
        t3.start();
    }
}
