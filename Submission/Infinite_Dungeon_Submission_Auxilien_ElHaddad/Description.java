import java.awt.event.*;

/**
 * <b> Sae partie 2 </b> : Description().
 *
 * @version 1
 * @author Nassim EL HADDAD & Katia AUXILIEN
 **/

public class Description extends MouseAdapter {

    /**
   * Methode qui affiche la description de la case quand on la survole.
   *
   * @param evenement de la souris.
   * @return void
   **/
    @Override
    public void mouseEntered(MouseEvent evenement)
    {
        VueCase vueCase = (VueCase) evenement.getSource();
        vueCase.showDescription();
    }   

    /**
   * Methode qui cache la description de la case quand on ne survole plus .
   *
   * @param evenement de la souris.
   * @return void.
   **/
    @Override
    public void mouseExited(MouseEvent evenement)
    {
        VueCase vueCase = (VueCase) evenement.getSource();
        vueCase.hideDescription();
    }  
}