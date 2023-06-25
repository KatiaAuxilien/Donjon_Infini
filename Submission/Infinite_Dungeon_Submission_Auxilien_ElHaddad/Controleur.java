import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * <b> Sae partie 2 </b> : Controleur()
 *
 * @version 2
 * @author Nassim EL HADDAD & Katia AUXILIEN
 **/
public class Controleur extends JFrame implements ActionListener, KeyListener {
  private int xHeros = 1;
  private int yHeros = 1;
  private static int points = 0;
  // private static Musique clip = new Musique();
  private static boolean inOptions = false;
  private static int dimensionPlateau = 3;
  public int theme = 1;
  public float niveau = 1;
  public static Case[][] plateau = new Case[dimensionPlateau][dimensionPlateau];
  public VuePlateau vue;
  private static boolean casePrecedenteMonstre = false;
  private int toursEffetGloculaire = 0;
  private boolean effetGloculaire = false;

  /**
   * Constructeur du contrôleur.
   *
   **/
  public Controleur() {
    super();
    // clip.playMusic();
    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ImageIcon icone = new ImageIcon("textures/icon.png");
    this.setIconImage(icone.getImage());
    setSize(700, 700);
    MenuPrincipal();
  }

  /**
   * Methode permettant de demarrer le jeu, s'active apres avoir clique sur Jouer
   *
   * @param xHeros, coordonnee x du heros
   * @param yHeros, coordonnee y du heros
   * @param pts, score de la partie
   * @param dimension, dimensions du plateau qui peuvent etre modifiees dans les options
   * @param difficulte, difficulte de la partie qui peut etre modifiee dans les options
   * @param theme, theme du plateau qui peut etre modifie dans les options
   * 
   * @return void, donc rien
   */
  public void jouer(int xHeros, int yHeros, int pts, int dimension, float difficulte, int theme1) {
    this.getContentPane().removeAll();
    this.xHeros = xHeros;
    this.yHeros = yHeros;
    points = pts;
    dimensionPlateau = dimension;
    this.niveau = difficulte;
    this.theme = theme1;
    plateau = new Case[dimensionPlateau][dimensionPlateau];
    for (int y = 0; y < dimensionPlateau; y++) {
      for (int x = 0; x < dimensionPlateau; x++) {
        if (x == xHeros && y == yHeros) {
          plateau[xHeros][yHeros] = new Heros(new Arme(niveau),this.niveau);
        } else {
          plateau[x][y] = Case.newRandomCase(niveau);
        }
      }
    }
    setTitle("Donjon infini");
    setLayout(new GridLayout(1, 1, 0, 0));
    vue = new VuePlateau(dimensionPlateau, this.theme);
    add(vue);
    setVisible(true);
    this.revalidate();
    addKeyListener(this);
    System.out.println("\u001B[36m>>> Début de la partie <<<");
    System.out.println("\u001B[0m");
    requestFocus();
  }


  /**
   * MenuPrincipal represente le menu principal du jeu
   *
   * @return void.
   **/
  private void MenuPrincipal() {
    this.getContentPane().removeAll();
    this.setTitle("Donjon Infini - Menu principal ");

    Color transparent = new Color(0, 0, 0, 0);
    JPanelWithBackground MenuPrincipal = new JPanelWithBackground("textures/fond/dungeon_background.png");

    MenuPrincipal.setLayout(new GridLayout(2, 1, 5, 5));

    // Création des boutons carrés
    JButton boutonJouer = creerBoutonRectangle("Jouer");
    JButton boutonOptions = creerBoutonRectangle("Options");
    JButton boutonQuitter = creerBoutonRectangle("Quitter");
    boutonJouer.setForeground(Color.WHITE);
    boutonOptions.setForeground(Color.WHITE);
    boutonQuitter.setForeground(Color.WHITE);
    boutonJouer.addActionListener(this);
    boutonOptions.addActionListener(this);
    boutonQuitter.addActionListener(this);

    // Mise en page des boutons
    JLabel titre = new JLabel("Donjon Infini");
    titre.setFont(new Font("Arial", Font.BOLD, 50));
    titre.setForeground(Color.WHITE);
    titre.setVerticalAlignment(JLabel.CENTER);
    titre.setHorizontalAlignment(JLabel.CENTER);

    MenuPrincipal.add(titre);

    JPanel boutons = new JPanel();
    boutons.setBackground(transparent);
    boutons.setLayout(new GridLayout(3, 1, 5, 5));

    JPanel paneBoutonJouer = new JPanel();
    paneBoutonJouer.add(boutonJouer);
    paneBoutonJouer.setBackground(transparent);
    boutons.add(paneBoutonJouer);

    JPanel paneBoutonOptions = new JPanel();
    paneBoutonOptions.add(boutonOptions);
    paneBoutonOptions.setBackground(transparent);
    boutons.add(paneBoutonOptions);

    JPanel paneBoutonQuitter = new JPanel();
    paneBoutonQuitter.setBackground(transparent);

    boutons.add(boutonQuitter);
    paneBoutonQuitter.add(boutonQuitter);
    boutons.add(paneBoutonQuitter);

    MenuPrincipal.add(boutons);

    this.add(MenuPrincipal, BorderLayout.CENTER);
    this.setVisible(true);
    this.revalidate();
  }

