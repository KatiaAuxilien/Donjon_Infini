import java.util.Random;

/**
 * <b> Sae partie 1 </b> : Potion().
 *
 * @version 1
 * @author Nassim EL HADDAD & Katia AUXILIEN
 */
public class Potion extends CaseBonus {

  /**
   * Constructeur d'une potion, heritee de CaseBonus.
   *
   * @param entier correspondant a la valeur de la potion.
   */
  public Potion() {
    super();
    Random random = new Random();
    this.valeur = random.nextInt(16) + 5;
  }

  /**
   * Redéfinition du getter du titre de la classe.
   *
   * @param args aucuns.
   * @return Chaine de caractere, representant le nom de la case.
   */
  @Override
  public String getLabel() {
    return "Potion";
  }

  /**
   * Redéfinition du getter de la valeur de la potion (pv recupere par le heros).
   *
   * @return valeur sous forme d'entier.
   */
  @Override
  public int getValeur() {
    return this.valeur;
  }
}
