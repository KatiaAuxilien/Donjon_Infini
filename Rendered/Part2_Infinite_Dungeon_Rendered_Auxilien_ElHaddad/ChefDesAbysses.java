/**
 * <b> Sae partie 2 </b> : ChefDesAbysses()
 *
 * @version 1
 * @author Nassim EL HADDAD & Katia AUXILIEN
 */
public class ChefDesAbysses extends Monstre {

  /**
   * Constructeur de la classe ChefDesAbysses. Génére aléatoirement les pv du monstre.
   *
   * @param diff, , variable issue du choix de l'utilisateur dans le menu des options
   */
  public ChefDesAbysses(float diff) {
    super();
    if (diff == 1.0) {
      this.pv = 75;
      this.pvMax = pv;
    }
    if (diff == 2.0) {
      this.pv = 100;
      this.pvMax = pv;
    }
    if (diff == 3.0) {
      this.pv = 125;
      this.pvMax = pv;
    }
  }

  /**
   * Obtenir la chaine de caractère "Chef des Abysses" pour un JLabel. Hérité de Monstre().
   *
   * @return la chaine de caractère "Chef des Abysses".
   */
  @Override
  public String getLabel() {
    return "Chef des Abysses";
  }

  /**
   * Obtenir la chaine de caractère correspondant aux pv du Monstre pour un
   * JLabel.  Hérité de Monstre().
   *
   * @return les PVs au format String.
   */
  @Override
  public String getLabelPv() {
    return "" + this.pv + "PV";
  }

  /**
   * Getter des PVs. Hérité de Monstre().
   *
   * 
   * @return les PVs du monstre
   */
  @Override
  public int getIntPv() {
    return this.pv;
  }

  /**
   * Obtenir les pv's d'un monstre en entier.  Hérité de Monstre().
   *
   * @param nvPv nouveaux pv.
   * @return rien
   **/
  public void setIntPv(int nvPv) {
    this.pv = nvPv;
  }

  /**
   * Getter de la description d'une case. Hérité de Monstre().
   *
   * @return chaine de caractere correspondand à la description du Chef des Abysses.
   * 
   **/
  @Override
  public String getDescription() {
    return "<html>Une menace redoutable, <br>il vaudrait mieux que <br>je me prépare à la combattre.</html>";
  }
}  