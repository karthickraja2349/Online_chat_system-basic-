package myChat.model;
import java.sql.Timestamp;
import java.util.List;

public class Chat {
    
    private String senderName;
    private String receiverName;
    private String message;
    private Timestamp datetime;
    private String receivedMessage;
    private Timestamp receivedTime;
    
    public static Chat chat = null;
    private Chat(){

    }
    
    public static Chat getChatInstance(){
       if(chat == null){
          chat = new Chat();
      }
     return chat;
    }

    public Chat(String senderName, String receiverName, String message, Timestamp datetime,
                String receivedMessage, Timestamp receivedTime) {
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.message = message;
        this.datetime = datetime;
        this.receivedMessage = receivedMessage;
        this.receivedTime = receivedTime;
    }

    // Getters and Setters
    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public String getReceivedMessage() {
        return receivedMessage;
    }

    public void setReceivedMessage(String receivedMessage) {
        this.receivedMessage = receivedMessage;
    }

    public Timestamp getReceivedTime() {
        return receivedTime;
    }

    public void setReceivedTime(Timestamp receivedTime) {
        this.receivedTime = receivedTime;
    }

    // Static method to format chat history
    public static String formatChatHistory(List<Chat> chatHistory) {
        if (chatHistory == null || chatHistory.isEmpty()) {
            return "No chat history available.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("THE CHAT BETWEEN YOU AND YOUR FRIEND ARE\n");
        sb.append("Sender: ").append(chatHistory.get(0).getSenderName()).append("\n");
        sb.append("Receiver: ").append(chatHistory.get(0).getReceiverName()).append("\n\n");

        for (Chat chat : chatHistory) {
            sb.append(chat.getDatetime()).append(" - ").append(chat.getSenderName()).append(": ").append(chat.getMessage()).append("\n");
        }

        sb.append("Chat Ended\n");
        return sb.toString();
    }
}