  /**
   * MenuOptions represente le menu des options du jeu
   *
   * @return void, donc rien
   **/
  private void MenuOptions() {
    inOptions = true;
    this.getContentPane().removeAll();
    this.setTitle("Donjon Infini - Options");

    Color transparent = new Color(0, 0, 0, 0);

    JPanelWithBackground optionsPane = new JPanelWithBackground("textures/fond/dungeon_background_options.png");
    optionsPane.setLayout(new GridLayout(8, 1, 5, 5));
    JLabel titre = new JLabel("Options");
    titre.setFont(new Font("Serif", Font.BOLD, 50));
    titre.setForeground(Color.WHITE);
    titre.setVerticalAlignment(JLabel.CENTER);
    titre.setHorizontalAlignment(JLabel.CENTER);
    optionsPane.add(titre);
    JLabel dim = new JLabel("Dimensions");
    dim.setForeground(Color.WHITE);
    dim.setFont(new Font("Arial", Font.BOLD, 30));
    dim.setVerticalAlignment(JLabel.CENTER);
    dim.setHorizontalAlignment(JLabel.CENTER);
    optionsPane.add(dim);
    JPanel optionsDim = new JPanel();
    optionsDim.setBackground(transparent);
    JButton bouton3 = creerBouton("3*3", Color.BLACK);
    bouton3.setForeground(Color.WHITE);
    JButton bouton6 = creerBouton("5*5", Color.BLACK);
    bouton6.setForeground(Color.WHITE);
    JButton bouton9 = creerBouton("7*7", Color.BLACK);
    bouton9.setForeground(Color.WHITE);
    optionsDim.add(bouton3);
    bouton3.addActionListener(this);
    optionsDim.add(bouton6);
    bouton6.addActionListener(this);
    optionsDim.add(bouton9);
    bouton9.addActionListener(this);
    optionsPane.add(optionsDim);
    JLabel diffic = new JLabel("Difficultés");
    diffic.setFont(new Font("Arial", Font.BOLD, 30));
    diffic.setForeground(Color.WHITE);
    diffic.setVerticalAlignment(JLabel.CENTER);
    diffic.setHorizontalAlignment(JLabel.CENTER);
    optionsPane.add(diffic);
    JPanel optionsDiff = new JPanel();
    optionsDiff.setBackground(transparent);
    JButton boutonDebutant = creerBoutonLarge("Niveau débutant", Color.GREEN);
    JButton boutonIntermediaire = creerBoutonLarge("Niveau intermédiaire", Color.YELLOW);
    JButton boutonExpert = creerBoutonLarge("Niveau expert", Color.RED);
    optionsDiff.add(boutonDebutant);
    boutonDebutant.addActionListener(this);
    optionsDiff.add(boutonIntermediaire);
    boutonIntermediaire.addActionListener(this);
    optionsDiff.add(boutonExpert);
    boutonExpert.addActionListener(this);
    optionsPane.add(optionsDiff);
    JLabel theme = new JLabel("Thèmes");
    theme.setFont(new Font("Arial", Font.BOLD, 30));
    theme.setForeground(Color.WHITE);
    theme.setVerticalAlignment(JLabel.CENTER);
    theme.setHorizontalAlignment(JLabel.CENTER);
    optionsPane.add(theme);
    JPanel optionsTheme = new JPanel();
    optionsTheme.setBackground(transparent);
    JButton boutonTheme1 = creerBoutonLarge("Thème Vanilla", Color.GRAY);
    boutonTheme1.setForeground(Color.WHITE);
    JButton boutonTheme2 = creerBoutonLarge("Thème Clair", Color.white);
    boutonTheme2.setForeground(Color.BLACK);
    JButton boutonTheme3 = creerBoutonLarge("Thème Sombre", Color.BLACK);
    boutonTheme3.setForeground(Color.WHITE);
    JButton boutonTheme4 = creerBoutonLarge("Thème Uni", Color.GREEN);
    boutonTheme4.setForeground(Color.WHITE);
    optionsTheme.add(boutonTheme1);
    optionsTheme.add(boutonTheme2);
    optionsTheme.add(boutonTheme3);
    optionsTheme.add(boutonTheme4);
    boutonTheme1.addActionListener(this);
    boutonTheme2.addActionListener(this);
    boutonTheme3.addActionListener(this);
    boutonTheme4.addActionListener(this);
    optionsPane.add(optionsTheme);
    JPanel retourPanel2 = new JPanel();
    retourPanel2.setBackground(transparent);
    JPanel retourPanel = new JPanel();
    retourPanel.setBackground(transparent);
    JButton retourButton = creerBoutonRectangle("Retour");
    retourButton.setForeground(Color.WHITE);
    retourButton.addActionListener(this);
    retourPanel.add(retourButton);
    retourPanel2.add(retourPanel);
    optionsPane.add(retourPanel2);
    this.add(optionsPane, BorderLayout.CENTER);
    this.setVisible(true);
    this.revalidate();
  }

