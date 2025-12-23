import javax.swing.JOptionPane;

public class Arbitre {

    /* C'est la fonction principale qui s'occupe du bon déroulement de la partie en appelant toutes les sous-fonctions nécessaires */

    public static void deroulementPartie(int[][] tablier, int choix) {
        Picture pic = new Picture((tablier[0].length)*10, (tablier.length)*10);
        pic.draw();
        int k = 0;
        int l = 0;
        int joueur1 = 1;
        int joueur2 = 2;
        int findepartie = 0;
        Affichage.afficheTablier(tablier);
        Affichage.affichageJoli(tablier, 10, pic, k, l);
        for (int i = 0; i < tablier.length * tablier[0].length; i++) {
            if (i%2 == 0) {
                if (verification(tablier, joueur1, joueur2) == false) {
                    i = tablier.length * tablier[0].length + 1;
                    findepartie = 2;
                }
                else {
                    if (choix == joueur1 || choix == 3) {
                        demandeCoup(tablier, joueur1, joueur2);
                    }
                    else {
                        IA.demandeIA(tablier, joueur1, joueur2, i);
                    }
                }
            }
            else {
                if (verification(tablier, joueur2, joueur1) == false) {
                    i = tablier.length * tablier[0].length + 1;
                    findepartie = 1;
                }
                else {
                    if (choix == joueur2 || choix == 3) {
                        demandeCoup(tablier, joueur2, joueur1);
                    }
                    else {
                        IA.demandeIA(tablier, joueur2, joueur1, i);
                    }
                }
            }
            Affichage.afficheTablier(tablier);
            Affichage.modifJoli(tablier, 10, pic, k, l);
        }
        if (findepartie == 1) {
            Affichage.afficheGagnant(joueur1);
        }
        else {
            Affichage.afficheGagnant(joueur2);
        }
    }

    /* Cette fonction permet de connaitre la ligne sur laquelle se trouve un joueur */

    public static int ligneJoueur(int joueur, int[][] tablier) {
        for (int i = 0; i < tablier.length; i++) {
            for (int j = 0; j < tablier[0].length; j++) {
                if (tablier[i][j] == joueur) {
                    return i;
                }
            }
        }
        return 0;
    }

    /* Cette fonction permet de connaitre la colonne sur laquelle se trouve un joueur */

    public static int colonneJoueur(int joueur, int[][] tablier) {
        for (int i = 0; i < tablier.length; i++) {
            for (int j = 0; j < tablier[0].length; j++) {
                if (tablier[i][j] == joueur) {
                    return j;
                }
            }
        }
        return 0;
    }

    /* Cette fonction vérifie si le joueur a bien des coups valides */

    public static boolean verification(int[][] tablier, int joueur, int adversaire) {
        if (coupvalide("U", tablier, joueur, adversaire) == false
        && coupvalide("D", tablier, joueur, adversaire) == false
        && coupvalide("L", tablier, joueur, adversaire) == false
        && coupvalide("R", tablier, joueur, adversaire) == false) {
            return false;
        }
        else {
            return true;
        }
    }

    /* Cette fonction demande au joueur quel coup il veut faire puis appele la fonction qui execute le coup */
    
    public static void demandeCoup(int[][] tablier, int joueur, int adversaire) {
        String coup;
        coup = JOptionPane.showInputDialog("Joueur " + joueur + " : Quel est votre coup ? U (Up) ? D (Down) ? L (Left) ? R (Right) ? ");
        while (coupvalide(coup, tablier, joueur, adversaire) == false) {
            coup = JOptionPane.showInputDialog("Erreur ! Joueur " + joueur + 
            " : Quel est votre coup ? U (Up) ? D (Down) ? L (Left) ? R (Right) ? ");
        }
        effectueCoup(coup, tablier, joueur);
    }

    /* Cette fonction vérifie que le coup proposé plus haut est bien valide et une fonction différente
       sera appelé en fonction du coup */

    public static boolean coupvalide(String coup, int[][] tablier, int joueur, int adversaire) {
        if (coupvalideError(coup) == false) {
            return false;
        }
        else if (coup.equals("U")) {
            return coupvalideUp(tablier, joueur, adversaire);
        }
        else if (coup.equals("D")) {
            return coupvalideDown(tablier, joueur, adversaire);
        }
        else if (coup.equals("L")) {
            return coupvalideLeft(tablier, joueur, adversaire);
        }
        else {
            return coupvalideRight(tablier, joueur, adversaire);
        }
    }

    /* Cette fonction vérifie que le coup donné fait bien partie des coups possibles */

    public static boolean coupvalideError(String coup) {
        if (coup.equals("U") == false && coup.equals("D") == false 
        && coup.equals("L") == false && coup.equals("R") == false) {
            return false;
        }
        else {
            return true;
        }
    }

