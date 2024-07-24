package myChat.controller;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AdminAuthentication {

  private static AdminAuthentication adminAuthentication = null;
  private static ChatSystem chatSystem = ChatSystem.getChatSystemInstance();
  
  private AdminAuthentication(){
  
  }
  
  public static AdminAuthentication getAdminAuthentication_Instance(){
       if(adminAuthentication == null){
           adminAuthentication = new AdminAuthentication();
       }
       return adminAuthentication;
  }     
          
  
  
  public void checking() {
         System.out.println("Unauthorized access!");
          playSound("TF043.WAV");
  
  }

  private static boolean authenticateAdmin(String username, String password) {
       
        return adminAuthentication.chatSystem.authenticateAdmin(username,password);
  }

   private static void playSound(String filename) {
    try {
        File file = new File(filename);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
        clip.drain();
        clip.close();
    } catch (Exception e){
        System.err.println("Error playing sound: " + e.getMessage());
    }
  }
}
