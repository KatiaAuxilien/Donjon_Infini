import java.util.Random;

/**
 * <b> Sae partie 2 </b> : Gloculaire()
 *
 * @version 1
 * @author Nassim EL HADDAD & Katia AUXILIEN
 */
public class Gloculaire extends Monstre {

  /**
   * Constructeur de la classe Gloculaire. Génére aléatoirement les pv du monstre.
   *
   * @param diff, variable issue du choix de l'utilisateur dans le menu des options
   */
  public Gloculaire(float diff) {
    super();
    if (diff == 1.0) {
    Random random = new Random();
    this.pv = random.nextInt(1) + 5;
    this.pvMax=pv;
    }
    if (diff == 2.0) {
    Random random = new Random();
    this.pv = random.nextInt(5) + 15;
    this.pvMax=pv;
    }
    if (diff == 3.0) {
    Random random = new Random();
    this.pv = random.nextInt(10) + 25;
    this.pvMax=pv;
    }
  }

  /**
   * Obtenir la chaine de caractère "Gloculaire" pour un JLabel. Hérité de Monstre().
   *
   * @return la chaine de caractère "Gloculaire".
   */
  @Override
  public String getLabel() {
    return "Gloculaire";
  }

  /**
   * Obtenir la chaine de caractère correspondant aux pv du Monstre pour un
   * JLabel. Hérité de Monstre().
   *
   * @return les pv au format String.
   */
  @Override
  public String getLabelPv() {
    return "" + this.pv + "PV";
  }

    /**
   * Getter des PVs du gloculaire. Hérité de Monstre().
   *
   * @return les PVs
   */
    @Override
  public int getIntPv() {
    return this.pv;
  }

  /**
   * Obtenir les pv's d'un monstre en entier. Hérité de Monstre().
   *
   * @param nvPv nouveaux pv.
   * @return void.
   **/
  public void setIntPv(int nvPv) {
    this.pv = nvPv;
  }

  /**
   * Getter de la classe Gloculaire pour récupérer sa description. Hérité de Monstre().
   *
   * @return retourne une chaine de caractere, une description du gloculaire
   **/
  @Override
  public String getDescription() {
    return "T'as de beaux yeux tu sais ?";
  }

}