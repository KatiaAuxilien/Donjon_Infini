// import javax.sound.sampled.AudioInputStream;
// import javax.sound.sampled.AudioSystem;
// import javax.sound.sampled.Clip;
// import java.io.File;

// /**
//  * <b> Sae partie 2 </b> : Musique()
//  *
//  * @version 1
//  * @author Nassim EL HADDAD & Katia AUXILIEN
//  */
// public class Musique {
//   private Clip clip;
//   private File musicFile;
//   private AudioInputStream audioInput;


//   /**
//    * Methode playMusic, permettant de lancer la musique.
//    *
//    * @return void.
//    */
//   public void playMusic() {
//     try {
//       musicFile = new File("music/Magic-Escape-Room.wav");
//       audioInput = AudioSystem.getAudioInputStream(musicFile);
//       clip = AudioSystem.getClip();
//       clip.open(audioInput);
//       clip.loop(Clip.LOOP_CONTINUOUSLY);
//       clip.start();
//     } catch (Exception e) {
//       e.printStackTrace();
//     }
//   }

//    /**
//    * Methode stopMusic, permettant de arrêter la musique.
//    *
//    * @return void.
//    */
//   public void stopMusic() {
//     try {
//       clip.stop();
//       audioInput.close();
//     } catch (Exception e) {
//       e.printStackTrace();
//     }
//   }

// }