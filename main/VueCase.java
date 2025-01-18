import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Font;
import javax.swing.JLabel;
/**
 * <b> Sae partie 1 </b> : VueCase()
 *
 * @version 1
 * @author Nassim EL HADDAD & Katia AUXILIEN
 */

public class VueCase extends JPanel {
  /*Infos case affichée*/
  private Case c;
  private int x = 0;
  private int y = 0;
  /*JLabel affichés*/
  private JLabel titre;
  private JLabel nb;
  private JLabel score;
  private JLabel ArmeHeros;
  /*Polices des JLabel*/
  private Font policeTitre = new Font("Times New Roman", Font.BOLD, 20);
  private Font police = new Font("Times New Roman", Font.PLAIN, 20);
  private Font policeScore = new Font("Courier New", Font.BOLD, 20);


  /**
   * Constructeur d'une vue case.
   *
   * @param Attribut de type vueCase, correpondant à la case affichée. x et y
   *                 coordonnées de cette même case.
   **/
  public VueCase(Case c, int x, int y) {
    super();
    this.c = c;
    this.x = x;
    this.y = y;

    this.titre = new JLabel(c.getLabel());
    this.nb = new JLabel();

    if (c instanceof CaseBonus) {
      nb.setText("Valeur : " + c.getValeur() + ""); /* Actualise a l'affichage la valeur des armes, potions et sacs d'or */
      
    } else {
      nb.setText(c.getLabelPv());
    }
    titre.setHorizontalAlignment(JLabel.CENTER);
    titre.setFont(policeTitre);
    nb.setHorizontalAlignment(JLabel.CENTER);
    nb.setFont(police);
    this.add(titre);
    this.add(nb);
    
    if (c instanceof Heros) {
      int valeurArmeHeros = c.getValeur();
      if( valeurArmeHeros != 0){
        this.ArmeHeros = new JLabel("Arme : " + valeurArmeHeros + " PW"); /* Affichage de la puissance de l'arme que possede le heros dans sa case */
      }if( valeurArmeHeros <= 0){
        this.ArmeHeros = new JLabel("Non armé"); /* Affiche "non arme" lorsque le heros n'a pas / plus d'arme dans sa case. */
      } 
      ArmeHeros.setHorizontalAlignment(JLabel.CENTER);
      ArmeHeros.setFont(police);
      this.add(ArmeHeros);
      
      this.score = new JLabel("Points : 0 pts");
      score.setFont(policeScore);
      score.setHorizontalAlignment(JLabel.CENTER);
      this.add(score);
    }
 
    this.setLayout(new GridLayout(4, 1));    
  }

  /**
   * Dessine nos elements en fonction de notre case c.
   *
   * @param args p de type Graphics, c'est un pinceau
   **/
  public void paintComponent(Graphics p) {

    /*  on cree un nouveau pinceau pour pouvoir le modifier plus tard */
    Graphics secondPinceau = p.create();
    /* si le composant n'est pas cense etre transparent */
    if (this.isOpaque()) {
     /* on repeint toute la surface avec la couleur de fond */
      secondPinceau.setColor(this.getBackground());
      secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    secondPinceau.setColor(Color.black);

    Color bleuPastel = new Color(0, 191, 255);
    Color couleurCorail = new Color(220, 20, 60);
    Color orangeOr = new Color(255, 165, 0);
    Color vertVif = new Color(0, 250, 154);
    Color violetFonce = new Color(139, 0, 139);
    
  /* Coloriage des cases avec des couleurs rattachees a chaque type d'objet. */
    if (c instanceof Heros) {
      secondPinceau.setColor(bleuPastel);
    } else if (c instanceof Or) {
      secondPinceau.setColor(orangeOr);
    } else if (c instanceof Arme) {
      secondPinceau.setColor(violetFonce); 
    } else if (c instanceof Monstre) {
      secondPinceau.setColor(couleurCorail);
    } else if (c instanceof Potion) {
      secondPinceau.setColor(vertVif);
    }

    /* Dans tout les cas on peint notre case. On recupere la taille de notre JComponent, notre case. Elle s'adaptera en fonction du JPanel. On peint le rectancle representant la case. */
    secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
  }

  /**
   * Modifie la "vueCase" et ses éléments.
   *
   * @param nouvelle Case.
   **/
  public void setCase(Case newc) {

    this.removeAll();
    this.c = newc;

    this.titre = new JLabel(c.getLabel());
    this.nb = new JLabel();

    /*  Affichage de la valeur de tous les objets provenant d'une case bonus, donc potion, arme et or */
    if (c instanceof CaseBonus) 
    {
      nb.setText("Valeur : " + c.getValeur() + "");  
    } else 
    {
      this.nb = new JLabel(c.getLabelPv());
    }
    titre.setHorizontalAlignment(JLabel.CENTER);
    titre.setFont(policeTitre);
    nb.setHorizontalAlignment(JLabel.CENTER);
    nb.setFont(police);
    this.add(titre);
    this.add(nb);
    
    if (c instanceof Heros) 
    {
      int valeurArmeHeros = c.getValeur();
      if( valeurArmeHeros != 0)
      {
        this.ArmeHeros = new JLabel("Arme : " + valeurArmeHeros + " PW"); /* Affichage de la puissance de l'arme que possede le heros dans sa case */
      }
      if( valeurArmeHeros <= 0)
      {
        this.ArmeHeros = new JLabel("Non armé"); /* Affiche "Non arme" si le heros n'a pas d'arme. */
      }
      ArmeHeros.setHorizontalAlignment(JLabel.CENTER);
      ArmeHeros.setFont(police);
      this.add(ArmeHeros);
      
      this.score = new JLabel("Points : "+ DonjonInfini.jeu.vue.getVuePoints() +" pts"); /* Affichage du nombre de points du joueur dans la case du heros */
      score.setFont(policeScore);
      score.setHorizontalAlignment(JLabel.CENTER);
      this.add(score);
    }

    this.revalidate();
  }

}