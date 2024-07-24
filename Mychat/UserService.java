interface UserServie{
  boolean addUser(User user );
  boolean userCheck(String user_Name, String password);
  void addFollower(String userName, String friendName);
  void removeFollower(long userId, long followerId);
  void message(long senderId, long receiverId,String message);
  void displayReceivedMessages(long userId, String userName);
  void sendFollowRequest(long senderId, long receiverId);
  void acceptFollowRequest(int notificationId);
}