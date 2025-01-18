import java.util.Random;

/**
 * <b> Sae partie 2 </b> : Arme().
 *
 * @version 2
 * @author Nassim EL HADDAD & Katia AUXILIEN
 **/
public class Arme extends CaseBonus {
  protected int valmax;

  /**
   * Constructeur de l'arme.
   * @param diff, variable issue du choix de l'utilisateur dans le menu des options
   *
   **/
  public Arme(float diff) {
    super();
    if (diff == 1) {
    Random random = new Random();
    this.valeur = random.nextInt(25) + 10;
    this.valmax = this.valeur;
    }
    if (diff == 2) {
    Random random = new Random();
    this.valeur = random.nextInt(16) + 5;
    this.valmax = this.valeur;
    }
    if (diff == 3) {
    Random random = new Random();
    this.valeur = random.nextInt(12) + 2;
    this.valmax = this.valeur;
    }
  }
  
  /**
   * Getter de la classe Arme pour récupérer sa valeur initiale.
   *
   * 
   * @return retourne en entier la valeur intiale de l'arme (donc sa puissance de début).
   **/
  public int getValInitiaux()
  {
    return this.valmax;
  }

  /**
   * Setter de la classe Arme pour renouveller sa valeur.
   *
   * @param nouvVal int nouvelle valeur de l'arme.
   * @return void.
   **/
  public void setValeur(int nouvVal) {
    this.valeur = nouvVal;
  }
  
  /**
   * Setter de la classe Arme pour renouveller sa valeur max.
   *
   * @param nouvValmax int nouvelle valeur max de l'arme.
   * @return void.
   **/
  public void setValeurMax(int nouvValmax) {
    this.valmax = nouvValmax;
  }

  /**
   * Getter de la classe Arme dans le cadre d'une utilisation de JLabel.
   *
   *
   * @return la chaine de caractères "Arme".
   **/
  @Override
  public String getLabel() {
    return "Arme";
  }

  /**
   * Getter de la classe Arme pour récupérer sa valeur.
   *
   * 
   * @return retourne en entier la valeur de l'arme (donc sa puissance).
   **/
  @Override
  public int getValeur() {
    return this.valeur;
  }

    /**
   * Getter de la classe Arme pour récupérer sa description.
   *
   * 
   * @return retourne une chaine de caractere, une description de l'arme.
   **/
  @Override
  public String getDescription() {
    return "<html>Une arme basique, <br>je vais pouvoir me <br>défendre des ennemis <br>qui me barreront la route.</html>";
  }
}
