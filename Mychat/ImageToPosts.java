import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.imageio.ImageIO;

public class ImageToPosts {

    public static void main(String[] args) {
    

        
     
        int userId = 1; // example user id
        String imagePath = "C:\\Users\\Ganeshmoorthy\\Desktop\\hello\\chatsystem\\download.jpg"; // path to the image file

        // Read the image
        File imageFile = new File(imagePath);
        try {
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", baos);
            byte[] imageBytes = baos.toByteArray();

            // Connect to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinechattingsystem","root","1234");

            // Prepare the SQL statement
            String sql = "INSERT INTO posts (user_id, post) VALUES ( ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setInt(1, userId);
            statement.setBytes(2, imageBytes);

            // Execute the SQL statement
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Image inserted successfully into the posts table!");
            }

            // Close the connection
            statement.close();
            connection.close();

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
