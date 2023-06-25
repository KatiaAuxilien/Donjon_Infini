import java.awt.*;
import javax.swing.*;

/**
 * <b> Sae partie 2 </b> : JPanelBackground()
 *
 * @version 1
 * @author Nassim EL HADDAD & Katia AUXILIEN
 *
 **/
public class JPanelWithBackground extends JPanel 
{
    private Image bg;

    /**
   * @param chemin de type String correspondant au chemin du fichier utilisé pour le fond.
   **/
    public JPanelWithBackground(String chemin) 
    {
        ImageIcon imageIcon = new ImageIcon(chemin);
        bg = imageIcon.getImage();
    }

    /**
    * Permet de peindre le fond de notre JPanel.
    * @param p de type Graphics, pinceau du paintComponent.
    **/
    @Override
    public void paintComponent(Graphics p) 
    {
        super.paintComponent(p);
        p.drawImage(bg, 0, 0, getSize().width, getSize().height, null);
    }
}