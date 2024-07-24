import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;

public class RetriveImage {

    public static void main(String[] args) {
      

        // ID of the post to retrieve
        int postId = 1; // example post id

        try {
            // Connect to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinechattingsystem","root","1234");

            // Prepare the SQL statement
            String sql = "SELECT post FROM posts WHERE post_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, postId);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                byte[] imageBytes = resultSet.getBytes("post");

                // Convert byte array to BufferedImage
                ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
                BufferedImage bufferedImage = ImageIO.read(bais);

                
                File outputFile = new File("retrieved_image.png");
                ImageIO.write(bufferedImage, "png", outputFile);

                System.out.println("Image retrieved and saved to: " + outputFile.getAbsolutePath());
            }

            // Close the connection
            resultSet.close();
            statement.close();
            connection.close();

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
