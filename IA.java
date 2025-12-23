public class IA {

    /* Cette fonction appele une autre fonction selon si l'IA est joueur 1 ou 2 */

    public static void demandeIA(int[][] tablier, int joueur, int adversaire, int tour) {
        if (joueur == 1) {
            coupJoueur1(tour, tablier, joueur, adversaire);
        }
        else {
            coupJoueur2(tour, tablier, joueur, adversaire);
        }
    }

    /* Cette fonction s'occupe du coup de l'IA en joueur 1 */

    public static void coupJoueur1(int tour, int[][] tablier, int joueur, int adversaire) {
        if (tour%2 == 0) {
            String coup = "D";
            jeuIA(coup, tablier, joueur, adversaire);
        }
        else {
            String coup = "R";
            jeuIA(coup, tablier, joueur, adversaire);
        }
    }

    /* Cette fonction s'occupe du coup de l'IA en joueur 2 */

    public static void coupJoueur2(int tour, int[][] tablier, int joueur, int adversaire) {
        if (tour%2 == 0) {
            String coup = "U";
            jeuIA(coup, tablier, joueur, adversaire);
        }
        else {
            String coup = "L";
            jeuIA(coup, tablier, joueur, adversaire);
        }
    }

    /* Cette fonction s'occupe du jeu de l'IA en vérifiant quel coup elle peut faire et selon la fonction
        qui l'appelle précédemment */

    public static void jeuIA(String coup, int[][] tablier, int joueur, int adversaire) {
        if (Arbitre.coupvalide(coup, tablier, joueur, adversaire)) {
            Arbitre.effectueCoup(coup, tablier, joueur);
        }
        else if (coup.equals("U")) {
            upIA(tablier, joueur, adversaire);
        }
        else if (coup.equals("L")) {
            leftIA(tablier, joueur, adversaire);
        }
        else if (coup.equals("D")) {
            downIA(tablier, joueur, adversaire);
        }
        else {
            rightIA(tablier, joueur, adversaire);
        }
    }

    /* Cette fonction effectue le coup si c'est le coup vers le haut qui est censé être executé */

    public static void upIA(int[][] tablier, int joueur, int adversaire) {
        if (Arbitre.coupvalide("L", tablier, joueur, adversaire)) {
            Arbitre.effectueCoup("L", tablier, joueur);
        }
        else if (Arbitre.coupvalide("D", tablier, joueur, adversaire)) {
            Arbitre.effectueCoup("D", tablier, joueur);
        }
        else {
            Arbitre.effectueCoup("R", tablier, joueur);
        }
    }

    /* Cette fonction effectue le coup si c'est le coup vers la gauche qui est censé être executé */

    public static void leftIA(int[][] tablier, int joueur, int adversaire) {
        if (Arbitre.coupvalide("U", tablier, joueur, adversaire)) {
            Arbitre.effectueCoup("U", tablier, joueur);
        }
        else if (Arbitre.coupvalide("R", tablier, joueur, adversaire)) {
            Arbitre.effectueCoup("R", tablier, joueur);
        }
        else {
            Arbitre.effectueCoup("D", tablier, joueur);
        }
    }

    /* Cette fonction effectue le coup si c'est le coup vers le bas qui est censé être executé */

    public static void downIA(int[][] tablier, int joueur, int adversaire) {
        if (Arbitre.coupvalide("R", tablier, joueur, adversaire)) {
            Arbitre.effectueCoup("R", tablier, joueur);
        }
        else if (Arbitre.coupvalide("U", tablier, joueur, adversaire)) {
            Arbitre.effectueCoup("U", tablier, joueur);
        }
        else {
            Arbitre.effectueCoup("L", tablier, joueur);
        }
    }

    /* Cette fonction effectue le coup si c'est le coup vers la droite qui est censé être executé */

    public static void rightIA(int[][] tablier, int joueur, int adversaire) {
        if (Arbitre.coupvalide("D", tablier, joueur, adversaire)) {
            Arbitre.effectueCoup("D", tablier, joueur);
        }
        else if (Arbitre.coupvalide("L", tablier, joueur, adversaire)) {
            Arbitre.effectueCoup("L", tablier, joueur);
        }
        else {
            Arbitre.effectueCoup("U", tablier, joueur);
        }
    }
}
