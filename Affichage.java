public class Affichage {

    /* Cette fonction affiche le tablier dans la console */

    public static void afficheTablier(int[][] tab) {
        System.out.println();
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[0].length; j++) {
                System.out.print(tab[i][j]);
            }
            System.out.println();
        }
    }

    /* Cette fonction affiche le gagnant */

    public static void afficheGagnant(int joueur) {
        System.out.println();
        System.out.println("Le joueur " + joueur + " a remporté la victoire");
    }

    /* Cette fonction affiche le tablier avec des pixels grace aux fichiers 
        Canvas.java, Color.java, Picture.java et Shape.java du TM8 */

    public static void affichageJoli(int[][] tablier, int taille, Picture pic, int k, int l) {
        for (int i = 0; i < tablier.length; i++) {
            for (int j = 0; j < tablier[0].length; j++) {
                if (tablier[i][j] == 0) {
                    for (int m = k; m < k+taille; m++) {
                        for (int n = l; n < l+taille; n++) {
                            pic.setColorAt(n, m, Color.GRAY);
                        }
                    }
                }
                else if (tablier[i][j] == 1) {
                    for (int m = k; m < k+taille; m++) {
                        for (int n = l; n < l+taille; n++) {
                            pic.setColorAt(n, m, Color.BLUE);
                        }
                    }
                }
                else if (tablier[i][j] == 2) {
                    for (int m = k; m < k+taille; m++) {
                        for (int n = l; n < l+taille; n++) {
                            pic.setColorAt(n, m, Color.RED);
                        }
                    }
                }
                else if (tablier[i][j] == 3) {
                    for (int m = k; m < k+taille; m++) {
                        for (int n = l; n < l+taille; n++) {
                            pic.setColorAt(n, m, Color.GREEN);
                        }
                    }
                }
                else if (tablier[i][j] == 4) {
                    for (int m = k; m < k+taille; m++) {
                        for (int n = l; n < l+taille; n++) {
                            pic.setColorAt(n, m, Color.BLACK);
                        }
                    }
                }
                l+=taille;
            }
            k+=taille;
            l = 0;
        }
    }

    /* Cette fonction modifie l'affichage pixels */

    public static void modifJoli(int[][] tablier, int taille, Picture pic, int k, int l) {
        for (int i = 0; i < tablier.length; i++) {
            for (int j = 0; j < tablier[0].length; j++) {
                if (tablier[i][j] == 1) {
                    for (int m = k; m < k+taille; m++) {
                        for (int n = l; n < l+taille; n++) {
                            pic.setColorAt(n, m, Color.BLUE);
                        }
                    }
                }
                else if (tablier[i][j] == 2) {
                    for (int m = k; m < k+taille; m++) {
                        for (int n = l; n < l+taille; n++) {
                            pic.setColorAt(n, m, Color.RED);
                        }
                    }
                }
                else if (tablier[i][j] == 3) {
                    for (int m = k; m < k+taille; m++) {
                        for (int n = l; n < l+taille; n++) {
                            if (pic.getColorAt(n, m) != Color.GREEN) {
                                pic.setColorAt(n, m, Color.GREEN);
                            }
                        }
                    }
                }
                l+=taille;
            }
            k+=taille;
            l = 0;
        }
    }
}
