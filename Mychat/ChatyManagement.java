import java.util.List;
import java.util.Scanner;

public class ChatyManagement extends ValidationCheck
{

  private ChatSystem chatSystem = ChatSystem.getChatSystemInstance ();
  private Admin admin = Admin.getAdminInstance ();
  private User user = User.getUserInstance ();
  private Chat chat = Chat.getChatInstance();
  private static Scanner input = new Scanner(System.in);
  private  AdminAuthentication adminAuthentication =AdminAuthentication.getAdminAuthentication_Instance ();
  private  DisplayImageAwt displayImageAwt = DisplayImageAwt.getDisplayImageAwtInstance();

  private static ChatyManagement chatyManagement = new ChatyManagement ();


                     // admin login
  public static boolean LoginAdmin ()
  {
	input.nextLine ();
	System.out.println ("--------------------");
	System.out.println ("Enter the User Name: ");
	String userName = input.nextLine ();
	System.out.println ("Enter the Password: ");
	String password = input.nextLine ();
    System.out.println ("--------------------");

	boolean checkAdmin =chatyManagement.chatSystem.adminCheck (userName, password);
	if (checkAdmin)
	  {
		System.out.println ("welcome:" + userName);
	  }
	else
	  {
		System.out.println ("Invalid Login credentials ");
		chatyManagement.adminAuthentication.checking ();
		return false;
	  }
	return true;
  }
  
  
                    //root admin login
  public static boolean getAdminLoginDetails ()
  {
	input.nextLine ();
	System.out.println ("--------------------");
	System.out.println ("Enter the User Name: ");
	String userName = input.nextLine ();
	System.out.println ("Enter the Password: ");
	String password = input.nextLine ();
	System.out.println ("--------------------");
	boolean adminCheck = chatyManagement.chatSystem.authenticateAdmin (userName, password);
	if (adminCheck)
	  {							
		System.out.println ("Welcome : " +chatyManagement.admin.getRootName ());
	  }
	else
	  {
		System.out.println ("Invalid login credentials");
		return false;
	  }
	return true;
  }
                    
                    // add admin
 public static void getAdminDetails() {
    System.out.println("Enter details for the given fields. All names should contain a minimum of 4 and a maximum of 30 characters. The name should only be within A-Z/a-z. The password must start with alphabets, may or may not contain numbers, but must contain at least one special character");
    System.out.println("--------------------");
    
    String adminName = chatyManagement.getValidatedInput("Enter the Admin Name");
    String adminUserName = chatyManagement.getValidatedInput("Enter the userName of the Admin");
    String password = chatyManagement.getValidatedPassword("Enter the password");
    
    Admin admin = new Admin(adminName, adminUserName, password);
    chatyManagement.chatSystem.addAdmin(admin);
    System.out.println("-------------------------");
}

  
                 //name validation
                 
    private  String getValidatedInput(String prompt) {
        
        System.out.println(prompt);
        String inputString = input.nextLine();
        while (!ValidateName(inputString)) {
            System.out.println("Invalid input.please  correctly " + prompt);
            inputString = input.nextLine();
        }
        return inputString;
    }
                                          //password validation
   private String getValidatedPassword(String prompt){
      System.out.println(prompt);
      String password = input.nextLine();
      while(!ValidatePassword(password)){
          System.out.println("Invalid password.please  correctly" + prompt);
          password = input.nextLine();
     }
     return password;
   }         
                                          //mobilenumber validation
   private String getValidatedMobileNumber(String prompt){
       System.out.println(prompt);
       long mobileNumber = input.nextLong();
       while(!validateNumber(mobileNumber)){
         System.out.println("Invalid syntax for mobile number.please correctly " + prompt);
         mobileNumber=input.nextLong();
       }
       return Long.toString(mobileNumber);
   }
                     
                     //show admins
    public  void showAdmins(){
        List<Admin> adminDetails =  chatSystem.viewAdminDetails();
        System.out.println("----------THE ADMINS MANAGING OUR CHATY MANAGEMENT ARE---------");
        for(Admin admin : adminDetails){
            System.out.println(admin);
        }
        System.out.println("---------THESE ARE THE ADMINS WORKING IN OUR CHATY MANAGEMENT----");
    } 
    
