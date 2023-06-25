/**
 * <b> Sae partie 2 </b> : EpeeLegendaire()
 *
 * @version 1
 * @author Nassim EL HADDAD & Katia AUXILIEN
 **/
public class EpeeLegendaire extends Arme {

  /**
   * Constructeur de l'epee legendaire.
   *
   * @param diff variable difficulté, issue du choix de l'utilisateur dans le menu des options, par défaut à 1 niveau débutant.
   **/
  public EpeeLegendaire(float diff) {
    super(diff);
    if (diff == 1.0) {
    this.valeur = 75;
    }
    if (diff == 2.0) {
    this.valeur = 50;
    }
    if (diff == 3.0) {
    this.valeur = 40;
    }
  }

  /**
   * Getter de la classe EpeeLegendaire dans le cadre d'une utilisation de JLabel. Hérité de Arme().
   * 
   * @return la chaine de caractères "Epee legendaire".
   **/
  @Override
  public String getLabel() {
    return "Epee Legendaire";
  }

  /**
   * Getter de la classe EpeeLegendaire pour récupérer sa valeur.  Hérité de Arme().
   *
   * @return retourne en entier la valeur de l'arme (donc sa puissance).
   **/
  @Override
  public int getValeur() {
    return this.valeur;
  }

  /**
   * Getter de la classe EpeeLegendaire pour récupérer sa description.  Hérité de Arme().
   *
   * @return retourne une chaine de caractere, une description de l'epee legendaire.
   **/
  @Override
  public String getDescription() {
    return "<html>La fameuse épée surpuissante<br> du Donjon ! Mes ennemis trembleront <br> lorsqu'ils s'approcheront trop de moi.</html>";
  }
  
}
