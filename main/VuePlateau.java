import javax.swing.*;
import java.awt.*;

/**
 * <b> Sae partie 2 </b> : VuePlateau()
 *
 * @version 2
 * @author Nassim EL HADDAD & Katia AUXILIEN
 */
public class VuePlateau extends JPanel {
  private int vuePoints;
  private int dim;
  public VueCase[][] tabVueCases;

  /**
   * Constructeur d'une vue du plateau.
   *
   * @param dimensions ce sont les dimensions du plateau.
   * @param theme c'est le theme du jeu.
   * @return void.
   **/
  public VuePlateau(int dimensions, int theme) 
  {
    super();
    Color background = Color.BLACK;
    if(theme == 2) 
    {
      background = Color.WHITE;
    }
    if(theme == 4)
    {
      background = new Color(64, 110, 108);
    }
    setBackground(background);

    this.dim = dimensions;
    tabVueCases = new VueCase[dim][dim];

    setLayout(new GridLayout(dim, dim, 10, 10));
    
    for (int y = 0; y < dim; y++) {
      for (int x = 0; x < dim; x++) {
        tabVueCases[x][y] = new VueCase(DonjonInfini.jeu.plateau[x][y], x, y,theme);
        add(tabVueCases[x][y]);
      }
    }
  }
  
  /**
   * A la creation, permet de peindre, et de repeindre à l'appel de repaint().
   *
   * @param g pinceau du paintcomponent.
   * @return void.
   **/
  public void paintComponent(Graphics g) 
  {
    super.paintComponent(g);

    for (int uy = 0; uy < dim; uy++) {
      for (int ux = 0; ux < dim; ux++) {
        tabVueCases[ux][uy].repaint();
      }
    }

  }

  /**
   * Mets a jour la vue d'une case.
   *
   * @param a une case à mettre à jour.
   * @param x coordonnee x de la case à mettre à jour.
   * @param y coordonnee y de la case à mettre à jour.
   * @return void.
   **/
  public void updateVue(Case a, int x, int y) 
  {
    tabVueCases[x][y].setCase(a);
  }

   /**
   * Mets a jour le plateau.
   *
   * @param plateauJeu plateau créé, un tableau double dimension de Case.
   * @return void.
   **/
  public void updatePlateau(Case[][] plateauJeu)
  {
    for (int uy = 0; uy < dim; uy++) {
      for (int ux = 0; ux < dim; ux++) {
        tabVueCases[ux][uy].setCase(plateauJeu[ux][uy]);;
      }
    }
  }

  /**
   * Permet d'augmenter ou diminuer le score de la partie selon le type d'objet trouve dans la
   * case. Agis sur vuePoints en même temps.
   *
   * @param c Case la case rencontrée par le Héros.
   * @return void.
   **/
  public void upPoint(Case c) 
  { 
    int pts = DonjonInfini.jeu.getPts();

    if (c instanceof Monstre) 
    {
      if(c instanceof Gobelin && pts > 0)
      {
        DonjonInfini.jeu.subPoints(c.getIntPv());
      }else{

        DonjonInfini.jeu.addPoints(c.getIntPv());
      }
    }
    if (c instanceof CaseBonus) 
    {
      if (c instanceof EpeeLegendaire) 
      {
        DonjonInfini.jeu.addPoints(c.getValeur());
      }
      else
      {
        DonjonInfini.jeu.addPoints(c.getValeur());
      }
    }

    this.vuePoints = DonjonInfini.jeu.getPts();

  }

  /**
   * Getter de vuePoints.
   *
   * 
   * @return un int, la variable vuePoints
   **/
  public int getVuePoints() 
  {
    return this.vuePoints;
  }

  /**
   * Permet de lancer une animation sur la case du Plateau indiquee.
   *
   * @param caseRencontree c'est la case qu'on rencontre.
   * @param x c'est la coordonnee x de la case.
   * @param y c'est la coordonnee y de la case.
   * @return un int, la variable vuePoints.
   **/
  public void launchAnimation(Case caseRencontree,int x, int y)
  {
    tabVueCases[x][y].launchAnimation(caseRencontree);
  }
  
}