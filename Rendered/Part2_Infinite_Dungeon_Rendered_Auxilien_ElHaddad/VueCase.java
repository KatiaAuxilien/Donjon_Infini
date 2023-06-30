import java.awt.*;
import java.awt.Graphics;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * <b> Sae partie 2 </b> : VueCase()
 *
 * @version 2
 * @author Nassim EL HADDAD & Katia AUXILIEN
 */
public class VueCase extends JPanel {
  /* Infos case affichée */
  private Case c;
  private int x = 0;
  private int y = 0;
  private int theme;
  /* JLabel affichés */
  private JLabel titre;
  private JLabel nb;
  private JLabel score;
  private JLabel ArmeHeros;
  /* Polices des JLabel */
  private Font policeTitre = new Font("Times New Roman", Font.BOLD, 10);
  private Font police = new Font("Times New Roman", Font.PLAIN, 10);
  private Font policeDescription = new Font("Times New Roman", Font.PLAIN, 15);
  private Font policeScore = new Font("Courier New", Font.BOLD, 8);
  /*Animations*/
  private int frame = 0;
  private int frameCount;
  private boolean herosAttack = false; 
  private boolean herosMove = false; 
  /*Description*/
  private boolean onDescription = false;

  /**
   * Constructeur d'une vue case.
   *
   * @param c Case.
   * @param x Coordonnee x du heros.
   * @param y Coordonnee y du Heros.
   * @param themeC theme graphique de la case.
   **/
  public VueCase(Case c, int x, int y, int themeC) 
  {
    super();
    this.c = c;
    this.x = x;
    this.y = y;
    this.theme= themeC;
    this.titre = new JLabel(c.getLabel());
    this.nb = new JLabel();

    Color foreground = Color.WHITE;
    if(theme == 2 || theme == 4)
    {
     foreground = Color.BLACK; 
    }
    if(!(c instanceof FeuCamp)) 
    {
    if (c instanceof CaseBonus) {
      
        nb.setText("Valeur : " + c.getValeur() + ""); 
      
    } else {
      nb.setText(c.getLabelPv());
    }
    }
    titre.setHorizontalAlignment(JLabel.CENTER);
    titre.setFont(policeTitre);
    titre.setForeground(foreground);
    nb.setHorizontalAlignment(JLabel.CENTER);
    nb.setFont(police);
    nb.setForeground(foreground);
    this.add(titre);
    this.add(nb);

    if (c instanceof Heros) {
      int valeurArmeHeros = c.getValeur();
      if (valeurArmeHeros != 0) {
        this.ArmeHeros = new JLabel("Arme : " + valeurArmeHeros + " PW");
      }
      if (valeurArmeHeros <= 0) {
        this.ArmeHeros = new JLabel("Non armé"); 
      }
      ArmeHeros.setHorizontalAlignment(JLabel.CENTER);
      ArmeHeros.setFont(police);
      ArmeHeros.setForeground(foreground);
      this.add(ArmeHeros);

      this.score = new JLabel("Points : 0 pts");
      score.setFont(policeScore);
      score.setForeground(foreground);
      score.setHorizontalAlignment(JLabel.CENTER);

      JLabel t1 = new JLabel();
      JLabel t2 = new JLabel();
      JLabel t3 = new JLabel();
      JLabel t4 = new JLabel();
      this.add(score);
      this.add(t1);
      this.add(t2);
      this.add(t3);
      this.add(t4);
      this.setLayout(new GridLayout(8, 1));
    }
    else {
      this.setLayout(new GridLayout(5, 1));
    }
    Description listener = new Description();
    this.addMouseListener(listener);
    
  }

  /**
   * Methode qui determine quand le heros a la vue case dans son champ de vision,
   * ou l'a deja vue au cours de la partie (effet à la mort d'un Gloculaire).
   *
   * @param caseTraite, la case dont on vérifie la visibilité.
   * @return True ou False.
   */
  public boolean isVisible(Case caseTraite) 
  {

    int xh = DonjonInfini.jeu.getxHero();
    int yh = DonjonInfini.jeu.getyHero();

    if(
      (((this.x == xh - 1) && (this.y == yh)) || 
      ((this.x == xh ) && (this.y == yh -1 )) || 
      ((this.x == xh + 1) && (this.y == yh)) || 
      ((this.x == xh ) && (this.y == yh +1 )) ||
      ((this.x == xh ) && (this.y == yh )) 
      )
    )
    {
      if(!c.getVisibilite())
      {
        this.setCase(c);
      }
      this.repaint(); 
      caseTraite.setVisibilite(true);
      return true;
    }
    this.removeAll();
    this.revalidate();
    this.repaint(); 
    caseTraite.setVisibilite(false);
    return false; 
  }

