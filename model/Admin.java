package myChat.model;
public class Admin
{

  private String name;
  private String user_Name;
  private String password;
  private int id;

  private final String main_Admin_name = "root";
  private final String main_Admin_User_Name = "MainAdmin";
  private final String main_Admin_Password = "MainAdmin123";

  public static Admin admin = null;

    public Admin (int id, String name, String user_Name, String password)
  {
	this.id = id;
	this.name = name;
	this.user_Name = user_Name;
	this.password = password;
  }
    public Admin(String name, String user_Name,String password){
        this.name = name;
      	this.user_Name = user_Name;
	      this.password = password;
  }
    
  private Admin ()
  {
  }

  public static Admin getAdminInstance ()
  {
	if (admin == null)
	  {
		admin = new Admin ();
	  }
	return admin;
  }

  public String getRootName ()
  {
	return main_Admin_name;
  }

  public String getRootUserName ()
  {
	return main_Admin_User_Name;
  }

  public String getRootPassword ()
  {
	return main_Admin_Password;
  }

  public void setNewUser (String newName)
  {
	this.name = newName;
  }

  public void setId (int id)
  {
	this.id = id;
  }

  public void setNewUserName (String userName)
  {
	this.user_Name = userName;
  }

  public void setPassword (String newPassword)
  {
	this.password = newPassword;
  }

  public int getId ()
  {
	return id;
  }

  public String getName ()
  {
	return name;
  }

  public String getUserName ()
  {
	return user_Name;
  }

  public String getPassword ()
  {
	return password;
  }
  
  public String toString(){
     	StringBuilder sb = new StringBuilder ();
     	sb.append ("----------------------------------------------------\n");
    	sb.append (String.format ("| %-20s : %-25s |\n", "Admin Id is", getId ()));
    	sb.append (String.format ("| %-20s : %-25s |\n", "Admin Name is", getName ()));
      sb.append (String.format ("| %-20s : %-25s |\n", "Admin UserName is", getUserName ()));
	    sb.append (String.format ("| %-20s : %-25s |\n", "Admin password is", "********"));
	    
	    sb.append ("----------------------------------------------------\n");
	    return sb.toString ();
  }

}
