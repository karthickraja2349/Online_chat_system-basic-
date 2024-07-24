package myChat;
import java.util.Scanner;
import myChat.view.ChatyManagement;
import myChat.controller.ChatSystem;
import myChat.model.Admin;
import myChat.model.User;

public class Chaty extends ChatyManagement{
    
    private Admin admin = Admin.getAdminInstance();
    private User user = User.getUserInstance();
    private ChatSystem chatSystem = ChatSystem.getChatSystemInstance();
    
    private static Scanner input = new Scanner(System.in);
    private static Chaty chaty = new Chaty();
    private static  ChatyManagement chatyManagement = new ChatyManagement();
    
    public static void main(String[]args){
        
        while(true){
            displayMenu();
            
            while(!input.hasNextInt()){
                System.out.println("Invalid input.please enter a number");
                input.next();
            }
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    adminMenu();
                    break;
                case 2:
                    userMenu();
                    break;
                case 3:
                    exitMenu();
                    break;
                default:
                    System.out.println("Invalid choice, Please Select Valid choice as per list. You Enter : " + choice);
            }
            
        }
    }
    
    public static void displayMenu(){
        System.out.println("Hello.Welcome to chaty!");
        System.out.println("Without communication,The world was deaf and dumb");
        System.out.println("-------------------------------");
        System.out.println("|option|  MainMenu            |");
        System.out.println("|--------+--------------------|");
        System.out.println("|  1   |  Admin               |");
        System.out.println("|  2   |  User                |");
        System.out.println("|  3   |  Exit                |");
        System.out.println("-------------------------------");
        
        System.out.println("Enter your choice");
        
        
    }
    
    public static void adminMenu(){
         while (true) {
            System.out.println("--------------------------------------------");
            System.out.println("| Option |          Admin Mainmenu         |");
            System.out.println("|--------+---------------------------------|");
            System.out.println("|   1    |Login Admin                      |");
            System.out.println("|   2    |Add Admin                        |");
            System.out.println("|   3    |Back to Mainmenu                 |");
            System.out.println("--------------------------------------------");
            System.out.println("Enter the Choice");
            
            int adminChoice = input.nextInt();
            
            switch(adminChoice){  
                case 1:     
                    if(LoginAdmin()){
                      adminSubMenu();
                     } 
                    break;
                case 2:
                     if(chaty.getAdminLoginDetails()){
		                getAdminDetails ();
		             }
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice, Please Select Valid choice: " + adminChoice);
            }
        }
            
            
    }
    
    public static void userMenu() {
        while (true) {
            System.out.println("--------------------------------------------");
            System.out.println("| Option |       User Mainmenu             |");
            System.out.println("|--------+---------------------------------|");
            System.out.println("|   1    |Login User                       |");
            System.out.println("|   2    |New User                         |");
            System.out.println("|   3    |Back to Mainmenu                 |");
            System.out.println("--------------------------------------------");
            System.out.println("Enter the choice");

            int userChoice = input.nextInt();
           

            switch (userChoice) {
                case 1:
                    input.nextLine();
                    System.out.println("--------------------");
                    System.out.println("1.LOGIN");
                    System.out.println("Enter user name"); 
                    String userName = input.nextLine();
                    System.out.println("Enter password");      
                    String password = input.nextLine(); 
                    try{
                    if(chaty.userLogin(userName,password)){
                        userSubMenu(userName);
                    }
                    }
                    catch(Exception e){
                       e.getMessage();
                    }   
                  
                    break;
                case 2:
                    chaty.newUser();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice, Please Select Valid choice: " + userChoice);
            }
        }
    }
    
    public static void exitMenu() {
        while (true) {
            System.out.println("--------------------------------------------");
            System.out.println("| Option |            Exit Menu            |");
            System.out.println("|--------+---------------------------------|");
            System.out.println("|   1    |Are you Sure?(If it, press 1)    |");
            System.out.println("|   2    |Stay Here .(press 2)             |");
            System.out.println("--------------------------------------------");
            System.out.println("Enter the choice");

            int exitChoice = input.nextInt();

            switch (exitChoice) {
                case 1:
                    System.out.println("Thankyou for visiting us ! Be a talkative");
                    System.exit(0);
                case 2:
                    return;
                default:
                    System.out.println("Invalid choice, Please Select Valid choice: " + exitChoice);
            }
        }
     }
     
    public static void adminSubMenu(){
        while(true){
            System.out.println("--------------------------------------------");
            System.out.println("| Option |            Admin SubMenu        |");
            System.out.println("|--------+---------------------------------|"); 
            System.out.println("|   1    |  View Admin Details             |");
            System.out.println("|   2    |  View user Details              |");
            System.out.println("|   3    |  View user mobile Number        |");
            System.out.println("|   4    |  Back to admin menu             |");
            System.out.println("--------------------------------------------");
            System.out.println("Enter the Choice: ");
           
            int Choice = input.nextInt();
            input.nextLine();
           
            switch (Choice) {
                case 1:
                    chaty.showAdmins();
                    break;
                case 2:
                    chaty.showUsers();
                    break;
                case 3:
                     chaty.chatyManagement.getMobileNumber();
                      break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice , please enter a valid choice");
            }  
         }
    }
    
    public static void userSubMenu(String userName) {
    while (true) {
       System.out.println("--------------------------------------------"); 
       System.out.println("| Option |            User SubMenu         |");
       System.out.println("|--------+---------------------------------|");
       System.out.println("|   1    |   View Profile                  |");
       System.out.println("|   2    |   Add Follower                  |");
       System.out.println("|   3    |   Remove Follower               |");
       System.out.println("|   4    |   Start Chat                    |");
       System.out.println("|   5    |   check message                 |");
       System.out.println("|   6    |   Share post                    |");
       System.out.println("|   7    |   View post                     |");
       System.out.println("|   8    |   View friend's post            |");
       System.out.println("|   9    |   Chat History                  |");
       System.out.println("|  10    |   Friend's list                 |");
       System.out.println("|  11    |   view Friends profile          |");
       System.out.println("|  12    |   View Notifications            |");
       System.out.println("|  13    |   Accept Follow Request         |");
       System.out.println("|  14    |   Back to Main Menu             |");
       System.out.println("--------------------------------------------");
       System.out.println("Enter your choice: ");
        int userChoice = input.nextInt();
        
        switch(userChoice) {
            case 1:
                chaty.showUsers(userName);
                break;
            case 2:
                chaty.chatyManagement.addFollowerToUserByUsername(userName);
                break;
            case 3:
                chaty.chatyManagement.removeFollowerFromUserByUsername(userName);
                break;
            case 4:
                chaty.chatyManagement.startChat(userName);
                break;
            case 5:
                chaty.chatyManagement.checkMessages(userName);
                 break;
            case 6:
                chaty.chatyManagement.post(userName);
                break;
            case 7:
                chaty.chatyManagement.viewPost(userName);
                break;
            case 8: 
                chaty.chatyManagement.viewFriendPost(userName);
                break;
            case 9:
                chaty.chatyManagement.chatHistory(userName);
                break;
            case 10:
                chaty.chatyManagement.viewFriends(userName);
                break;
            case 11:
                chaty.chatyManagement.viewFriendProfile(userName);
                break;
           case 12:
               chaty.chatyManagement.viewNotifications(userName);
               break;
           case 13:
              chaty.chatyManagement.acceptRequest(userName);
              break;
            case 14:
                return;
            default:
                System.out.println("Invalid choice, please enter a valid choice.");
        }
    }
}

    }
    
    
    
    
    
    
    
    