  /**
   * endScreen represente le menu qui apparait lorsque l'on perd la partie
   *
   * @return void.
   **/
  private void endScreen() {
    this.getContentPane().removeAll();
    this.setTitle("Donjon Infini - Game Over");
    Color transparent = new Color(0, 0, 0, 0);
    JPanelWithBackground End = new JPanelWithBackground("textures/fond/dungeon_background_end.png");
    End.setBackground(transparent);
    End.setLayout(new GridLayout(5, 1, 5, 5));
    JLabel titre = new JLabel("Game Over");
    titre.setForeground(Color.WHITE);
    titre.setFont(new Font("Serif", Font.BOLD, 50));
    titre.setVerticalAlignment(JLabel.CENTER);
    titre.setHorizontalAlignment(JLabel.CENTER);
    End.add(titre);

    int votreScore = getPoints();
    int meilleurScore = this.getBestScore();


    JPanel resPane = new JPanel();
    resPane.setBackground(transparent);

    JLabel annonce_res = new JLabel("Score " + votreScore);
    annonce_res.setForeground(Color.WHITE);
    annonce_res.setFont(new Font("Serif", Font.BOLD, 25));

    JPanel resbestPane = new JPanel();
    resbestPane.setBackground(transparent);

    JLabel annonce_bestscore = new JLabel("Meilleur score " + meilleurScore);
    
    if (votreScore >= meilleurScore) {
      annonce_bestscore = new JLabel("Vous avez battu le meilleur score !");
      
    }
    annonce_bestscore.setForeground(Color.WHITE);
    annonce_bestscore.setFont(new Font("Serif", Font.BOLD, 25));
    resbestPane.add(annonce_bestscore);
    resPane.add(annonce_res);

    End.add(resbestPane);
    End.add(resPane);
    

    JPanel Choix = new JPanel();
    Choix.setBackground(transparent);

    JButton Quitter = creerBoutonRectangle("Quitter");
    Quitter.setForeground(Color.WHITE);
    Quitter.addActionListener(this);
    Choix.add(Quitter);
    End.add(Choix);

    this.add(End, BorderLayout.CENTER);
    this.setVisible(true);
    this.revalidate();
  }

