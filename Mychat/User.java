public class User
{
  private int user_Id;
  private String user_Name;
  private String user_UserName;
  private String user_Password;
  private long user_MobileNo;
  private String user_profile;

  public static User user = null;

  private User ()
  {

  }


  public User (int user_Id,String user_Name, String user_UserName, String user_Password,
			   long user_MobileNo,String profile)
  {
	this.user_Id = user_Id;
	this.user_Name = user_Name;
	this.user_UserName = user_UserName;
	this.user_Password = user_Password;
	this.user_MobileNo = user_MobileNo;
  this.user_profile = profile;
  }

  public User (String user_Name, String user_UserName, String user_Password,
			   long user_MobileNo, String profile)
  {
	this.user_Name = user_Name;
	this.user_UserName = user_UserName;
	this.user_Password = user_Password;
	this.user_MobileNo = user_MobileNo;
	this.user_profile = profile;
  }

  public static User getUserInstance ()
  {
	if (user == null)
	  {
		user = new User ();
	  }
	return user;
  }

  public int getUserId ()
  {
	return user_Id;
  }

  public String getUserName ()
  {
	return user_Name;
  }

  public String getUsersUserName ()
  {
	return user_UserName;
  }

  public String getUserPassword ()
  {
	return user_Password;
  }

  public long getUserMobileNo ()
  {
	return user_MobileNo;
  }

  public String getProfile ()
  {
	return user_profile;
  }

  public String toString ()
  {
	StringBuilder sb = new StringBuilder ();
	sb.append ("-------------------------------------------------\n");
	sb.append (String.format ("| %-20s : %-25s |\n", "User Id is", "*******" ));
	sb.append (String.format ("| %-20s : %-25s |\n", "User Name is", getUserName ()));
	sb.append (String.format ("| %-20s : %-25s |\n", "User's userName is",  getUsersUserName ()));
	sb.append (String.format ("| %-20s : %-25s |\n", "User password is", "********"));
	sb.append (String.format ("| %-20s : %-25s |\n", "User mobileNo is", "*********"));
	sb.append (String.format ("| %-20s : %-25s |\n", "User profile is",  getProfile ()));
	sb.append ("------------------------------------------------\n");
	return sb.toString ();

  }



}