import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import java.awt.GridLayout;

/**
 * <b> Sae partie 1 </b> : Controleur().
 *
 * @version 1
 * @author Nassim EL HADDAD & Katia AUXILIEN
 */
public class Controleur extends JFrame implements KeyListener {
  /* Coordonnées du Héros X et Y */
  private int xHeros;
  private int yHeros;
  /* Score du joueur et donc de la partie, initialise au debut 0. */
  private static int points = 0;
  /* Booléen pour retenir si le Héros a rencontre un monstre au tour précédent pour generer ainsi de l'or */
  private static boolean casePrecedenteMonstre = false;
  /* Tableu contenant toutes les cases du jeu */
  public static Case[][] plateau = new Case[3][3];
  /* Vue sur le plateau */
  public VuePlateau vue;

  /**
   * Constructeur de Controleur.
   *
   * @param 3 entiers, position x, y et les points du joueur.
   */
  public Controleur(int xHeros, int yHeros, int pts) {
    super();
    this.xHeros = xHeros;
    this.yHeros = yHeros;
    points = pts;

    /* Initialisation du jeu */
    for (int y = 0; y < 3; y++) {
      for (int x = 0; x < 3; x++) {
        /* Creation d'un tableau contenant nos 9 cases generee aleatoirement */
        if (x == xHeros && y == yHeros) { /* Au passage sur les coordonnées du Heros on genere notre instance Héros */
          plateau[xHeros][yHeros] = new Heros(new Arme());
        } else {
          plateau[x][y] = Case.newRandomCase();
        }
      }
    }

    /* Modifications de la Frame */
    setExtendedState(JFrame.MAXIMIZED_BOTH);/*  Mettre en plein écran */
    setTitle("Donjon infini"); /* Le titre la fenêtre */
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(700, 700);
    setLayout(new GridLayout(1, 1, 0, 0));

    /* Creation du JPanel vuePlateau */
    vue = new VuePlateau();
    add(vue);
    setVisible(true);
    /* Ajout d'un key listener à la JFrame */
    addKeyListener(this);
        
    System.out.println("\u001B[36m>>> Début de la partie <<<");
    System.out.println("\u001B[0m");
  }

  /**
   * Rencontre d'un joueur et d'une case.
   *
   * @param args nouvelles positions x et y du joueur sur le plateau.
   */
  public void rencontre(int xNew, int yNew) {
    int precxHeros = this.xHeros;
    int precyHeros = this.yHeros;

    Heros hero = (Heros) plateau[precxHeros][precyHeros];

    if (hero.monstreMort && plateau[xNew][yNew] instanceof Monstre) 
    {
      casePrecedenteMonstre = true;
    }

    if (hero.move && !casePrecedenteMonstre) 
    {
      this.xHeros = xNew;
      this.yHeros = yNew;

      plateau[xNew][yNew] = plateau[precxHeros][precyHeros];
      plateau[precxHeros][precyHeros] = Case.newRandomCase();
      
      vue.updateVue(hero, xNew, yNew);
      vue.updateVue(plateau[precxHeros][precyHeros], precxHeros, precyHeros);
    } 
    
    if (hero.monstreMort && casePrecedenteMonstre) 
    {
      Monstre monstre = (Monstre) plateau[xNew][yNew];
      plateau[xNew][yNew] = (Case) new Or(monstre.pvMax);
      System.out.println("\u001B[36m > De l'or apparait.");
      System.out.println("\u001B[0m");
      
      vue.updateVue(hero, precxHeros, precyHeros);
      vue.updateVue(plateau[xNew][yNew], xNew, yNew);
      
      casePrecedenteMonstre = false;
    } 
    
    if (!hero.move)
    {
      vue.updateVue(hero, precxHeros, precyHeros);
      vue.updateVue(plateau[xNew][yNew], xNew, yNew);
    }
    vue.repaint();
    System.out.println("\u001B[36m> Score : " + points + " points. ");
    System.out.println("\u001B[0m");
  }

  /**
   * Gere les deplacements du heros avec les fleches directionnelles du clavier.
   *
   * @param args keyEvent evenement, la touche pressée.
   */
  @Override
  public void keyPressed(KeyEvent evenement) {
    int xNew = 0;
    int yNew = 0;
    int key = evenement.getKeyCode();

    /* Nouvelles coordonnées en fonction de la touche pressée */

    switch (key) {
      case KeyEvent.VK_LEFT:
        xNew = xHeros - 1;
        yNew = yHeros;
        break;
      case KeyEvent.VK_RIGHT:
        xNew = xHeros + 1;
        yNew = yHeros;
        break;
      case KeyEvent.VK_UP:
        xNew = xHeros;
        yNew = yHeros - 1;
        break;
      case KeyEvent.VK_DOWN:
        xNew = xHeros;
        yNew = yHeros + 1;
        break;
    }
    
    /*Vérifications des limites.*/
    if ((xNew >= 0 && xNew <= 2) && (yNew >= 0 && yNew <= 2)) 
    {
      
      /**Limites du déplacement en diagonale.**/
      if( ((xNew == xHeros +1) && (yNew == yHeros +1) ) ||
          ((xNew == xHeros -1) && (yNew == yHeros -1) ) ||
          ((xNew == xHeros +1) && (yNew == yHeros -1) ) ||
          ((xNew == xHeros -1) && (yNew == yHeros +1) )
        )
      {
          /* Le Héros n'avance pas. */
          System.out.println("\u001B[34m> Héros : Je ne peux pas avancer en diagonal.");
      }
      else 
      {
        System.out.println("\u001B[34m> Héros : Je peux avancer.");
        System.out.println("\u001B[0m");
        Heros h = (Heros) plateau[xHeros][yHeros];
        
        if (h.rencontrer(plateau[xNew][yNew])) {
          rencontre(xNew, yNew);
        } else {
          /* Fin du jeu ! */
          this.setVisible(false);
          this.dispose();
          System.out.println("--------------------");
          System.out.println("\u001B[35m>>> Score : " + points + " points. <<<");
          System.out.println("\u001B[0m");
        }
      }
    } 
    else 
    {
      System.out.println("\u001B[34m> Héros : Il y a un mur devant moi.");
      System.out.println("\u001B[0m");
    }

    System.out.println("--------------------");
  }

  /**
   * Non utilisée.
   *
   * @param args keyEvent evenement, la touche relachée
   */
  @Override
  public void keyReleased(KeyEvent evenement) {
  }

  /**
   *  Non utilisée.
   *
   * @param args keyEvent evenement, la touche tapée.
   */
  @Override
  public void keyTyped(KeyEvent evenement) {
  }

  /**
   * Getter des points de la partie (score).
   *
   * @return retourne les points en entier.
   **/
  public static int getPoints() {
    return points;
  }

  /**
   * Setter des points de la partie.
   *
   * @param nombre de points à ajouter, en entier. 
   */
  public void setPoints(int set) {
    points += set;
  }

}