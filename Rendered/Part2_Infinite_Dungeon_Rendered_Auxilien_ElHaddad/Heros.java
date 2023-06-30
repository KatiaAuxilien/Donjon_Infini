import java.lang.String;

/**
 * <b> Sae partie 2 </b> : Heros()
 *
 * @version 2
 * @author Nassim EL HADDAD & Katia AUXILIEN
 *
 **/
class Heros extends Case {
  private int pv;
  public Arme arme;
  private boolean equipe = false;
  public int pvInitiauxMax;
  public boolean move = false;
  public boolean monstreMort = false;

  /**
   * Constructeur de Héros héritée de Case.
   *
   * @param Arme que le Héros possède
   * @param diff variable issue du choix de l'utilisateur dans le menu des options
   * @return rien
   **/
  public Heros(Arme arme, float diff) 
  {
    super();
    if (diff == 1.0) {
      this.pvInitiauxMax = 200; // Valeur spécifique pour le niveau "Débutant"
    } else if (diff == 2.0) {
      this.pvInitiauxMax = 150; // Valeur spécifique pour le niveau "Intermédiaire"
    } else if (diff == 3.0) {
      this.pvInitiauxMax = 100; // Valeur spécifique pour le niveau "Expert"
    }
    this.pv = this.pvInitiauxMax;
    this.arme = arme;
    arme.setValeur(0);
    arme.setValeurMax(0);
  }

  /**
   * Représentation de la rencontre entre le heros et la case, et gère les
   * interactions entre les differents types d'objets.
   *
   * @param attribut de type Case.
   * @return booleen correspondant à la mort ou non du Héros.
   **/
  public boolean rencontrer(Case c) 
  {
    System.out.println("pv heros : " + this.getIntPv());

    this.move = true;

    if (c instanceof Monstre) {
      
      Monstre monstre = (Monstre) c;
      int pvMonstre = monstre.getIntPv(); 

      System.out.println("\u001B[34m> Héros : un " + c.getLabel() + "!");
      System.out.println("\u001B[0m");

      System.out.println("\u001B[36m-- Début du combat");
      System.out.println("\u001B[0m");

      if (this.arme.getValeur() <= 0)
      {
        this.pv -= pvMonstre;
        monstre.setIntPv(pvMonstre - 1); 
      } else {
        int pvMonstreTemp = pvMonstre;
        monstre.setIntPv(pvMonstre - this.arme.getValeur());
        int valarme =this.arme.getValeur();
        this.arme.setValeur( valarme -= pvMonstreTemp);
        if (this.arme.getValeur() <= 0) {
          System.out.println("\u001B[34m> Héros : Mon arme est cassée !");
          System.out.println("\u001B[0m");
          this.arme.setValeur(0);
          this.arme.setValeurMax(0);
          this.equipe = false;
          if (pvMonstre > 0) {
            this.pv -= pvMonstre;
          }
        }
      }
      pvMonstre = monstre.getIntPv();
      // Le gobelin vole de l'argent
      if (c.getLabel() == "Gobelin") {
        System.out.println("\u001B[31m🧌 Niark niark niark ! Je te vole ton argent !");
        System.out.println("\u001B[0m");
      }
      if (pvMonstre <= 0) {
        DonjonInfini.jeu.vue.upPoint(c); 
        this.monstreMort = true;
        System.out.println("\u001B[32m>>> Vous avez vaincu le " + c.getLabel() + " !");
        System.out.println("\u001B[0m");
        return true;
      }
      if (this.pv <= 0) {
        System.out.println("\u001B[31m>>> GAME OVER : Le " + c.getLabel() + " vous a battu...");
        System.out.println("\u001B[0m");
        return false;
      }
    } else if (c instanceof Arme) {
      Arme arme = (Arme) c;
      if (this.arme.getValeur() == 0 || arme.getValeur() > this.arme.getValeur()) {
        this.arme = arme;
        this.equipe = true;
        this.arme.setValeur(c.getValeur());
        this.arme.setValeurMax(c.getValeur());
        DonjonInfini.jeu.vue.upPoint(c);
        System.out.println("\u001B[34m> Héros : Une nouvelle arme !");
        System.out.println("\u001B[0m");
        return true;
      } 
      else 
      {
        System.out.println("\u001B[34m> Héros : Cette arme ne m'intéresse pas.");
        System.out.println("\u001B[0m");
        DonjonInfini.jeu.vue.upPoint(c);
        return true;
      }
    } else if (c instanceof Potion)
    {
      Potion potion = (Potion) c;
      int pvSoin = potion.valeur;
      int nouveauxPV = this.pv + pvSoin;

      if (nouveauxPV > this.pvInitiauxMax) {
        pvSoin = this.pvInitiauxMax - this.pv;
        this.pv = this.pvInitiauxMax;
      } else {
        this.pv += pvSoin;
      }
      DonjonInfini.jeu.vue.upPoint(c); /* On augmente le score du joueur en fonction de la valeur de la potion */
      System.out.println("\u001B[34m> Héros : ça revigore !");
      System.out.println("\u001B[0m");
      return true;
    } 
    else if (c instanceof Or) 
    {
      DonjonInfini.jeu.vue.upPoint(c); /* On augmente le score du joueur en fonction de la valeur du sac d'or */
      System.out.println("\u001B[34m> Héros : Wow de l'or !");
      System.out.println("\u001B[0m");
      return true;
    } 
    else if (c instanceof FeuCamp) 
    {
      this.pv = this.pvInitiauxMax;
      if(equipe)
      {
        this.arme.setValeur(this.arme.getValInitiaux());
      }
      System.out.println("\u001B[34m> Héros : Un feu de camp ! Vous vous reposez et réparez votre arme !");
      System.out.println("\u001B[0m");
      return true;
    }
    System.out.println("\u001B[31m>>> Le Monstre est encore en vie.");
    System.out.println("\u001B[0m");
    this.monstreMort = false;
    this.move = false;
    return true;
  }
    
  /**
   * Getter de la case getIntPV. Hérité de Case().
   *
   * @return son nombre de PV en int.
   **/
  @Override
  public int getIntPv() 
  {
    return this.pv;
  }
  
  /**
   * Getter de la case getLabel. Hérité de Case().
   *
   * @return retourne le String "Heros"
   **/
  @Override
  public String getLabel() 
  {
    return "Heros";
  }

  /**
   * Getter de la case getLabelPV. Hérité de Case().
   *
   * @return retourne les PV du heros en String, avec en denominateur le nombre de
   *         pv initiaux.
   **/
  @Override
  public String getLabelPv() 
  {
    return this.pv + "/" + this.pvInitiauxMax + " PV";
  }

  /**
   * Getter de la valeur de Héros. Hérité de Case().
   *
   * @return Rla valeur de l'arme du Héros.
   **/
  @Override
  public int getValeur() 
  {
    int valeurArme = this.arme.getValeur();
    return valeurArme;
  }

  /**
   * Getter de la classe Heros pour récupérer sa description. Hérité de Case().
   *
   * @return retourne une chaine de caractere, une description du heros
   **/
  @Override
  public String getDescription() 
  {
    return "<html>Il s'agit de moi, je<br> suis plutôt pas mal<br> sous cet angle.</html>";
  }

}