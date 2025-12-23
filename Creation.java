import java.util.Random;

import javax.swing.JOptionPane;

public class Creation {
    /* La fonction main qui crée le tablier puis appele les fonctions pour disposer les joueurs et obstacles
        et enfin qui appele la fonction de la classe Arbitrage pour lancer la partie */
    public static void main(String[] args) {
        int[][] tablier = initialisationTablier();
        dispositionJoueurs(tablier);
        dispositionObstacles(tablier);
        int choix = unoudeux();
        Arbitre.deroulementPartie(tablier, choix);
    }

    /* Cette fonction crée le tablier en demandant aux joueurs la taille de ce tablier */

    public static int[][] initialisationTablier() {
        int longueur = Integer.valueOf(JOptionPane.showInputDialog("Quelle est la longueur du tablier ? Entre 3 et 25"));
        while (longueur <= 2 || longueur > 25) {
            longueur = Integer.valueOf(JOptionPane.showInputDialog("Erreur ! Quelle est la longueur du tablier ? Entre 3 et 25"));
        }
        int largeur = Integer.valueOf(JOptionPane.showInputDialog("Quelle est la largeur du tablier ? Entre 3 et 25"));
        while (largeur <= 2 || largeur > 25) {
            largeur = Integer.valueOf(JOptionPane.showInputDialog("Erreur ! Quelle est la largeur du tablier ? Entre 3 et 25"));
        }
        int[][] tablier = creationTablier(longueur, largeur);
        return tablier;
    }

    /* C'est la sous-fonction de al fonction précédente qui crée le tablier avec les dimensions données */

    public static int[][] creationTablier(int longueur, int largeur) {
        int[][] tablier = new int[largeur][longueur];
        for (int i = 0; i < tablier.length; i++) {
            for (int j = 0; j < tablier[0].length; j++) {
                tablier[i][j] = 0;
            }
        }
        return tablier;
    }

    /* Cette fonction dispose les joueurs dans les coins du plateau de jeu */

    public static void dispositionJoueurs(int[][] tablier) {
        tablier[0][0] = 1;
        tablier[tablier.length-1][tablier[0].length-1] = 2;
    }

    /* Cette fonction dispose les obstacles de manière aléatoire grace a la librairie java.util.random
        et le nombre d'obstacles varie en fonction de la taille du plateau */

    public static void dispositionObstacles(int[][] tablier) {
        if (tablier.length * tablier[0].length >= 12 && tablier.length * tablier[0].length < 36) {
            tablier[new Random().nextInt(tablier.length-2)+1][new Random().nextInt(tablier[0].length-2)+1] = 4;
        }
        else if (tablier.length * tablier[0].length >= 36 && tablier.length * tablier[0].length < 64) {
            while (scan(tablier,4) < 2) {
                tablier[new Random().nextInt(tablier.length-2)+1][new Random().nextInt(tablier[0].length-2)+1] = 4;
            }
        }
        else if (tablier.length * tablier[0].length >= 64) {
            while (scan(tablier,4) < 8) {
                tablier[new Random().nextInt(tablier.length-2)+1][new Random().nextInt(tablier[0].length-2)+1] = 4;
            }
        }
    }

    /* Cette fonction permet de savoir si les obstacles se sont bien tous disposés sur le plateau */

    public static int scan(int[][] tablier, int bloc) {
        int compte = 0;
        for (int i = 0; i < tablier.length; i++) {
            for (int j = 0; j < tablier[0].length; j++) {
                if (tablier[i][j] == bloc) {
                    compte++;
                }
            }
        }
        return compte;
    }

    /* Cette fonction demande à l'utilisateur combien de joueurs vont jouer, afin de savoir s'il faut appeler l'IA ou non */

    public static int unoudeux() {
        int nbJoueurs = Integer.valueOf(JOptionPane.showInputDialog("Combien y a-t-il de joueurs ? 1 ou 2 ?"));
        while (nbJoueurs <= 0 || nbJoueurs > 2) {
            nbJoueurs = Integer.valueOf(JOptionPane.showInputDialog("Erreur ! Combien y a-t-il de joueurs ? 1 ou 2 ?"));
        }
        if (nbJoueurs == 1) {
            int ordeJoueur = Integer.valueOf(JOptionPane.showInputDialog("Quel joueur voulez-vous être ? 1 ou 2 ?"));
            while (ordeJoueur <= 0 || ordeJoueur > 2) {
                ordeJoueur = Integer.valueOf(JOptionPane.showInputDialog("Erreur ! Quel joueur voulez-vous être ? 1 ou 2 ?"));
            }
            return ordeJoueur;
        }
        else {
            return 3;
        }
    }
}