import javax.swing.*;
import java.awt.*;
import java.awt.GridLayout;
import java.awt.Graphics;

/**
 * <b> Sae partie 1 </b> : VuePlateau()
 *
 * @version 1
 * @author Nassim EL HADDAD & Katia AUXILIEN
 */

public class VuePlateau extends JPanel {
  private int vuePoints;
  public static VueCase[][] tabVueCases = new VueCase[3][3];

  /**
   * Constructeur d'une vue de plateau.
   *
   **/
  public VuePlateau() {
    super();
    setBackground(Color.BLACK);
    setLayout(new GridLayout(3, 3, 10, 10));

    for (int y = 0; y < 3; y++) {
      for (int x = 0; x< 3; x++) {
        tabVueCases[x][y] = new VueCase(DonjonInfini.jeu.plateau[x][y], x, y);
        add(tabVueCases[x][y]);
      }
    }
  }

  /**
   * Mets a jour la vue d'une case.
   *
   * @param a une case, x et y ses coordonnees.
   **/
  public void updateVue(Case a, int x, int y) {
    tabVueCases[x][y].setCase(a);
  }

  /**
   * A la creation, permet de peindre, et de repeindre à l'appel de repaint().
   *
   * @param args g qui est un pinceau.
   **/
  public void paintComponent(Graphics g) {
    /* on cree un nouveau pinceau pour pouvoir le modifier plus tard */
    Graphics secondPinceau = g.create();
    /* si le composant n'est pas cense etre transparent */
    if (this.isOpaque()) {
      /* on repeint toute la surface avec la couleur de fond */
      secondPinceau.setColor(this.getBackground());
      secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
    for (int uy = 0; uy < 3; uy++) {
      for (int ux = 0; ux < 3; ux++) {
        tabVueCases[ux][uy].repaint();
      }
    }

  }

/**
   * Permet d'augmenter le score de la partie selon le type d'objet trouve dans la case. Modifie vuePoints en même temps.
   *
   * @param poin les points du joueur, c la case rencontrée par le Héros.
   **/  
  public void upPoint(/*int poin,*/ Case c) { // ajouter argument Case c
    if (c instanceof Monstre) {
      DonjonInfini.jeu.setPoints(c.getIntPv());
      this.vuePoints += c.getIntPv();
    }
    if (c instanceof CaseBonus) {
      DonjonInfini.jeu.setPoints(c.getValeur());
      this.vuePoints += c.getValeur();
    }    
  }

  /**
   * Getter de vuePoints.
   *
   * @return un int, la variable vuePoints
   **/
  public int getVuePoints(){
    return this.vuePoints;
  }

}