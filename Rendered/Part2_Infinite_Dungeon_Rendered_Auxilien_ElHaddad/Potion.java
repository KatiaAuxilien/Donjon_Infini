import java.util.Random;

/**
 * <b> Sae partie 2 </b> : Potion().
 *
 * @version 2
 * @author Nassim EL HADDAD & Katia AUXILIEN
 */
public class Potion extends CaseBonus {

  /**
   * Constructeur d'une potion, heritee de CaseBonus.
   *
   * @param diff variable issue du choix de l'utilisateur dans le menu des options
   */
  public Potion(float diff) {
    super();
    if (diff == 1.0) {
    Random random = new Random();
    this.valeur = random.nextInt(25) + 5;
    }
    if (diff == 2.0) {
    Random random = new Random();
    this.valeur = random.nextInt(16) + 5;
    }
    if (diff == 3.0) {
    Random random = new Random();
    this.valeur = random.nextInt(10) + 5;
    }
  }

  /**
   * Redéfinition du getter du titre de la classe. Hérité de CaseBonus().
   *
   * 
   * @return Chaine de caractere, representant le nom de la case.
   */
  @Override
  public String getLabel() {
    return "Potion";
  }

  /**
   * Redéfinition du getter de la valeur de la potion (pv recupere par le heros). Hérité de CaseBonus().
   *
   * 
   * @return valeur sous forme d'entier.
   */
  @Override
  public int getValeur() {
    return this.valeur;
  }

   /**
   * Getter de la classe Potion pour récupérer sa description. Hérité de CaseBonus().
   *
   * 
   * @return retourne une chaine de caractere, une description de la potion
   **/
  @Override
  public String getDescription() {
    return "<html>Berk ! Le goût de cette<br> potion est le prix à <br>payer pour survivre à <br>de telles aventures.<html>";
  }

}
