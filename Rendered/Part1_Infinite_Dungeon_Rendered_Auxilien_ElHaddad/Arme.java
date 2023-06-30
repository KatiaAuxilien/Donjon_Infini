import java.util.Random;

/**
 * <b> Sae partie 1 </b> : Arme() hérite de CaseBonus.
 *
 * @version 1
 * @author Nassim EL HADDAD & Katia AUXILIEN
 **/
public class Arme extends CaseBonus {

  /**
   * Constructeur de l'arme. 
   *
   **/
  public Arme() {
    super();
    Random random = new Random();
    this.valeur = random.nextInt(16) + 5;
  }

  /**
   * Getter de la classe Arme dans le cadre d'une utilisation de JLabel.
   *
   * @return la chaine de caractères "Arme".
   **/
  public String getLabel() {
    return "Arme";
  }

  /**
   * Getter de la classe Arme pour récupérer sa valeur.
   *
   * @return retourne en entier la valeur de l'arme (donc sa puissance).
   **/
  public int getValeur() {
    return this.valeur;
  }

/** Remarques :
* Arme() hérite aussi de public String getLabelPV() mais une Arme n'a pas de PV.
**/
}