                    //show users
    public void showUsers() {
    List<User> userDetails = chatyManagement.chatSystem.viewUserDetails();
    System.out.println("----------THE USERS USING OUR CHATY ARE-----------");
    for (User user : userDetails) {
        System.out.println(user);
    }
    System.out.println("----------THESE ARE THE USERS USING OUR CHATY------");
  }
   
                  //get mobile number for other purposes like sms,update
    public void getMobileNumber(){
      chatyManagement.chatSystem.getNumber();
     
   }

                  //show particular user
  public void showUsers(String userName){
      List<User> userDetail = chatyManagement.chatSystem.viewUserDetails(userName);
      System.out.println("-------your profile is------");
      for(User user : userDetail){
          System.out.println(user);
      }
      System.out.println("-----------------------------");
  }
                   // view friend profile
  public void viewFriendProfile(String userName){
     System.out.println("Enter your friend name:");
     String friendName = input.nextLine();
    
      long userId = chatyManagement.chatSystem.findUserIdByUsername(userName);
      long friendId = chatyManagement.chatSystem.findUserIdByUsername(friendName);
     
     if(chatyManagement.chatSystem.isFollowerAlreadyExists(userId,friendId)){
           showUsers(friendName);
      }
     else{
       System.out.println("You are not a Friend to:"+ friendName);
    }

}

                    
                   //login user
    public  boolean userLogin(String username, String password)throws Exception {
                                                                
     try {    
        boolean check = chatyManagement.chatSystem.userCheck(username, password); 
        if (check) {
            System.out.println("Welcome " + username);
            
        } else {
            System.out.println("Invalid username or password, please check before login");
            
            return false;
        }
    } 
    catch (Exception e) {
         e.getMessage();
    }  
    return true;
  }
             //new user
  
  public  void newUser(){
            System.out.println ("--------------------");
            System.out.println("1.SIGN UP");
            System.out.println("-------Welcome to chaty--------");
            System.out.println("------------------------------");
            System.out.println("Enter details for the given fields. All names should contain a minimum of 4 and a maximum of 30 characters. The name should only be within A-Z/a-z.The password must start with alphabets may or may not contains numbers but must contain atleast one special character. The Mobile Number should be contains 10digit");
            System.out.println("---------------------------------");
            
            String Name          = chatyManagement.getValidatedInput("Enter the customer Name");
            String userName      = chatyManagement.getValidatedInput("Enter the userName of the customer");
            String password      = chatyManagement.getValidatedPassword("Enter the password");
            System.out.println("Enter your profile:");
            String profile       = input.nextLine();
            String mobileNo      = chatyManagement.getValidatedMobileNumber("Enter the mobile number");
            
            
            long MobileNumber = Long.parseLong(mobileNo);
            
            User user  = new User(Name,userName,password,MobileNumber,profile);
            chatyManagement.chatSystem.addUser(user);
            
            System.out.println("REGISTRATION SUCCESSFULL");
            System.out.println("------------------------------");
          
    }
    
    
    // Add follower to user by username
    public void addFollowerToUserByUsername(String username ) {
        input.nextLine(); 
      
        System.out.println("Enter the username of the friend to follow: ");
        String followerUsername = input.nextLine();

        chatyManagement.chatSystem.addFollower(username, followerUsername);
    }
    
    // Add follower by username
    /*public void addFollowerByUsername(String username, String followerUsername) {
        long userId =chatyManagement.chatSystem. findUserIdByUsername(username);
        long followerId =chatyManagement.chatSystem. findUserIdByUsername(followerUsername);

        if (userId != -1 && followerId != -1) {
            chatyManagement.chatSystem.addFollower(userId, followerId);
            System.out.println("Follower added successfully.");
        } else {
            System.out.println("Invalid username(s) provided.");
        }
    }*/
    
    
    // Remove follower from user by username
    public void removeFollowerFromUserByUsername(String username) {
        input.nextLine(); 
       
        System.out.println("Enter the username of the friend to unfollow: ");
        String followerUsername = input.nextLine();

        removeFollowerByUsername(username, followerUsername);
    }
    
    
    // Remove follower by username
    public void removeFollowerByUsername(String username, String followerUsername) {
        long userId =chatyManagement.chatSystem. findUserIdByUsername(username);
        long followerId =chatyManagement.chatSystem. findUserIdByUsername(followerUsername);

        if (userId != -1 && followerId != -1) {
           chatyManagement.chatSystem. removeFollower(userId, followerId);
            System.out.println("Follower removed successfully.");
        } else {
            System.out.println("Invalid username(s) provided.");
        }
    }


