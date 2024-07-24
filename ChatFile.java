package myChat;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class ChatFile{
     
     public static ChatFile chatFile= null;
     
     private ChatFile(){
     
     }
     
     public static ChatFile getChatFileInstance(){
         if(chatFile== null){
             chatFile= new ChatFile();
         }
         return  chatFile;
     }
     
     public void userMenu(){
           FileInputStream fileInputStream = null;
           BufferedInputStream bufferedInputStream = null;
           try{
               fileInputStream = new FileInputStream("Chaty.txt");
               bufferedInputStream = new BufferedInputStream(fileInputStream);
               int i = 0;
               while ((i = bufferedInputStream.read()) != -1){
                  char ch =(char)i;
                  System.out.print(ch);
               }
          }
          catch(IOException e){
             e.getMessage();
          }
          finally{
              try{
                if (bufferedInputStream != null) {
                  bufferedInputStream.close();
                }
              }
              catch(IOException e){
                  e.getMessage();
             }   
        }
    }
    
    public void helpMenu(){
         FileInputStream fileInputStream = null;
         BufferedInputStream bufferedInputStream = null;
           try{
               fileInputStream = new FileInputStream("Help.txt");
               bufferedInputStream = new BufferedInputStream(fileInputStream);
               int i = 0;
               while ((i = bufferedInputStream.read()) != -1){
                  char ch =(char)i;
                  System.out.print(ch);
               }
          }
          catch(IOException e){
             e.getMessage();
          }
          finally{
              try{
                if (bufferedInputStream != null) {
                  bufferedInputStream.close();
                }
              }
              catch(IOException e){
                  e.getMessage();
             }   
        }
    }
    
    
 }                   

