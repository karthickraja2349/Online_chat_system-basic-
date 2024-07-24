package myChat.model;
public interface   AdminService{
  boolean addAdmin(Admin admin);
  boolean adminCheck(String user_Name, String password);
}