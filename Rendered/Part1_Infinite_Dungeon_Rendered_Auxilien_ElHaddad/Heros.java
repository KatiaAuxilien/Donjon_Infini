import java.lang.String;

/**
 * <b> Sae partie 1 </b> : Heros()
 *
 * @version 1
 * @author Nassim EL HADDAD & Katia AUXILIEN
 *
 **/

class Heros extends Case {
  private int pv;
  public Arme arme = new Arme();
  public int pvInitiauxMax = 70;
  public boolean move = false;
  public boolean monstreMort = false;

  /**
   * Constructeur de Héros héritée de Case.
   *
   * @param Arme que le Héros possède.
   **/
  public Heros(Arme arme) {
    super();
    this.pv = this.pvInitiauxMax;
    this.arme = arme;
    arme.valeur = 0;
  }

  /**
   * Représentation de la rencontre entre le heros et la case, et gère les
   * interactions entre les differents types d'objets
   *
   * @param args attribut de type Case.
   * @return booleen correspondant à la mort ou non du Héros et la possibilité
   *         d'aller sur cette case.
   **/
  public boolean rencontrer(Case c) {
    this.move = true;
    if (c instanceof Monstre) { /* Si le Heros rencontre un Monstre. **/
      
      Monstre monstre = (Monstre) c;
      int pvMonstre = monstre.getIntPv(); /* Recupération des pv du Monstre dans une variable pour le combat. */
      
      System.out.println("\u001B[34m> Héros : Un Monstre !");
      System.out.println("\u001B[0m");

      /* Combat en cours : */
      System.out.println("\u001B[36m-- Début du combat");
      System.out.println("\u001B[0m");
      if (this.arme.getValeur() <= 0) /*Si le Héros n'a pas d'arme le heros perd autant de pv que les pv du monstre*/
      {
        //System.out.println("-- Hero : pas d'arme");
        this.pv -= pvMonstre;
        monstre.setIntPv(1); /* Le nombre de PV's perdus par le monstre quand le heros n'a pas d'arme n'est
                             pas indique, donc le monstre perd 1 PV lorsque le heros n'a pas d'arme pour
                             pousser le joueur a recuperer une arme avant de combattre.*/
        /*Si le Heros possède une arme le Monstre perd autant de pv que la valeur de l'arme.*/
      } else 
      {
        int pvMonstreTemp = pvMonstre;
        monstre.setIntPv(this.arme.getValeur());
        this.arme.valeur -= pvMonstreTemp; /* L'arme perd autant de durabilite que les pv du monstre */
        /*Si l'arme casse en plein combat, le programme executera par la suite le combat à main nu */
        if (this.arme.getValeur() <= 0) 
        {
          System.out.println("\u001B[34m> Héros : Mon arme est cassée !");
          System.out.println("\u001B[0m");
          this.arme.valeur = 0;
          if (pvMonstre > 0) {
            this.pv -= pvMonstre;
          }
        }
      }

      pvMonstre = monstre.getIntPv(); /* Actualisation des pv */

      /* Resultat du combat */
      if (pvMonstre <= 0) {
        DonjonInfini.jeu.vue.upPoint(c); /* Le Monstre est mort, on augmente le score du joueur */
        this.monstreMort=true;
        System.out.println("\u001B[32m>>> Vous avez vaincu le monstre !");
        System.out.println("\u001B[0m");
        return true;
      }

      if (this.pv <= 0) {
        System.out.println("\u001B[31m>>> GAME OVER : Le monstre vous a battu..."); /* Le Héros est mort ! Game Over... */
        System.out.println("\u001B[0m");
        return false;
      }

    } else if (c instanceof Arme) /* Le cas ou le heros rencontre une case Arme */
    {
      Arme arme = (Arme) c;
      /*
       * Le Héros récupère l'Arme, si il
       * n'en a pas ou si celle qu'il a déjà
       * est moins forte que celle sur la
       * case
       */
      if (this.arme.getValeur() == 0 || arme.getValeur() > this.arme.getValeur()) 
      {
        /* L'arme du heros devient l'arme de la case */
        this.arme = arme; 
        DonjonInfini.jeu.vue.upPoint(c);
        System.out.println("\u001B[34m> Héros : Une nouvelle arme !");
        System.out.println("\u001B[0m");
        return true;
      } else /* Sinon, le heros ne recupere pas l'arme car elle est moins puissante */
      {
        System.out.println("\u001B[34m> Héros : Cette arme ne m'intéresse pas.");
        System.out.println("\u001B[0m");
        DonjonInfini.jeu.vue.upPoint(c);
        return true;
      }
    } else if (c instanceof Potion) /* Le héros rencontre une case Potion */
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
    } else if (c instanceof Or) {
      DonjonInfini.jeu.vue.upPoint(c); /* On augmente le score du joueur en fonction de la valeur du sac d'or */
      System.out.println("\u001B[34m> Héros : Wow de l'or !");
      System.out.println("\u001B[0m");
      return true;
    }

    System.out.println("\u001B[31m>>> Le Monstre est encore en vie.");
    System.out.println("\u001B[0m");
    this.monstreMort=false;
    this.move = false;
    return true;
  }

  /**
   * Getter de la case getLabel.
   *
   * @return retourne le String "Heros"
   **/
  @Override
  public String getLabel() {
    return "Heros";
  }

  /**
   * Getter de la case getLabelPV.
   *
   * @return retourne les PV du heros en String, avec en denominateur le nombre de
   *         pv initiaux.
   **/
  @Override
  public String getLabelPv() {
    return this.pv + "/" + this.pvInitiauxMax + " PV";
  }

  /**
   * Getter de la valeur de Héros.
   *
   * @return Rla valeur de l'arme du Héros.
   **/
  @Override
  public int getValeur() {
    int valeurArme = this.arme.getValeur();
    return valeurArme;
  }

  /**
   * Getter de la case getIntPV.
   *
   * @return son nombre de PV en int.
   **/
  @Override
  public int getIntPv() {
    return 0;
  }

}