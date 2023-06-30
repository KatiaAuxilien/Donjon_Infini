import java.util.Random;

/**
 * <b> Sae partie 2 </b> : Gobelin()
 *
 * @version 1
 * @author Nassim EL HADDAD & Katia AUXILIEN
 */
public class Gobelin extends Monstre {
  private int pv;
  public int pvMax;

  /**
   * Constructeur de la classe Gobelin. Génére aléatoirement les pv du monstre.
   *
   * @param diff variable issue du choix de l'utilisateur dans le menu des options.
   */
  public Gobelin(float diff) {
    super();
    if (diff == 1.0) {
    Random random = new Random();
    this.pv = random.nextInt(1) + 3;
    this.pvMax = pv;
    }
    if (diff == 2.0) {
    Random random = new Random();
    this.pv = random.nextInt(5) + 5;
    this.pvMax = pv;
    }
    if (diff == 3.0) {
    Random random = new Random();
    this.pv = random.nextInt(7) + 14;
    this.pvMax = pv;
    }
  }

  /**
   * Obtenir la chaine de caractère "Gobelin" pour un JLabel.  Hérité de Monstre().
   *
   * @return la chaine de caractère "Gobelin".
   */
  @Override
  public String getLabel() {
    return "Gobelin";
  }

  /**
   * Obtenir la chaine de caractère correspondant aux pv du Gobelin pour un
   * JLabel. Hérité de Monstre().
   *
   * @return les pv au format String.
   */
  @Override
  public String getLabelPv() {
    return "" + this.pv + "PV";
  }

    /**
   * Getter des PVs du Gobelin. Hérité de Monstre().
   *
   * @return les PV du monstre en entier.
   */
  @Override
  public int getIntPv() {
    return this.pv;
  }

  /**
   * Obtenir les pv's d'un monstre en entier. Hérité de Monstre().
   *
   * @param nvPv nouveaux pv.
   * @return voids.
   **/
  public void setIntPv(int nvPv) {
    this.pv = nvPv;
  }

   /**
   * Getter de la classe Gobelin pour récupérer sa description. Hérité de Monstre().
   *
   * @return retourne une chaine de caractere, une description du Gobelin
   **/
  @Override
  public String getDescription() {
    return "<html>Ces viles créatures ont<br> un dangereux goût<br> pour l'or.</html>";
  }

}