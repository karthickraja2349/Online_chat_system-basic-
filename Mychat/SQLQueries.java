public class SQLQueries
{
  public static final String CREATE_USERS_TABLE =
    "CREATE TABLE IF NOT EXISTS users ("+
    "user_id INT PRIMARY KEY AUTO_INCREMENT, "+
    "name VARCHAR(30) NOT NULL,"+ 
    "user_name VARCHAR(30) UNIQUE NOT NULL,"+ 
    "password VARCHAR(255) NOT NULL,"+ 
    "mobile_no BIGINT UNIQUE NOT NULL, "+
    "profile VARCHAR(100),"+ 
    "followers_id VARCHAR(200) DEFAULT NULL)";




public static final String CREATE_ADMIN_TABLE =
    "CREATE TABLE IF NOT EXISTS admins (" +
    "admin_id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
    "admin_name VARCHAR(20) NOT NULL, " +
    "admin_username VARCHAR(20) UNIQUE NOT NULL, " +
    "admin_password VARCHAR(255) NOT NULL)";


  public static final String CREATE_CHAT_TABLE =
	"CREATE TABLE IF NOT EXISTS chats (" +
	"chat_id INT PRIMARY KEY AUTO_INCREMENT, " +
	"user_id INT NOT NULL, " +
	"receiver_id INT NOT NULL, " +
	"message TEXT NOT NULL, " +
	"datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
  "received_message TEXT,"+
  "received_time TIMESTAMP,"+
	"FOREIGN KEY (user_id) REFERENCES users(user_id), " +
	"FOREIGN KEY (receiver_id) REFERENCES users(user_id))";

public static final String CREATE_POST_TABLE =
    "CREATE TABLE IF NOT EXISTS posts(" +
    "post_id int PRIMARY KEY AUTO_INCREMENT,"+
    "user_id int NOT NULL,"+
    "post BLOB NOT NULL,"+
    "FOREIGN KEY(user_id) REFERENCES users(user_id))";

  public static final String INSERT_ADMIN =
    "INSERT INTO admins(admin_name,admin_username,admin_password)Values(?,?,?)";
    
  public static final String INSERT_USERS =
    "INSERT INTO users(name,user_name,password,mobile_no,profile)VALUES(?,?,?,?,?)";
  
  public static final String INSERT_CHATS = 
    "INSERT INTO chats(user_id, receiver_id, message) VALUES (?, ?, ?)";

  public static final String INSERT_POSTS =
    "INSERT INTO posts (user_id, post) VALUES (?, ?)";

 public static final String GET_ADMIN_DETAILS = 
    "SELECT admin_id, admin_name, admin_username, admin_password FROM admins";

public static final String GET_USER_DETAILS =
    "SELECT user_id, name, user_name, password, mobile_no, profile FROM users";

  public static final String GET_CHAT_USER_DETAILS = 
  "SELECT c.chat_id, " +
  "u1.name AS sender_name, " +
  "u2.name AS receiver_name, " +
  "c.message, " +
  "c.datetime " +
  "FROM chats c " +
  "JOIN users u1 ON c.user_id = u1.user_id " +
  "JOIN users u2 ON c.receiver_id = u2.user_id";
  
 public static final String GET_CHAT_DETAILS =
                       "SELECT " +
                       " c.user_id, " +
                       " u1.user_name AS sender_name, " +
                       " c.receiver_id, " +
                       " u2.user_name AS receiver_name, " +
                       " c.message, " +
                       " c.datetime, " +
                       " c.received_message, " +
                       " c.received_time " +
                       "FROM " +
                       "    chats c " +
                       "JOIN " +
                       "    users u1 ON c.user_id = u1.user_id " +
                       "JOIN " +
                       "    users u2 ON c.receiver_id = u2.user_id " +
                       "WHERE " +
                       "    (c.user_id = ? AND c.receiver_id = ?) " +
                       "    OR " +
                       "    (c.user_id = ? AND c.receiver_id = ?) " +
                       "ORDER BY " +
                       "    c.datetime ASC";

    
    public static final String ADMIN_CHECK =
      "SELECT admin_username, admin_password FROM admins WHERE admin_username = ?";

    public static final String USER_CHECK =
      " SELECT user_name,password FROM users WHERE user_name =?";
    
    public static final String SHOW_USER_DETAILS=
      "SELECT name , user_name, password,mobile_no,profile FROM users WHERE user_name=?";
    
    public static final String CHECK_ADMIN_EXISTENCE =
	   "SELECT COUNT(*) FROM admins WHERE admin_UserName = ?"; 
	   
    public static final String CHECK_USER_EXISTENCE =
  	 "SELECT COUNT(*) FROM users WHERE user_name = ?"; 
    
    public static final String GET_USERID_BY_USERNAME =
      "SELECT user_id FROM users WHERE user_name = ?";
    
    public static final String GET_USERNAME_BY_USERID = 
       "SELECT user_name FROM users WHERE user_id = ?";

    public static final String GET_FOLLOWERSID_BY_USERID =
      "SELECT followers_id FROM users WHERE user_id = ?";
   
    public static final String UPDATE_FOLLOWERS =
       "UPDATE users SET followers_id = ? WHERE user_id = ?";	

    public static final String GET_NUMBER =
      "SELECT name, user_name, mobile_no FROM users";

    public static final String CHECK_FOLLOWERS_EXISTENCE =
    "SELECT 1 FROM users " +
    "WHERE (followers_id LIKE ? OR followers_id LIKE ? OR followers_id LIKE ?) AND user_id = ? " +
    "UNION " +
    "SELECT 1 FROM users " +
    "WHERE (followers_id LIKE ? OR followers_id LIKE ? OR followers_id LIKE ?) AND user_id = ?";



   public static final String SELECT_RECEIVED_MESSAGES =
     "SELECT chat_id, user_id, message, datetime FROM chats WHERE receiver_id = ? AND received_message IS NULL ORDER BY datetime";

  public static final String UPDATE_RECEIVED_MESSAGE = 
    "UPDATE chats SET received_message = ?, received_time = ? WHERE chat_id = ?";
   
  public static final String GET_FOLLOWERS_ID =
      "SELECT followers_id FROM users WHERE user_id = ?";
  
 public static final String GET_FOLLOWERS_NAMES =
        "SELECT name FROM users WHERE user_id IN ";

public static final String INSERT_NOTIFICATIONS =
        "INSERT INTO notifications (sender_id, receiver_id, message) VALUES (?, ?, ?)";
 
public static final String VIEW_NOTIFICATIONS =
         "SELECT notification_id, sender_id, message, status FROM notifications WHERE receiver_id = ?";

public static final String ACCEPT_REQUEST =
        "UPDATE notifications SET status = 'accepted' WHERE notification_id = ?";

public static final String GET_SENDER_ID =
       "SELECT sender_id FROM notifications WHERE notification_id = ?";

public static final String GET_RECEIVER_ID =
      "SELECT receiver_id FROM notifications WHERE notification_id = ?";
    
}
