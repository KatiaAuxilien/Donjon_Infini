
/**
 * <b> Sae partie 2 </b> : Monstre()
 *
 * @version 2
 * @author Nassim EL HADDAD & Katia AUXILIEN
 */
public class Monstre extends Case {
  protected int pv;
  protected int pvMax;

  /**
   * Constructeur de la classe Monstre. Génére aléatoirement les pv du monstre.
   *
   * @param diff variable issue du choix de l'utilisateur dans le menu des options.
   */
  public Monstre() {
    super();
  }

  /**
   * Obtenir la chaine de caractère "Monstre" pour un JLabel. Hérité de Case().
   *
   * @return la chaine de caractère "Monstre".
   */
  @Override
  public String getLabel() {
    return "Monstre";
  }

  /**
   * Obtenir la chaine de caractère correspondant aux pv du Monstre pour un
   * JLabel. Hérité de Case().
   *
   * @return les pv au format String.
   */
  @Override
  public String getLabelPv() {
    return "" + this.pv + "PV";
  }

  /**
   * Getter, obtenir les pv's d'un monstre. Hérité de Case().
   *
   * @return retourne les pv's du monstre.
   */
  @Override
  public int getIntPv() {
    return this.pv;
  }

  /**
   * Getter de la classe Monstre pour récupérer sa description. Hérité de Case().
   *
   * @return retourne une chaine de caractere, une description du monstre.
   **/
  @Override
  public String getDescription() {
    return "<html>Il vaudrait mieux <br> que je m'en éloigne <br> si je ne suis pas armé.</html>";
  }

    /**
   * Getter de la classe Monstre pour récupérer ses PVs initiaux.
   *
   * @return les PVs initiaux du monstre.
   **/
  public int getIntPvMax() {
    return this.pvMax;
  }

  /**
   * Obtenir les pv's d'un monstre en entier. Hérité de Case().
   *
   * @param nvPv nouveaux pv.
   * @return void
   **/
  public void setIntPv(int nvPv) {
    this.pv = nvPv;
  }

}