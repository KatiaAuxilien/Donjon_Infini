/**
 * <b> Sae partie 2 </b> : Case()
 *
 * @version 2
 * @author Nassim EL HADDAD & Katia AUXILIEN
 **/
public class Case {
  protected int valeur;
  protected boolean visible;

  /**
   * Constructeur d'une Case, caractérisée par sa valeur.
   *
   **/
  public Case() 
  {
    this.valeur = 0;
  }

  /**
   * Getter de la case pour l'utilisation d'un JLabel.
   *
   * 
   * @return retourne une chaine de caractères. ("vide" car Case() est une
   *         superclasse).
   **/
  public String getLabel() 
  {
    return "";
  }

  /**
   * Getter de la case getLabelPV pour l'utilisation d'un JLabel.
   *
   * 
   * @return retourne la chaine de caractères correspondant à ses pv (0 car c'est
   *         une superclasse).
   **/
  public String getLabelPv() 
  {
    return "";
  }

  /**
 * Getter, obtenir les pv's d'un monstre.
 *
 * 
 * @return retourne les pv's du monstre.
 */
  public int getIntPv() {
    return 0;
  }

  /**
   * Getter de la case getValeur.
   *
   * 
   * @return retourne sa valeur (0 car c'est une superclasse).
   * 
   **/
  public int getValeur() 
  {
    return this.valeur;
  }

  /**
   * Setter de la visibilité d'une case, utilisee lors des combats contre
   * Gloculaire
   *
   * @param v, booleen determinant si oui ou non la case est visible.
   * @return void
   **/
  public void setVisibilite(boolean nvVisib) 
  {
    this.visible = nvVisib;
  }

  /**
   * Getter de la visibilite d'une case
   *
   * 
   * @return boolean la variable visible.
   *
   **/
  public boolean getVisibilite() 
  {
    return visible;
  }

  /**
   * Getter de la description d'une case
   *
   * 
   * @return String chaine de caractere 
   *            
   **/
  public String getDescription() 
  {
    return "Une case simple.";
  }

  /**
   * Genere une nouvelle "Case", d'une classe enfant, aléatoirement.
   *
   * @param niveau variable issue du choix de l'utilisateur dans le menu des options
   * @return Case Une case, d'un type de classe enfant, en fonction du reel rand selon
   *         des probabilites determinees
   *         à l'avance pour générer des types de monstres, des types d'arme et
   *         des types de soin.
   * 
   **/
  public static Case newRandomCase(float niveau) 
  {
    double rand = Math.random();
    if (rand < 0.04) {
      return new ChefDesAbysses(niveau);
    } else if (rand < 0.14) {
      return new Gloculaire(niveau);
    } else if (rand < 0.24) {
      return new Gobelin(niveau);
    } else if (rand < 0.40) {
      return new Squelette(niveau);
    } else if (rand < 0.60) {
      return new Or(niveau);
    } else if (rand < 0.63) {
      return new EpeeLegendaire(niveau);
    } else if (rand < 0.69) {
      return new Hache(niveau);
    } else if (rand < 0.77) {
      return new Dague(niveau);
    } else if (rand < 0.92) {
      return new Potion(niveau);
    } else {
      return new FeuCamp();
    }
  }

}