package myChat.model;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public abstract class ValidationCheck implements Validate {
     
      
   public  boolean ValidateName(String name){
        String regex = "^[A-Za-z][A-Za-z ]{2,29}$";
        Pattern pattern = Pattern.compile(regex);
        if(name==null){
          return false;
        }
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
   } 
   
  public  boolean ValidatePassword(String password) {
        String regex = "^[A-Za-z][A-Za-z0-9!@#$%^&*]{7}$";
        Pattern pattern = Pattern.compile(regex);
        if (password == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
  }
    
   public  boolean validateNumber(long number) { 
        String numberStr = Long.toString(number);
        String regex = "^[6-9]\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(numberStr);
        return matcher.matches();
  }
  
  public boolean validateAadhar(long aadharNumber){
      String aadharNum = Long.toString(aadharNumber);
      String regex = "^[1-9]\\d{11}$";
      Pattern pattern = Pattern.compile(regex);
      Matcher matcher = pattern.matcher(aadharNum);
      return matcher.matches();
  }    
}
