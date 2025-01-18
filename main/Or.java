import java.util.Random;

/**
 * <b> Sae partie 2 </b> : Or().
 *
 * @version 2
 * @author Nassim EL HADDAD & Katia AUXILIEN
 */
public class Or extends CaseBonus {

  /**
   * Constructeur de la classe Or, heritee de CaseBonus. (surcharge)
   *
   * @param diff variable issue du choix de l'utilisateur dans le menu des options
   */
  public Or(float diff) {
    super();
    if (diff == 1.0) {
    Random random = new Random();
    this.valeur = random.nextInt(20) + 1;
    }
    if (diff == 2.0) {
    Random random = new Random();
    this.valeur = random.nextInt(25) + 5;
    }
    if (diff == 3.0) {
    Random random = new Random();
    this.valeur = random.nextInt(35) + 10;
    }
  }

  /**
   * Constructeur de la classe Or, heritee de CaseBonus. (surcharge)
   *
   * @param valeur correspondant a la valeur de l'or.
   */
  public Or(int val) {
    super();
    this.valeur = val;
  }

  /**
   * Redéfinition du getter du titre de la classe.Hérité de CaseBonus().
   *
   * @return Chaine de caractere "Or".
   */
  @Override
  public String getLabel() {
    return "Or";
  }

  /**
   * Redéfinition du getter de la valeur de l'or (pieces recupere par le heros). Hérité de CaseBonus().
   *
   * @return valeur sous forme d'entier.
   */
  @Override
  public int getValeur() {
    return this.valeur;
  }

  /**
   * Getter de la classe Or pour récupérer sa description. Hérité de CaseBonus().
   *
   * @return retourne une chaine de caractere, une description de l'or.
   **/
  @Override
  public String getDescription() {
    return "<html>Brillantes, étincelantes, leurs <br> cliquetis peut en faire perdre <br> la raison à plus d'un.</html>";
  }

}