  /**
   * actionPerformed récupére l'événement générée, ici via les boutons, et lance le jeu, gére les options, et permet de fermer le jeu.
   *
   * @return void.
   **/
  public void actionPerformed(ActionEvent e) {
    String choix = e.getActionCommand();

    if (inOptions) {
      if (choix.startsWith("Niveau ")) {
        switch (choix) {
          case "Niveau débutant":
            this.niveau = 1;
            break;
          case "Niveau intermédiaire":
            this.niveau = 2;
            break;
          case "Niveau expert":
            this.niveau = 3;
            break;
        }
      }
      if (choix.startsWith("Thème ")) {
        switch (choix) {
          case "Thème Vanilla":
            this.theme = 1;
            break;
          case "Thème Clair":
            this.theme = 2;
            break;
          case "Thème Sombre":
            this.theme = 3;
            break;
          case "Thème Uni":
            this.theme = 4;
            break;
        }
      }
      if (choix.startsWith("3") || choix.startsWith("5") || choix.startsWith("7")) {
        switch (choix) {
          case "3*3":
            dimensionPlateau = 3;
            this.xHeros = 1;
            this.yHeros = 1;
            break;
          case "5*5":
            dimensionPlateau = 5;
            this.xHeros = 2;
            this.yHeros = 2;
            break;
          case "7*7":
            dimensionPlateau = 7;
            this.xHeros = 3;
            this.yHeros = 3;
            break;
        }
      }
    }
          switch (choix) {
        case "Jouer":
          jouer(xHeros, yHeros, 0, dimensionPlateau, niveau, theme);
          break;
        case "Options":
          MenuOptions();
          break;
        case "Retour":
          MenuPrincipal();
          inOptions = false;
            break;
        case "Quitter":
          dispose();
          // clip.stopMusic();
          break;
      }
  }

  /**
   * Permet de creer un bouton
   *
   * @param label la chaine de caractère contenue dans le bouton.
   * @param couleur Couleur du bouton.
   *
   * @return JButton créé
   **/
  private JButton creerBouton(String label, Color couleur) {
    JButton bouton = new JButton(label);
    bouton.setPreferredSize(new Dimension(100, 50));
    bouton.setBackground(couleur);
    bouton.setFocusPainted(false);
    bouton.setFont(new Font("Arial", Font.BOLD, 16));
    return bouton;
  }
  
  /**
   * Permet de creer un bouton large (dimensions plus larges)
   *
   * @param label la chaine de caractère contenue dans le bouton.
   * @param couleur Couleur du bouton.
   *
   * @return JButton créé
   **/
  private JButton creerBoutonLarge(String label, Color couleur) {
    JButton bouton = new JButton(label);
    bouton.setPreferredSize(new Dimension(190, 50));
    bouton.setBackground(couleur);
    bouton.setFocusPainted(false);
    bouton.setFont(new Font("Serif", Font.BOLD, 16));
    return bouton;
  }
  
  /**
   * Permet de creer un bouton en forme de rectangle
   *
   * @param label la chaine de caractère contenue dans le bouton.
   *
   * @return JButton, donc un bouton
   **/
  private JButton creerBoutonRectangle(String label) {
    JButton bouton = new JButton(label);
    bouton.setPreferredSize(new Dimension(180, 50));
    bouton.setBackground(Color.BLACK);
    bouton.setFocusPainted(false);
    bouton.setFont(new Font("Serif", Font.BOLD, 16));
    return bouton;
  }

