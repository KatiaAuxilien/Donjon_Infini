/**
 * <b> Sae partie 1 </b> : CaseBonus(), classe enfant de Case.
 *
 * @version 1
 * @author Nassim EL HADDAD & Katia AUXILIEN
 */

public class CaseBonus extends Case {
  
/**
   * Constructeur de la case bonus, surcharge si il y a une valeur en argument.
   *
   * @param valeur de la CaseBonus en entier.
   **/
  public CaseBonus(int valeur) {
    this.valeur = valeur;
  }

  /**
   * 2eme Constructeur de la case bonus (surcharge).
   *
   **/
  public CaseBonus() {
  }

  /**
   * Genere une nouvelle "CaseBonus", d'une classe enfant, aléatoirement.
   *
   * @return Une case, d'un type de classe enfant, en fonction du reel rand selon des probabilites determinees
   *         à l'avance pour générer de l'or, une arme ou une potion (Classes enfants de CaseBonus).
   *         
   **/
  public static Case newRandomCase() {
    double rand = Math.random();
    if (rand < 0.4) {
      return new Or();
    } else if (rand < 0.35) {
      return new Arme();
    } else if (rand < 0.25) {
      return new Potion();
    } else {
      return new CaseBonus();
    }
  }

}