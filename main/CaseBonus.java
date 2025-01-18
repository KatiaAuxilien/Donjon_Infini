/**
 * <b> Sae partie 2 </b> : CaseBonus()
 *
 * @version 2
 * @author Nassim EL HADDAD & Katia AUXILIEN
 */
public class CaseBonus extends Case {

  /**
   * Constructeur de la case bonus, surcharge si il y a une valeur en argument.
   *
   * @param valeur, valeur de la CaseBonus.
   * @return rien
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
   * @param niveau, variable issue du choix de l'utilisateur dans le menu des
   *              options
   * @return Une case, d'un type de classe enfant, en fonction du reel rand selon
   *         des probabilites determinees
   *         à l'avance pour générer de l'or, une arme ou une potion (Classes
   *         enfants de CaseBonus).
   * 
   **/
  public static Case newRandomCase(float niveau) {
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