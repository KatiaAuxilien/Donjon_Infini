import java.util.Random;

/**
 * <b> Sae partie 1 </b> : Or().
 *
 * @version 1
 * @author Nassim EL HADDAD & Katia AUXILIEN
 */
public class Or extends CaseBonus {

  /**
   * Constructeur de la classe Or, heritee de CaseBonus. (surcharge)
   *
   */

  public Or() {
    super();
    Random random = new Random();
    this.valeur = random.nextInt(16) + 5;
  }

  /**
   * Constructeur de la classe Or, heritee de CaseBonus. (surcharge)
   *
   * @param entier correspondant a la valeur de l'or.
   */
  public Or(int val) {
    super();
    this.valeur = val;
  }

  /**
   * Redéfinition du getter du titre de la classe.
   *
   * @return Chaine de caractere, representant le nom de la case.
   */
  @Override
  public String getLabel() {
    return "Or";
  }

  /**
   * Redéfinition du getter de la valeur de l'or (pieces recupere par le heros).
   *
   * @return valeur sous forme d'entier.
   */
  public int getValeur() {
    return this.valeur;
  }
}