import java.util.Random;

/**
 * <b> Sae partie 2 </b> : Hache()
 *
 * @version 1
 * @author Nassim EL HADDAD & Katia AUXILIEN
 **/
public class Hache extends Arme {

  /**
   * Constructeur de la hache.
   *
   * @param diff, variable issue du choix de l'utilisateur dans le menu des options
   **/
  public Hache(float diff) {
    super(diff);
    if (diff == 1.0) {
      Random random = new Random();
      this.valeur = random.nextInt(25) + 15;
    }
    if (diff == 2.0) {
      Random random = new Random();
      this.valeur = random.nextInt(20) + 10;
    }
    if (diff == 3.0) {
      Random random = new Random();
      this.valeur = random.nextInt(10) + 5;
    }
  }

  /**
   * Getter de la classe Hache dans le cadre d'une utilisation de JLabel. Hérité de Monstre().
   *
   * @return la chaine de caractères "Hache".
   **/
  @Override
  public String getLabel() {
    return "Hache";
  }

  /**
   * Getter de la classe Hache pour récupérer sa valeur. Hérité de Monstre().
   *
   * @return retourne en entier la valeur de la hache (donc sa puissance).
   **/
  @Override
  public int getValeur() {
    return this.valeur;
  }

  /**
   * Getter de la classe Hache pour récupérer sa description. Hérité de Monstre().
   *
   * @return retourne une chaine de caractere, une description de la hache
   **/
  @Override
  public String getDescription() {
    return "Lourd mais efficace !";
  }

}