  /**
   * Permet de gerer differents cas lors des rencontres que rencontrer dans Heros ne gere pas entierement (or apres mort de monstre), gere le score, actualise la vue des cases ainsi que les coordonnees du Heros
   *
   * @param xNew nouvelle coordonnee x du heros
   * @param yNew nouvelle coordonnee y du heros
   *
   * @return void
   **/
  private void rencontre(int xNew, int yNew) {
    int precxHeros = this.xHeros;
    int precyHeros = this.yHeros;

    Heros hero = (Heros) plateau[precxHeros][precyHeros];

    if (hero.monstreMort && plateau[xNew][yNew] instanceof Monstre) {
      casePrecedenteMonstre = true;
    }

    if (hero.move && !casePrecedenteMonstre) {
      this.xHeros = xNew;
      this.yHeros = yNew;

      plateau[xNew][yNew] = plateau[precxHeros][precyHeros];
      plateau[precxHeros][precyHeros] = Case.newRandomCase(niveau);

      vue.updateVue(hero, xNew, yNew);
      vue.updateVue(plateau[precxHeros][precyHeros], precxHeros, precyHeros);
    }

    if (hero.monstreMort && casePrecedenteMonstre) {
      if (plateau[xNew][yNew] instanceof Gloculaire) {
        Gloculaire gloculaire = (Gloculaire) plateau[xNew][yNew];
        plateau[xNew][yNew] = new Or(gloculaire.pvMax);
      } else if (plateau[xNew][yNew] instanceof Gobelin) {
        Gobelin gobelin = (Gobelin) plateau[xNew][yNew];
        plateau[xNew][yNew] = new Or(gobelin.pvMax);
      } else if (plateau[xNew][yNew] instanceof Squelette) {
        Squelette squelette = (Squelette) plateau[xNew][yNew];
        plateau[xNew][yNew] = new Or(squelette.pvMax);
      }else if (plateau[xNew][yNew] instanceof ChefDesAbysses) {
        ChefDesAbysses chef = (ChefDesAbysses) plateau[xNew][yNew];
        plateau[xNew][yNew] = new Or(chef.pvMax);
      }
      
      System.out.println("\u001B[36m > De l'or apparaît.");
      System.out.println("\u001B[0m");

      vue.updateVue(hero, precxHeros, precyHeros);
      vue.updateVue(plateau[xNew][yNew], xNew, yNew);

      casePrecedenteMonstre = false;
    }

    if (!hero.move) {
      vue.updateVue(hero, precxHeros, precyHeros);
      vue.updateVue(plateau[xNew][yNew], xNew, yNew);
    }

    vue.repaint();
    System.out.println("\u001B[36m> Score : " + points + " points. ");
    System.out.println("\u001B[0m");
  }

  /**
   * Permet de gerer les deplacements du heros avec les fleches directionnelles, et quitter le jeu avec Echap.
   *
   * @param evenement de type KeyEvent généré par une action clavier.
   *
   * @return void
   **/
  @Override
  public void keyPressed(KeyEvent evenement) {
    int xNew = 0;
    int yNew = 0;
    int key = evenement.getKeyCode();

    switch (key) {
      case KeyEvent.VK_LEFT:
        xNew = xHeros - 1;
        yNew = yHeros;
        System.out.println("Deplacement : Gauche x: " + xNew + " y: " + yNew + "");
        break;
      case KeyEvent.VK_RIGHT:
        xNew = xHeros + 1;
        yNew = yHeros;
        System.out.println("Deplacement : Droit x: " + xNew + " y: " + yNew + "");
        break;
      case KeyEvent.VK_UP:
        xNew = xHeros;
        yNew = yHeros - 1;
        System.out.println("Deplacement : Haut x: " + xNew + " y: " + yNew + "");
        break;
      case KeyEvent.VK_DOWN:
        xNew = xHeros;
        yNew = yHeros + 1;
        System.out.println("Deplacement : Bas x: " + xNew + " y: " + yNew + "");
        break;
      case KeyEvent.VK_ESCAPE:
        dispose();
        // clip.stopMusic();
        break;
    }

    if ((xNew >= 0 && xNew < dimensionPlateau) && (yNew >= 0 && yNew < dimensionPlateau)) {
      if (((xNew == xHeros + 1) && (yNew == yHeros + 1)) ||
          ((xNew == xHeros - 1) && (yNew == yHeros - 1)) ||
          ((xNew == xHeros + 1) && (yNew == yHeros - 1)) ||
          ((xNew == xHeros - 1) && (yNew == yHeros + 1))) 
      {
        System.out.println("\u001B[34m> Héros : Je ne peux pas avancer en diagonal.");
      } else {
        System.out.println("\u001B[34m> Héros : Je peux avancer.");
        System.out.println("\u001B[0m");
        Heros h = (Heros) plateau[xHeros][yHeros];

        vue.launchAnimation(plateau[xNew][yNew], xHeros, yHeros);

        if (h.rencontrer(plateau[xNew][yNew])) {
          if (toursEffetGloculaire > 0 && effetGloculaire) {
            toursEffetGloculaire--;
          }
          if (toursEffetGloculaire == 0) {
            effetGloculaire = false;
            vue.updatePlateau(plateau);
          }
          if (plateau[xNew][yNew] instanceof Gloculaire && h.monstreMort) {
            toursEffetGloculaire = getDimensionPlateau();
            effetGloculaire = true;
          }
          rencontre(xNew, yNew);
        } else {
          /* Fin du jeu ! */
          System.out.println("--------------------");
          System.out.println("\u001B[35m>>> Score : " + points + " points. <<<");
          System.out.println("\u001B[0m");

          int bestScore = getBestScore();
          if (points > bestScore) {
            System.out.println(bestScore + " Vous avez atteint le meilleur score !");
            setBestScore();

          } else {
            System.out.println("Le meilleur score est " + bestScore);

          }

          endScreen();
        }
      }
    } else {
      System.out.println("\u001B[34m> Héros : Il y a un mur devant moi.");
      System.out.println("\u001B[0m");
    }
    System.out.println("--------------------");
  }

