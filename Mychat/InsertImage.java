import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertImage {
    public void insertImage(int i) {
        
        String filePath = "C:\\Users\\Ganeshmoorthy\\Desktop\\hello\\chatsystem\\download.jpg";

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO posts (user_id, post) VALUES (?, ?)";
            PreparedStatement statement = DatabaseConnection.getPreparedStatement(sql);
            statement.setInt(1, 1); // example user_id

            FileInputStream inputStream = new FileInputStream(filePath);
            statement.setBinaryStream(2, inputStream);

            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println("Image inserted successfully.");
            }
            statement.close();
            inputStream.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
