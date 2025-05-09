# Programme de Topologie en Anneau

Ce programme implémente une topologie en anneau où plusieurs nœuds (contrôleurs et superviseur) échangent des messages de manière circulaire. Le superviseur traite les messages, enregistre le nombre de messages traités et le temps écoulé, puis relance le cycle.

## Prérequis

- **Java** : Assurez-vous que Java est installé sur votre machine. Vous pouvez vérifier la version de Java avec la commande suivante :

```bash
java -version
```

## Structure du Projet

Le projet est organisé comme suit :

```
src/
  com/univ/algo/diffusion/
    Application.java
    Controleur1.java
    Controleur2.java
    Superviseur.java
    State.java
```

## Étapes pour exécuter le programme

1. **Compilation** :
   - Ouvrez un terminal et placez-vous dans le répertoire racine du projet (là où se trouve le dossier `src`).
   - Compilez les fichiers Java avec la commande suivante :

```bash
javac -d bin src/com/univ/algo/diffusion/*.java
```

   - Les fichiers compilés seront placés dans le dossier `bin`.

2. **Exécution** :
   - Exécutez le programme avec la commande suivante :

```bash
java -cp bin com.univ.algo.diffusion.Application
```

3. **Résultat** :
   - Le programme affichera dans la console les messages échangés entre les nœuds, le nombre de messages traités et le temps écoulé.

## Fonctionnement

- **Contrôleurs** :
  - Les contrôleurs créent des messages et les transmettent au nœud suivant dans la topologie en anneau.

- **Superviseur** :
  - Le superviseur reçoit les messages, les traite, enregistre les statistiques (nombre de messages et temps écoulé), puis relance le cycle en notifiant le premier contrôleur.

## Exemple de Sortie

```
Controleur1 sent: Question message 0
Controleur2 received: Question message 0
Controleur2 sent: Processed by Controleur2
Superviseur received: Processed by Controleur2
Superviseur ANSWERED Question of Controleur: Name = Controleur2, Message = Processed by Controleur2
Messages processed: 1
Elapsed time: 5000 ms
```

## Auteur

Ce programme a été développé pour démontrer le fonctionnement d'une topologie en anneau en Java.