             //start chat
  public void startChat(String userName){
      System.out.println("Enter your friend username:");
      String friendName = input.nextLine();
      System.out.println("Type your message:");
      String senderMessage = input.nextLine();
      
     long senderId = chatyManagement.chatSystem.findUserIdByUsername(userName);
     long receiverId = chatyManagement.chatSystem.findUserIdByUsername(friendName);
    
     if (senderId != -1 && receiverId != -1) {
        chatyManagement.chatSystem. message(senderId, receiverId,senderMessage);
         
    } 
   else {
         System.out.println("Invalid username(s) provided.");
        } 

 }
      //notification check
 public void checkMessages(String userName) {
        long userId = chatyManagement.chatSystem.findUserIdByUsername(userName);
        if (userId != -1) {
           chatyManagement.chatSystem.displayReceivedMessages(userId,userName);
        } else {
            System.out.println("Invalid username provided.");
        }
}
              //post putting
public void post(String userName){
      long id = chatyManagement.chatSystem.findUserIdByUsername(userName);
      int userId = (int)id;
      System.out.println("Enter the image name to post");
      String filename = input.nextLine();
      if(userId != -1){
        chatyManagement.chatSystem.insertPost(userId,filename);
     }
}
            //view our post
public void viewPost(String userName){
   long id = chatyManagement.chatSystem.findUserIdByUsername(userName);
   int userId = (int)id;
   chatyManagement.displayImageAwt.viewPost(userId); 
}
           //view our friend post
public void viewFriendPost(String userName){
   System.out.println("Enter your friend Name");
   String friendName = input.nextLine();
   
   long userId = chatyManagement.chatSystem.findUserIdByUsername(userName);
   long friendId = chatyManagement.chatSystem.findUserIdByUsername(friendName);
  
  if(chatyManagement.chatSystem.isFollowerAlreadyExists(userId,friendId)){
       viewPost(friendName);
  }
  else{
    System.out.println("You are not a friend to:" + friendName);
  }
     

}

             //chat History
  public void chatHistory(String userName){
      System.out.println("Enter your friend name");
      String friendName = input.nextLine();

      long userId = chatyManagement.chatSystem.findUserIdByUsername(userName);
      long friendId = chatyManagement.chatSystem.findUserIdByUsername(friendName);
     
     if(chatyManagement.chatSystem.isFollowerAlreadyExists(userId,friendId)){
           showHistory((int)userId,(int)friendId);
      }
      else{
       System.out.println("NO chat between you and :"+ friendName);
      }        
       
}         // show history
private void showHistory(int userId,int friendId){
    List<Chat> chatHistory = chatyManagement.chatSystem.getChatHistory(userId,friendId);
     if (chatHistory != null && !chatHistory.isEmpty()) {
        System.out.println("THE CHAT BETWEEN YOU AND YOUR FRIEND ARE");
        System.out.println(chatyManagement.chat.formatChatHistory(chatHistory));
         System.out.println("Chat Ended");
    }
    else {
        System.out.println("No chat history available.");
    }
  
}
           //friendlist
public void viewFriends(String userName){
     long userId = chatyManagement.chatSystem.findUserIdByUsername(userName);
     System.out.println("Your friends are as Follows:");
     List<String> friensList =chatyManagement.chatSystem.getFollowersNames((int)userId);
     System.out.println(friensList);
}

public void viewNotifications(String userName){
   long userId = chatyManagement.chatSystem.findUserIdByUsername(userName);
   chatyManagement.chatSystem.viewNotifications(userId);

}

public void acceptRequest(String userName){
    System.out.println("Enter the notification ID to accept:");
    int notificationId = input.nextInt();
    input.nextLine(); 
    chatyManagement.chatSystem.acceptFollowRequest(notificationId);

}
}