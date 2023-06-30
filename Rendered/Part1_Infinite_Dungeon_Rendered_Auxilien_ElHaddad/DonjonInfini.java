/**
 * <b> Sae partie 1 </b> : main DonjonInfini() 
 *
 * @version 1
 * @author Nassim EL HADDAD & Katia AUXILIEN
 **/
class DonjonInfini {
  public static Controleur jeu;

  /**
   * Lancement du jeu, création du controleur.
   *
   * @param Tableau de chaine de caractères correspondant à un argument. Ici,
   *                inutile.
   **/
  public static void main(String[] args) {
    jeu = new Controleur(1, 1, 0);
  }
}