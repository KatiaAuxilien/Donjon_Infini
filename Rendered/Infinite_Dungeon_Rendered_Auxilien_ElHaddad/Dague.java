import java.util.Random;

/**
 * <b> Sae partie 2 </b> : Dague()
 *
 * @version 1
 * @author Nassim EL HADDAD & Katia AUXILIEN
 **/
public class Dague extends Arme {

  /**
   * Constructeur de la dague.
   *
   * @param diff, variable issue du choix de l'utilisateur dans le menu des options
   **/
  public Dague(float diff) {
    super(diff);
    if (diff == 1.0) {
      Random random = new Random();
      this.valeur = random.nextInt(10) + 10;
    }
    if (diff == 2.0) {
      Random random = new Random();
      this.valeur = random.nextInt(5) + 5;
    }
    if (diff == 3.0) {
      Random random = new Random();
      this.valeur = random.nextInt(1) + 4;
    }
  }

  /**
   * Getter de la classe Dague dans le cadre d'une utilisation de JLabel. Hérité de Arme().
   *
   * @return la chaine de caractères "Dague".
   **/
  @Override
  public String getLabel() {
    return "Dague";
  }

  /**
   * Getter de la classe Dague pour récupérer sa valeur. Hérité de Arme().
   *
   * @return retourne en entier la valeur de la dague (donc sa puissance).
   **/
  @Override
  public int getValeur() {
    return this.valeur;
  }

  /**
   * Getter de la classe Dague pour récupérer sa description. Hérité de Arme().
   * 
   * @return retourne une chaine de caractere, une description de la dague.
   **/
  @Override
  public String getDescription() {
    return "<html>Une petite arme, <br>néamoins toujours utile !</html>";
  }
}