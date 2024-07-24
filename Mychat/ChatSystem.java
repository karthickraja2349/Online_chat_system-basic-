
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ChatSystem implements AdminServie, UserServie {
    
    private Connection connection = DatabaseConnection.getConnection();
    private Admin admin = Admin.getAdminInstance();
    private User user = User.getUserInstance();
    private Chat chat = Chat.getChatInstance();
    public static ChatSystem chatSystem = null;
    private  static  Scanner input = new Scanner(System.in);
    private ChatSystem(){
        
    }
    
    public static ChatSystem getChatSystemInstance(){
        if(chatSystem == null){
            chatSystem = new ChatSystem();
        }
        return chatSystem;
    }
    
                       //create chat table
    public void createChatTable(){
      try{
         PreparedStatement statement = DatabaseConnection.getPreparedStatement(SQLQueries.CREATE_CHAT_TABLE);       
         statement.execute();
         }
      catch(SQLException e){
         System.out.println("sorry"+ e.getMessage());
      }
  }
  
                                  //create admin table
   public void createAdminTable() {
        try {
            PreparedStatement statement = DatabaseConnection.getPreparedStatement(SQLQueries.CREATE_ADMIN_TABLE);
            statement.execute();
            }
        catch (SQLException e) {
            System.out.println("sorry"+e.getMessage());
        }
    }
    
                                //create user table
    public void createUserTable(){
       try{
           PreparedStatement statement = DatabaseConnection.getPreparedStatement(SQLQueries.CREATE_USERS_TABLE);
           statement.execute();
          }
       catch(SQLException e){
           System.out.println("sorry"+e.getMessage());
       }
    }  
       
       
             //check other admins
  public boolean adminCheck(String user_Name, String password) {
    try (PreparedStatement statement = DatabaseConnection.getPreparedStatement(SQLQueries.ADMIN_CHECK)) {
        statement.setString(1, user_Name);
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String userName = resultSet.getString("admin_username");
                String passWord = resultSet.getString("admin_password");
                
                
                if (user_Name.equals(userName) && password.equals(passWord)) {
                    return true;
                }
            }
        }
    } catch (SQLException e) {
        e.getMessage();
    }
    return false;
}
           //check the admin
   public boolean authenticateAdmin(String userName ,String password){                                          
       if(userName.equals(admin.getRootUserName())&& password.equals(admin.getRootPassword())){                                    
              return true;
        }
        else{
            return false;
       }  
    }
    
    
                      
                          //add admin to the admin table 
  public boolean addAdmin(Admin admin) {
     try {
     
        PreparedStatement existenceStatement = DatabaseConnection.getPreparedStatement(SQLQueries.CHECK_ADMIN_EXISTENCE);
        existenceStatement.setString(1, admin.getUserName());
        ResultSet resultSet = existenceStatement.executeQuery();
        resultSet.next();                                                                                                             
        int count = resultSet.getInt(1);

        if (count > 0) {
            System.out.println("Username already exists. Please choose a different username.");
            return false;
        }

       
        PreparedStatement statement = DatabaseConnection.getPreparedStatement(SQLQueries.INSERT_ADMIN);
      
        statement.setString(1, admin.getName());
        statement.setString(2, admin.getUserName());
        statement.setString(3, admin.getPassword());
        statement.executeUpdate();

        System.out.println("ADMIN ADDED SUCCESSFULLY");
        return true;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
  }
  
                   //add user to the user table
  public boolean addUser(User user){
      try{
          PreparedStatement existenceStatement = DatabaseConnection.getPreparedStatement(SQLQueries.CHECK_USER_EXISTENCE);
          existenceStatement.setString(1,user.getUsersUserName());
          ResultSet resultSet = existenceStatement.executeQuery();
          resultSet.next();
          int count = resultSet.getInt(1);
          
          if(count>0){
            System.out.println("Username already exists. Please choose a different username.");
            return false;
        
          }
          PreparedStatement statement = DatabaseConnection.getPreparedStatement(SQLQueries.INSERT_USERS);
          statement.setString(1,user.getUserName());
          statement.setString(2,user.getUsersUserName());
          statement.setString(3,user.getUserPassword());
          statement.setLong(4,user.getUserMobileNo());
          statement.setString(5,user.getProfile());
          statement.executeUpdate();
          
          System.out.println("user added successfully");
          return true;
          
      }
      catch(SQLException e){
          e.getMessage();
      }
      return false;
  }
  
                        //display admin details
    public List<Admin> viewAdminDetails(){
         List<Admin> AdminDetail = new ArrayList<>();
         try(PreparedStatement statement = DatabaseConnection.getPreparedStatement(SQLQueries.GET_ADMIN_DETAILS);ResultSet resultSet = statement.executeQuery()){
              
              while(resultSet.next()){
                  AdminDetail.add(new Admin(resultSet.getInt("admin_id"), resultSet.getString("admin_name"), resultSet.getString("admin_username"),
                         resultSet.getString("admin_password")));
              }
         }
         catch(SQLException e){
             e.getMessage();
         }
         return AdminDetail;
    }
                        //display user details
  public List<User> viewUserDetails() {
    List<User> userDetail = new ArrayList<>();
    try (PreparedStatement statement = DatabaseConnection.getPreparedStatement(SQLQueries.GET_USER_DETAILS);
         ResultSet resultSet = statement.executeQuery()) {

        while (resultSet.next()) {
            userDetail.add(new User(
               // resultSet.getInt("user_id"),
                resultSet.getString("name"),
                resultSet.getString("user_name"),
                resultSet.getString("password"),
                resultSet.getLong("mobile_no"),
                resultSet.getString("profile")
            ));
        }
    } catch (SQLException e) {
        e.getMessage(); 
    }
    return userDetail;
}
                       //display mobile number
public void getNumber(){
    try (PreparedStatement statement = DatabaseConnection.getPreparedStatement(SQLQueries.GET_NUMBER);
         ResultSet resultSet = statement.executeQuery()) {

      System.out.println("--------------------------");
      while(resultSet.next()){
         String name = resultSet.getString("name");
         String userName = resultSet.getString("user_name");
         Long mobileNumber = resultSet.getLong("mobile_no");
        
        System.out.println("|Name:"+ name + "-userName:" + userName + "-MobileNumber:" + mobileNumber + "|");
      }
       System.out.println("---------------------------");
   }   
   catch(SQLException e) {
       e.getMessage();

   }   

}

// Display particular user detail
public List<User> viewUserDetails(String userName) {
    List<User> userDetail = new ArrayList<>();
    try (PreparedStatement statement = DatabaseConnection.getPreparedStatement(SQLQueries.SHOW_USER_DETAILS)) {
        statement.setString(1, userName);
        
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                userDetail.add(new User(
            
                    resultSet.getString("name"),
                    resultSet.getString("user_name"),
                    resultSet.getString("password"),
                    resultSet.getLong("mobile_no"),
                    resultSet.getString("profile")
                ));
            }
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage()); // Use proper logging instead
    }
    return userDetail;
}

    
                     
    

    
    
    
                      // user check with username & password
    public boolean userCheck(String user_Name, String password) {
       try (PreparedStatement statement = DatabaseConnection.getPreparedStatement(SQLQueries.USER_CHECK)) {
        statement.setString(1, user_Name); 
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String userName = resultSet.getString("user_name");
                String passWord = resultSet.getString("password");
                if (user_Name.equals(userName) && password.equals(passWord)) {
                    return true;
                }
            }
        }
    } 
    catch (SQLException e) {
        e.getMessage();
    }
    return false;
  }
        // Find user ID by username
    public long findUserIdByUsername(String username) {
        
        long userId = -1;

        try (PreparedStatement statement = DatabaseConnection.getPreparedStatement(SQLQueries.GET_USERID_BY_USERNAME)) {
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                userId = rs.getLong("user_id");
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return userId;
    }
        
          //find username by userid
  public String findUsernameByUserId(long id){
      String userName="" ;
       int userId = (int)id;
       try(PreparedStatement statement = DatabaseConnection.getPreparedStatement(SQLQueries.GET_USERNAME_BY_USERID)){
         statement.setInt(1,userId);
         ResultSet resultSet = statement.executeQuery();
         if(resultSet.next()){
            userName = resultSet.getString("user_name") ;
        }
      }
      catch(SQLException e) {
            e.getMessage();
      }
      return userName;
  }

 public boolean isFollowerAlreadyExists(long userId, long followerId) {
    boolean exists = false;
     String userIdStr = String.valueOf(userId);
    String followerIdStr = String.valueOf(followerId);

    try (PreparedStatement statement = DatabaseConnection.getPreparedStatement(SQLQueries.CHECK_FOLLOWERS_EXISTENCE)) {
           String userPattern1 = "%," + userIdStr + ",%"; // Middle
        String userPattern2 = userIdStr + ",%"; // Start
        String userPattern3 = "%," + userIdStr; // End

        String followerPattern1 = "%," + followerIdStr + ",%"; // Middle
        String followerPattern2 = followerIdStr + ",%"; // Start
        String followerPattern3 = "%," + followerIdStr; // End

        // Set parameters for userId checking followerId
        statement.setString(1, followerPattern1);
        statement.setString(2, followerPattern2);
        statement.setString(3, followerPattern3);
        statement.setLong(4, userId);

        // Set parameters for followerId checking userId
        statement.setString(5, userPattern1);
        statement.setString(6, userPattern2);
        statement.setString(7, userPattern3);
        statement.setLong(8, followerId);

        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            exists = true;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return exists;
}

public void addFollower(String userName, String friendName) {
    long userId = findUserIdByUsername(userName);
    long friendId = findUserIdByUsername(friendName);

    if (userId == friendId) {
        System.out.println("You cannot follow yourself.");
        return;
    }

    if (isFollowerAlreadyExists(userId, friendId)) {
        System.out.println("This user is already a follower.");
    } else {
        sendFollowRequest(userId, friendId);
        System.out.println("Follow request sent successfully.");
    }
}





        //add follower
/*public void addFollower(long userId, long followerId) {
    if (isFollowerAlreadyExists(userId, followerId)) {
        System.out.println("This user is already a follower.");
        return;
    }

    String followers = "";
    try (PreparedStatement statement = DatabaseConnection.getPreparedStatement(SQLQueries.GET_FOLLOWERSID_BY_USERID)) {
        statement.setLong(1, userId);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            followers = rs.getString("followers_id");
        }

        if (followers == null || followers.isEmpty()) {
            followers = Long.toString(followerId);
        } else {
            followers = followers + "," + followerId;
        }

        try (PreparedStatement updateStatement = DatabaseConnection.getPreparedStatement(SQLQueries.UPDATE_FOLLOWERS)) {
            updateStatement.setString(1, followers);
            updateStatement.setLong(2, userId);
            updateStatement.executeUpdate();
        }
    } catch (SQLException e) {
        e.getMessage(); 
    }
}
        */
        // Remove follower
    public void removeFollower(long userId, long followerId) {
       
        String followers = "";

        try (PreparedStatement statement = DatabaseConnection.getPreparedStatement(SQLQueries.GET_FOLLOWERSID_BY_USERID)) {
            statement.setLong(1, userId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                followers = rs.getString("followers_id");
            }

            if (followers != null && !followers.isEmpty()) {
                List<String> followersList = new ArrayList<>(Arrays.asList(followers.split(",")));
                followersList.remove(Long.toString(followerId));
                followers = String.join(",", followersList);
            }

            
            try (PreparedStatement updateStatement = DatabaseConnection.getPreparedStatement(SQLQueries.UPDATE_FOLLOWERS)) {
                updateStatement.setString(1, followers);
                updateStatement.setLong(2, userId);
                updateStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.getMessage();
        }
    }

              //message to friend
    public void message(long senderId, long receiverId,String message){
      try{
        PreparedStatement statement = DatabaseConnection.getPreparedStatement(SQLQueries.INSERT_CHATS);
        int senderIdNumber = (int)senderId;
        int reeiverIdNumber =(int)receiverId;

         statement.setInt(1,senderIdNumber);
         statement.setInt(2,reeiverIdNumber);
         statement.setString(3,message);
         
         statement.executeUpdate();
        
        System.out.println("message sent successully");  
    }
    catch(SQLException e){
         e.getMessage();
   }
    
}
                  // display messages
     public void displayReceivedMessages(long userId, String userName) {
        try {
            PreparedStatement statement = DatabaseConnection.getPreparedStatement(SQLQueries.SELECT_RECEIVED_MESSAGES);
            int userIdNumber = (int) userId;

            statement.setInt(1, userIdNumber);
            ResultSet resultSet = statement.executeQuery();

            System.out.println("Received messages:");
            while (resultSet.next()) {
                int chatId = resultSet.getInt("chat_id");
                int senderId = resultSet.getInt("user_id");
                String message = resultSet.getString("message");
                Timestamp datetime = resultSet.getTimestamp("datetime");

                String senderName = findUsernameByUserId(senderId);
                System.out.println("From: " + senderName + " at " + datetime + "\nMessage: " + message + "\n");

                System.out.println("Type your reply:");
                String replyMessage = input.nextLine();

                updateReceivedMessage(chatId, replyMessage);
                message(userId, senderId, replyMessage); 
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
                     //update message
   public void updateReceivedMessage(int chatId, String receivedMessage) {
        try {
            PreparedStatement statement = DatabaseConnection.getPreparedStatement(SQLQueries.UPDATE_RECEIVED_MESSAGE);
            Timestamp receivedTime = new Timestamp(System.currentTimeMillis());

            statement.setString(1, receivedMessage);
            statement.setTimestamp(2, receivedTime);
            statement.setInt(3, chatId);

            statement.executeUpdate();
           
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
                          // insert post
  public void insertPost(int userId,String fileName){

     try{
            PreparedStatement statement = DatabaseConnection.getPreparedStatement(SQLQueries.INSERT_POSTS);
            statement.setInt(1, userId); 
            File file = new File(fileName);
            FileInputStream inputStream = new FileInputStream(file);
            statement.setBinaryStream(2, inputStream);

            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println("Image inserted successfully.");
            }
            statement.close();
            inputStream.close();
        } catch (SQLException | IOException e) {
            e.getMessage();
        }  



     } 

                          //chat history
public List<Chat> getChatHistory(int userId, int friendId) {
    List<Chat> chatHistory = new ArrayList<>();

    try (PreparedStatement statement = DatabaseConnection.getPreparedStatement(SQLQueries.GET_CHAT_DETAILS)) {

            statement.setInt(1, userId);
            statement.setInt(2, friendId);
            statement.setInt(3, friendId);
            statement.setInt(4, userId);

          try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String senderName = resultSet.getString("sender_name");
                    String receiverName = resultSet.getString("receiver_name");
                    String message = resultSet.getString("message");
                    Timestamp datetime = resultSet.getTimestamp("datetime");
                    String receivedMessage = resultSet.getString("received_message");
                    Timestamp receivedTime = resultSet.getTimestamp("received_time");

                    Chat chat = new Chat(senderName, receiverName, message, datetime, receivedMessage, receivedTime);
                    chatHistory.add(chat);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chatHistory;
    }
            //show followers
public List<String> getFollowersNames(int userId) {
        List<String> followersNames = new ArrayList<>();

        
        String getFollowerNamesQuery = "SELECT name FROM users WHERE user_id IN ";

        try (PreparedStatement followersStatement = DatabaseConnection.getPreparedStatement(SQLQueries.GET_FOLLOWERS_ID)) {

            followersStatement.setInt(1, userId);

            try (ResultSet resultSet = followersStatement.executeQuery()) {
                if (resultSet.next()) {
                    String followersIds = resultSet.getString("followers_id");

                   
                    getFollowerNamesQuery += "(" + followersIds + ")";

                    try (Statement followerNamesStmt = connection.createStatement();
                         ResultSet followersRs = followerNamesStmt.executeQuery(getFollowerNamesQuery)) {

                        while (followersRs.next()) {
                            followersNames.add(followersRs.getString("name"));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.getMessage();
        }

        return followersNames;
    }

   public void sendFollowRequest(long senderId, long receiverId) {
    String message = "User " + findUsernameByUserId(senderId) + " has sent you a follow request.";
    try (PreparedStatement statement = DatabaseConnection.getPreparedStatement(SQLQueries.INSERT_NOTIFICATIONS)) {
        statement.setLong(1, senderId);
        statement.setLong(2, receiverId);
        statement.setString(3, message);
        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
public void viewNotifications(long userId) {
    try (PreparedStatement statement = DatabaseConnection.getPreparedStatement(SQLQueries.VIEW_NOTIFICATIONS)) {
        statement.setLong(1, userId);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            int notificationId = rs.getInt("notification_id");
            long senderId = rs.getLong("sender_id");
            String message = rs.getString("message");
            String status = rs.getString("status");
            System.out.println("Notification ID: " + notificationId);
            System.out.println("Sender: " + findUsernameByUserId(senderId));
            System.out.println("Message: " + message);
            System.out.println("Status: " + status);
            System.out.println("-----------------------------");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void acceptFollowRequest(int notificationId) {
    try (PreparedStatement statement = DatabaseConnection.getPreparedStatement(SQLQueries.ACCEPT_REQUEST)) {
        statement.setInt(1, notificationId);
        statement.executeUpdate();

        // Add follower relationship
        long senderId = getSenderIdByNotificationId(notificationId);
        long receiverId = getReceiverIdByNotificationId(notificationId);
        addFollowerToDatabase(senderId, receiverId);
        
        System.out.println("Follow request accepted successfully.");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public long getSenderIdByNotificationId(int notificationId) {
    long senderId = -1;
    try (PreparedStatement statement = DatabaseConnection.getPreparedStatement(SQLQueries.GET_SENDER_ID)) {
        statement.setInt(1, notificationId);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            senderId = rs.getLong("sender_id");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return senderId;
}

public long getReceiverIdByNotificationId(int notificationId) {
    long receiverId = -1;
    try (PreparedStatement statement = DatabaseConnection.getPreparedStatement(SQLQueries.GET_RECEIVER_ID)) {
        statement.setInt(1, notificationId);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            receiverId = rs.getLong("receiver_id");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return receiverId;
}

public void addFollowerToDatabase(long userId, long followerId) {
    try (PreparedStatement statement = DatabaseConnection.getPreparedStatement(
            "UPDATE users SET followers_id = CONCAT(IFNULL(followers_id, ''), ?) WHERE user_id = ?")) {
        String followerPattern = followerId + ",";
        statement.setString(1, followerPattern);
        statement.setLong(2, userId);
        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}



}
 





