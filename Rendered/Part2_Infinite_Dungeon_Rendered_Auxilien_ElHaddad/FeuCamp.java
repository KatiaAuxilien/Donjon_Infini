/**
 * <b> Sae partie 2 </b> : FeuCamp().
 *
 * @version 1
 * @author Nassim EL HADDAD & Katia AUXILIEN
 */
public class FeuCamp extends CaseBonus {

  /**
   * Constructeur d'un feu de camp.
   *
   */
  public FeuCamp() {
    super();
  }

  /**
   * Getter de la classe FeuCamp dans le cadre d'une utilisation de JLabel. Hérité de CaseBonus().
   *
   * @return Chaine de caractere 'Feu de camp".
   */
  @Override
  public String getLabel() {
    return "Feu de camp";
  }

  
  /**
   * Getter de la classe EpeeLegendaire pour récupérer sa description. Hérité de CaseBonus().
   *
   * @return retourne une chaine de caractere, une description du feu de camp.
   **/
  @Override
  public String getDescription() {
    return "<html>Enfin ! Un feu de camp !<br> Le lieu idéal pour ce<br> reposer avant de repartir<br> à l'aventure.</html>";
  }
}