  /**
   * Non utilisé
   * @param evenement de type KeyEvent généré par une action clavier.
   *
   * @return void
   **/
  @Override
  public void keyReleased(KeyEvent evenement) {
  }

  /**
   * Non utilisé
   * @param evenement de type KeyEvent généré par une action clavier.
   *
   * @return void
   **/
  @Override
  public void keyTyped(KeyEvent evenement) {
  }

  /**
   * Getter des points de la partie (score)
   * 
   *
   * @return un int représentant les points de la partie
   **/
  public static int getPoints() {
    return points;
  }

    /**
   * Getter des points de la partie (score), dans une méthode non static.
   * 
   *
   * @return un int représentant les points de la partie
   **/
  public int getPts() {
    return points;
  }

  /**
   * Ajouts des points de la partie (score)
   * 
   * @param add ce sont les points supplementaires
   *
   * @return void
   **/
  public void addPoints(int add) {
    points += add;
  }

  /**
   * Retirer des points de la partie (score).
   * 
   * @param minus ce sont les points en moins.
   *
   * @return void
   **/
  public void subPoints(int minus) {
    points -= minus;
  }

  /**
   * Getter de la coordonnee x du heros.
   * 
   *
   * @return x la coordonnee x du heros en entier.
   **/
  public int getxHero() {
    return this.xHeros;
  }

  /**
   * Getter de la coordonnee y du heros.
   * 
   *
   * @return y la coordonnee y du heros en entier.
   **/
  public int getyHero() {
    return this.yHeros;
  }

  /**
   * Getter des dimensions du plateau.
   * 
   *
   * @return les dimensions en int du plateau.
   **/
  public static int getDimensionPlateau() {
    return dimensionPlateau;
  }

  /**
   * Getter de l'effet du gloculaire, un booleen renvoyant vrai ou faux.
   * 
   *
   * @return l'effet du gloculaire, vrai ou faux.
   **/
  public boolean getEffetGloculaire() {
    return this.effetGloculaire;
  }

  /**
   * Setter du meilleur score dans un fichier.
   * 
   *
   * @return void.
   **/
  private void setBestScore() {
    try {
      FileOutputStream fos = new FileOutputStream("saves/bestScore.bin");
      DataOutputStream dos = new DataOutputStream(fos);
      try {
        dos.writeInt(points);
      } catch (IOException e) {
        System.out.println("Erreur d'écriture.");
      }
      try {
        dos.close();
      } catch (IOException e) {
        System.out.println("Erreur de fermeture.");
      }
    } catch (IOException e) {
      System.out.println("Erreur d'ouverture.");
    }
    System.out.println("Meilleur score remplacé !");
  }

  /**
   * Getter du meilleur score du jeu.
   * 
   *
   * @return bestScore le meilleur score en entier.
   **/
  private int getBestScore() {
    int bestScore = 0;
    try {
      FileInputStream fis = new FileInputStream("saves/bestScore.bin");
      DataInputStream dis = new DataInputStream(fis);

      try {
        bestScore = dis.readInt();

      } catch (IOException e) {
        System.out.println("Erreur de lecture.");
      }
      try {
        dis.close();
      } catch (IOException e) {
        System.out.println("Erreur de fermeture.");
      }
    } catch (IOException e) {
      System.out.println("Erreur d'ouverture.");
    }
    return bestScore;
  }

}