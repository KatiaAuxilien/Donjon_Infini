import java.util.Random;

/**
 * <b> Sae partie 1 </b> : Monstre()
 *
 * @version 1
 * @author Nassim EL HADDAD & Katia AUXILIEN
 */
public class Monstre extends Case {
  private int pv;
  public int pvMax;

  /**
   * Constructeur de la classe Monstre. Génére aléatoirement les pv du monstre.
   *
   */
  public Monstre() {
    super();
    Random random = new Random();
    this.pv = random.nextInt(16) + 5;
    this.pvMax=pv;
  }

  /**
   * Obtenir la chaine de caractère "Monstre" pour un JLabel.
   *
   * @return la chaine de caractère "Monstre".
   */
  @Override
  public String getLabel() {
    return "Monstre";
  }

  /**
   * Obtenir la chaine de caractère correspondant aux pv du Monstre pour un JLabel.
   *
   * @return les pv au format String.
   */
  @Override
  public String getLabelPv() {
    return "" + this.pv + "PV";
  }
  
  /**
   * Obtenir les pv's d'un monstre.
   *
   * @return retourne les pv's du monstre.
   */
  @Override
  public int getIntPv() {
    return this.pv;
  }
  
  /**
   * Obtenir les pv's d'un monstre en entier.
   *
   * @param degats que subissent le monstre.
   **/
  public void setIntPv(int degats) {
    this.pv = this.pv - degats;
  }

}