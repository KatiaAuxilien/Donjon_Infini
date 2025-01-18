/**
 * <b> Sae partie 1 </b> : Case() superclasse.
 *
 * @version 1
 * @author Nassim EL HADDAD & Katia AUXILIEN
 **/

public class Case {
  protected int valeur;
  
  /**
   * Constructeur d'une Case, caractérisée par sa valeur.
   *
   **/
  public Case() {
    this.valeur = 0;
  }

  /**
   * Getter de la case pour l'utilisation d'un JLabel.
   *
   * @return retourne une chaine de caractères. ("vide" car Case() est une superclasse).
   **/
  public String getLabel() {
    return "Vide";
  }

  /**
   * Getter de la case getLabelPV pour l'utilisation d'un JLabel.
   *
   * @return retourne la chaine de caractères correspondant à ses pv (0 car c'est une superclasse).
   *         
   **/
  public String getLabelPv() {
    return "0";
  }

  /**
   * Getter de la case getValeur.
   *
   * @return retourne sa valeur (0 car c'est une superclasse).
   *         
   **/
  public int getValeur() {
    return 0;
  }

  /**
   * Getter de la case getIntPV.
   *
   * @return retourne son nombre de PV (0 car c'est une superclasse).
   *
   **/
  public int getIntPv() {
    return 0;
  }

  /**
   * Genere une nouvelle "Case", d'une classe enfant, aléatoirement.
   *
   * @return Une case, d'un type de classe enfant, en fonction du reel rand selon des probabilites determinees
   *         à l'avance pour générer un monstre (classe enfant de Case), de l'or, une arme ou une potion (Classes enfants de CaseBonus elle même Classe enfant).
   *         
   **/
  public static Case newRandomCase() {
    double rand = Math.random();
    if (rand < 0.5) {
      return new Monstre();
    } else if (rand < 0.7) {
      return new Or();
    } else if (rand < 0.875) {
      return new Arme();
    } else {
      return new Potion();
    }
  }

}