    /* Cette fonction vérifie que le coup vers le haut est valide */

    public static boolean coupvalideUp(int[][] tablier, int joueur, int adversaire) {
        if (ligneJoueur(joueur, tablier)-1 < 0) {
            return false;
        }
        else {
            if (tablier[ligneJoueur(joueur, tablier)-1][colonneJoueur(joueur, tablier)] == 3
            || tablier[ligneJoueur(joueur, tablier)-1][colonneJoueur(joueur, tablier)] == adversaire
            || tablier[ligneJoueur(joueur, tablier)-1][colonneJoueur(joueur, tablier)] == 4) {
                return false;
            }
            else {
                return true;
            }
        }
    }

    /* Cette fonction vérifie que le coup vers le bas est valide */

    public static boolean coupvalideDown(int[][] tablier, int joueur, int adversaire) {
        if (ligneJoueur(joueur, tablier)+1 >= tablier.length) {
            return false;
        }
        else {
            if (tablier[ligneJoueur(joueur, tablier)+1][colonneJoueur(joueur, tablier)] == 3
            || tablier[ligneJoueur(joueur, tablier)+1][colonneJoueur(joueur, tablier)] == adversaire
            || tablier[ligneJoueur(joueur, tablier)+1][colonneJoueur(joueur, tablier)] == 4) {
                return false;
            }
            else {
                return true;
            }
        }
    }

    /* Cette fonction vérifie que le coup vers la gauche est valide */

    public static boolean coupvalideLeft(int[][] tablier, int joueur, int adversaire) {
        if (colonneJoueur(joueur, tablier)-1 < 0) {
            return false;
        }
        else {
            if (tablier[ligneJoueur(joueur, tablier)][colonneJoueur(joueur, tablier)-1] == 3
            || tablier[ligneJoueur(joueur, tablier)][colonneJoueur(joueur, tablier)-1] == adversaire
            || tablier[ligneJoueur(joueur, tablier)][colonneJoueur(joueur, tablier)-1] == 4) {
                return false;
            }
            else {
                return true;
            }
        }
    }

    /* Cette fonction vérifie que le coup vers la droite est valide */

    public static boolean coupvalideRight(int[][] tablier, int joueur, int adversaire) {
        if (colonneJoueur(joueur, tablier)+1 >= tablier[0].length) {
            return false;
        }
        else {
            if (tablier[ligneJoueur(joueur, tablier)][colonneJoueur(joueur, tablier)+1] == 3
            || tablier[ligneJoueur(joueur, tablier)][colonneJoueur(joueur, tablier)+1] == adversaire
            || tablier[ligneJoueur(joueur, tablier)][colonneJoueur(joueur, tablier)+1] == 4) {
                return false;
            }
            else {
                return true;
            }
        }
    }

    /* Cette fonction execute le coup qui est forcément valide car vérifié plus tôt.
        En fonction du coup, ce sera une fonction différente qui sera appelé */

    public static void effectueCoup(String coup,int[][] tablier, int joueur) {
        if (coup.equals("U")) {
            effectueCoupUp(tablier, joueur);
        }
        else if (coup.equals("D")) {
            effectueCoupDown(tablier, joueur);
        }
        else if (coup.equals("L")) {
            effectueCoupLeft(tablier, joueur);
        }
        else {
            effectueCoupRight(tablier, joueur);
        }
    }

    /* Cette fonction effectue le coup vers le haut */

    public static void effectueCoupUp(int[][] tablier, int joueur) {
        int x = ligneJoueur(joueur, tablier);
        tablier[ligneJoueur(joueur, tablier)-1][colonneJoueur(joueur, tablier)] = joueur;
        tablier[x][colonneJoueur(joueur, tablier)] = 3;
    }

    /* Cette fonction effectue le coup vers le bas */

    public static void effectueCoupDown(int[][] tablier, int joueur) {
        int x = ligneJoueur(joueur, tablier);
        tablier[ligneJoueur(joueur, tablier)+1][colonneJoueur(joueur, tablier)] = joueur;
        tablier[x][colonneJoueur(joueur, tablier)] = 3;
    }

    /* Cette fonction effectue le coup vers la gauche */

    public static void effectueCoupLeft(int[][] tablier, int joueur) {
        int y = colonneJoueur(joueur, tablier);
        tablier[ligneJoueur(joueur, tablier)][colonneJoueur(joueur, tablier)-1] = joueur;
        tablier[ligneJoueur(joueur, tablier)][y] = 3;
    }

    /* Cette fonction effectue le coup vers la droite */

    public static void effectueCoupRight(int[][] tablier, int joueur) {
        int y = colonneJoueur(joueur, tablier);
        tablier[ligneJoueur(joueur, tablier)][colonneJoueur(joueur, tablier)+1] = joueur;
        tablier[ligneJoueur(joueur, tablier)][y] = 3;
    }
}