  /**
   * Dessine nos elements en fonction de notre case c.
   *
   * @param p pinceau utilisée pour peindre le composant.
   * @return void.
   **/
  public void paintComponent(Graphics p) 
  {

    /* on cree un nouveau pinceau pour pouvoir le modifier plus tard */
    Graphics secondPinceau = p.create();
    /* si le composant n'est pas cense etre transparent */
    if (this.isOpaque()) {
      /* on repeint toute la surface avec la couleur de fond */
      secondPinceau.setColor(this.getBackground());
      secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    boolean affichageDescription = onDescription;
    boolean affichageCase = true ;
    boolean effet = DonjonInfini.jeu.getEffetGloculaire();
    if(effet)
    {
      isVisible(c);
      affichageDescription= onDescription && c.getVisibilite();
      affichageCase = c.getVisibilite();
    }

    /*Sprite*/
    ImageIcon sprite = new ImageIcon("");;
    int x = 0;
    int y = 0;
    int xSize = 0;
    int ySize = 0;
    /*DonjonInfini.jeu.effetGloculaire */
  if(affichageDescription)
  {
    switch(theme)
    {
      case 1:
        sprite = new ImageIcon("textures/description/background.png");
        break;
      case 2:
        sprite = new ImageIcon("textures/description/backgroundLight.png");
        break;
      case 3:
        sprite = new ImageIcon("textures/description/backgroundDark.png");
        break;
      case 4:
        sprite = new ImageIcon("textures/description/backgroundUni.png");
        break;
    }
    
    p.drawImage(sprite.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
  }
  else
  {
    /*Couleurs*/
    Color init = new Color(23, 19, 51);
    secondPinceau.setColor(init);
    Color cHeros = init;
    Color cOr = init;
    Color cPotion = init;
    Color cFeu = init;
    Color cArme = init;
    Color cDague = init;
    Color cEpee = init;
    Color cHache = init;
    Color cSquelette = init;
    Color cGobelin = init;
    Color cGloculaire = init;
    Color cChef = init;

    if (affichageCase) 
    {
    switch (theme) {
          case 1: //Vanilla
              cHeros = new Color(0, 191, 255);
              cOr = new Color(255, 165, 0);
              cPotion = new Color(0, 250, 154);
              cArme = new Color(139, 0, 139);
              cDague = new Color(222,81,211);
              cEpee = new Color(133, 11, 133);
              cHache = new Color(189, 0, 189);
              cFeu = new Color(249, 99, 42);
              cSquelette = new Color(189,189,189);
              cGobelin = new Color(37,135,45);
              cGloculaire = new Color(91, 49, 180);
              cChef = new Color(186, 30, 30);
            break;
          case 2: //Light
            cHeros = new Color(128, 223, 255);
            cOr = new Color(255, 210, 128);
            cPotion = new Color(128, 255, 206);
            cArme = new Color(232, 174, 232);
            cDague = new Color(217, 158, 212);
            cEpee = new Color(242, 157, 242);
            cHache = new Color(252, 144, 252);
            cFeu = new Color(252, 165, 131);
            cSquelette = new Color(204, 202, 202);
            cGobelin = new Color(155, 228, 161);
            cGloculaire = new Color(178, 155, 228);
            cChef = new Color(237, 145, 145);
            break;
          case 3: //Black
            cHeros = new Color(0, 57, 77);
            cOr = new Color(77, 50, 0);
            cPotion = new Color(0, 77, 47);
            cArme = new Color(77, 0, 76);
            cDague = new Color(64, 12, 60);
            cEpee = new Color(71, 6, 71);
            cHache = new Color(77, 0, 76);
            cFeu = new Color(75, 23, 2);
            cSquelette = new Color(38, 38, 38);
            cGobelin = new Color(16, 60, 20);
            cGloculaire = new Color(30, 16, 60);
            cChef = new Color(66, 11, 11);
            break;
          case 4: //Nassim
          cHeros = new Color(77, 210, 105);
          cOr = new Color(77, 210, 105);
          cPotion = new Color(77, 210, 105);
          cArme = new Color(77, 210, 105);
          cDague = new Color(77, 210, 105);
          cEpee = cArme;
          cHache = cArme;
          cFeu = new Color(77, 210, 105);
          cSquelette = new Color(77, 210, 105);
          cGobelin = new Color(77, 210, 105);
          cGloculaire = new Color(77, 210, 105);
          cChef = new Color(77, 210, 105);
            break;
        }

    /* Coloriage des cases avec des couleurs rattachees a chaque type d'objet. */
    /*Ajout des sprites.*/
    if (c instanceof Heros) 
    {
      secondPinceau.setColor(cHeros);
      x = this.getWidth() / 4;
      y = this.getHeight() / 2;
      xSize =this.getWidth() / 2;
      ySize = this.getHeight() / 2;
      if(this.herosMove)
      {
System.out.println("Je run");
        if(frameCount == 0)
        {
          this.frameCount=9;
        }
        sprite = new ImageIcon("textures/Heros/__Run.gif");
        this.frame++;
        try 
        {
          Thread.sleep(50); //Un léger temps d'attente pour que l'animation ne soit pas trop rapide.
        } 
        catch (InterruptedException e) 
        {
          e.printStackTrace();
        }
        if(this.frame == this.frameCount)
        {
          this.herosMove = false;
          this.frame = 0;
        }
      }
      if(this.herosAttack)
      {
        if(frameCount == 0)
        {
          this.frameCount=10;
        }
        sprite = new ImageIcon("textures/Heros/__AttackComboNoMovement.gif");
        this.frame++;
        try 
        {
          Thread.sleep(50); //Un léger temps d'attente pour que l'animation ne soit pas trop rapide.
        } 
        catch (InterruptedException e) 
        {
          e.printStackTrace();
        }
        if(this.frame == this.frameCount)
        {
          this.herosAttack = false;
          this.frame = 0;
        }
      }
      else
      {
        sprite = new ImageIcon("textures/Heros/__Idle.gif");
      }
    } 
    else if (c instanceof Or) 
    {
      secondPinceau.setColor(cOr);
      if(c.getValeur() <= 10)
      {
        sprite = new ImageIcon("textures/Or/Pieces.png");
        x =(this.getWidth() / 4) + 10;
        y =(this.getHeight() / 2) +6;
        xSize =(this.getWidth() / 3)+10;
        ySize = (this.getHeight() / 3)+10;
      }
      else
      {
        sprite = new ImageIcon("textures/Or/SacOr.png");
        x =(this.getWidth() / 4) + 10;
        y =(this.getHeight() / 2) +6;
        xSize =(this.getWidth() / 3)+10;
        ySize = (this.getHeight() / 3)+10;
      }  
    } 
    else if (c instanceof Arme) 
    {
      if(c instanceof EpeeLegendaire)
      {
        secondPinceau.setColor(cEpee);
        sprite = new ImageIcon("textures/Armes/EpeeLegendaire.png");
        x =(this.getWidth() / 4) + 10;
        y =(this.getHeight() / 2) +6;
        xSize =(this.getWidth() / 3)+10;
        ySize = (this.getHeight() / 3)+10;
      }
      if(c instanceof Dague)
      {
        secondPinceau.setColor(cDague);
        sprite = new ImageIcon("textures/Armes/Dague.png");
        x =(this.getWidth() / 4) + 10;
        y =(this.getHeight() / 2) +6;
        xSize =(this.getWidth() / 3)+10;
        ySize = (this.getHeight() / 3)+10;
      }
      if(c instanceof Hache)
      {
        secondPinceau.setColor(cHache);
        sprite = new ImageIcon("textures/Armes/Hache.png");
        x =(this.getWidth() / 4) + 10;
        y =(this.getHeight() / 2) +6;
        xSize =(this.getWidth() / 3)+10;
        ySize = (this.getHeight() / 3)+10;
      }
      if(c.getLabel()=="Arme")
      {
        secondPinceau.setColor(cArme);
        sprite = new ImageIcon("textures/Armes/Arme.png");
        x =(this.getWidth() / 4) + 10;
        y =(this.getHeight() / 2) +6;
        xSize =(this.getWidth() / 3)+10;
        ySize = (this.getHeight() / 3)+10;
      }
    } 
    else if (c instanceof Monstre) 
    {
      x =this.getWidth() / 4 - 55;
      y =this.getHeight() / 2 - 50;
      xSize =this.getWidth() / 2 +100;
      ySize = this.getHeight() / 2 +100;
      //secondPinceau.setColor(couleurCorail); 
      if(c instanceof Squelette)
      {
        secondPinceau.setColor(cSquelette);
        sprite = new ImageIcon("textures/Monstre/Squelette/Idle.gif");
        
      }
      if(c instanceof Gobelin)
      {
        secondPinceau.setColor(cGobelin);
        sprite = new ImageIcon("textures/Monstre/Gobelin/Idle.gif");
      }
      if(c instanceof Gloculaire)
      {
        secondPinceau.setColor(cGloculaire);
        sprite = new ImageIcon("textures/Monstre/Gloculaire/Idle.gif");
      }
      if(c instanceof ChefDesAbysses)
      {
        secondPinceau.setColor(cChef);
        sprite = new ImageIcon("textures/Monstre/ChefDesAbysses/Idle.gif");

      }
    } 
    else if (c instanceof Potion) 
    {
      secondPinceau.setColor(cPotion);
      sprite = new ImageIcon("textures/Potion/Potion.gif");
      x =this.getWidth() / 4;
      y =this.getHeight() / 2;
      xSize =this.getWidth() / 2;
      ySize = this.getHeight() / 2;
    }
    else if (c instanceof FeuCamp) 
    {
      secondPinceau.setColor(cFeu);
      sprite = new ImageIcon("textures/FeuCamp/Campfire2D.gif");
      x =this.getWidth() / 4;
      y =this.getHeight() / 2;
      xSize =this.getWidth() / 2;
      ySize = this.getHeight() / 2;
    }
  }
    secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
    if(affichageCase)
    {
      p.drawImage(sprite.getImage(), x, y,xSize,ySize , null);
    }
    repaint();
    }
  }

  /**
   * Modifie la "vueCase" et ses éléments.
   *
   * @param newc nouvelle Case.
   * @return void.
   **/
  public void setCase(Case newc) 
  {
    this.removeAll();
    Color foreground = Color.WHITE;
    if(theme == 2 || theme == 4)
    {
     foreground = Color.BLACK; 
    }
    
    this.c = newc;

    this.titre = new JLabel(c.getLabel());
    this.nb = new JLabel();
    if(!(c instanceof FeuCamp)){

    if (c instanceof CaseBonus) {
      nb.setText("Valeur : " + c.getValeur() + "");
    } else {
      this.nb = new JLabel(c.getLabelPv());
    }     
    }

    titre.setHorizontalAlignment(JLabel.CENTER);
    titre.setFont(policeTitre);
    titre.setForeground(foreground);
    nb.setHorizontalAlignment(JLabel.CENTER);
    nb.setFont(police);
    nb.setForeground(foreground);
    this.add(titre);
    this.add(nb);

    if (c instanceof Heros) {
      int valeurArmeHeros = c.getValeur();
      if (valeurArmeHeros != 0) {
        this.ArmeHeros = new JLabel("Arme : " + valeurArmeHeros + " PW"); 
      }
      if (valeurArmeHeros <= 0) {
        this.ArmeHeros = new JLabel("Non armé");
      }
      ArmeHeros.setHorizontalAlignment(JLabel.CENTER);
      ArmeHeros.setFont(police);
      ArmeHeros.setForeground(foreground);
      this.add(ArmeHeros);
      
      this.score = new JLabel("Points : " + DonjonInfini.jeu.vue.getVuePoints() + " pts"); 

      score.setFont(policeScore);
      score.setHorizontalAlignment(JLabel.CENTER);
      score.setForeground(foreground);
      this.add(score);

      JLabel t1 = new JLabel();
      JLabel t2 = new JLabel();
      JLabel t3 = new JLabel();
      JLabel t4 = new JLabel();
      
      this.add(t1);
      this.add(t2);
      this.add(t3);
      this.add(t4);
      this.setLayout(new GridLayout(8, 1));
    }else{
      this.setLayout(new GridLayout(5, 1));
    }

    Description listener = new Description();
    this.addMouseListener(listener);
    
    this.revalidate();
  }


  /**
   * Animation d'attaque du heros.
   *
   * @param caseRencontree.
   * @return void.
   **/
  public void launchAnimation(Case caseRencontree)
  {
    if(c instanceof Heros)
    {
      if(caseRencontree instanceof Monstre)
      {
        System.out.println("Case rencontrée est un monstre.");
        this.herosAttack = true;
        System.out.println("Repeint.");
        repaint();
      }
        else
      {
        this.herosMove = true;
        System.out.println("J'avance.");
        repaint();
      }
    }
  }

  /**
   * Affichage de la description.
   *
   * 
   * @return void.
   **/
  public void showDescription()
  {
    this.removeAll();
    
    Color foreground = Color.WHITE;
    if(theme == 2 || theme == 4)
    {
     foreground = Color.BLACK; 
    }
    
    BorderLayout layout = new BorderLayout(0, 0);
    this.setLayout (layout);
    this.titre = new JLabel(c.getLabel());
    titre.setHorizontalAlignment(JLabel.CENTER);
    titre.setFont(policeTitre);
    titre.setForeground(foreground);
    this.add(titre, BorderLayout.NORTH);
    
    JLabel description = new JLabel(c.getDescription());
    description.setHorizontalAlignment(JLabel.CENTER);
    description.setFont(policeDescription);
    description.setForeground(foreground);
    this.add(description, BorderLayout.CENTER); 
    this.revalidate();
    this.onDescription = true;
    this.repaint();
  }

  /**
   * Cache la description.
   *
   * 
   * @return void.
   **/
  public void hideDescription()
  {
    this.onDescription = false;
    this.setCase(c);
  }
}