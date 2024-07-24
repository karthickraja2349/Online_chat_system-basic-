package myChat.controller;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.*;
import javax.imageio.ImageIO;

import myChat.database.DatabaseConnection;

public class DisplayImageAwt extends Frame {

    private Image[] images;
    private int numImages;
    public static DisplayImageAwt displayImageAwt = null;
    
    private DisplayImageAwt(){
    
    }
    public static DisplayImageAwt getDisplayImageAwtInstance(){
          if(displayImageAwt == null){
              displayImageAwt = new DisplayImageAwt();
          }
          return displayImageAwt;
    }

    public DisplayImageAwt(int userId) {
        setTitle("Display Images from Database");
        setSize(800, 600);
                                                               //800 pixel wide , 600 pixel height
        try {
            images = getImagesFromDatabase(userId);
            numImages = images.length;
        } catch (SQLException | IOException e) {
            e.getMessage();
        }
        
       

        // Add a window listener to handle window closing
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    private Image[] getImagesFromDatabase(int userId) throws SQLException, IOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Image[] images = null;

        try {
            connection = DatabaseConnection.getConnection();
            String sql = "select p.post as posts from posts p join users u on p.user_id = u.user_id where u.user_id = ?";
            statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();

            int rowCount = 0;
            if (resultSet.last()) {
                rowCount = resultSet.getRow();
                resultSet.beforeFirst(); // Reset cursor position to beginning
            }

            images = new Image[rowCount];
            int i = 0;

            while (resultSet.next()) {
                
                byte[] imageBytes = resultSet.getBytes("posts");

                //  bytes to Image
                if (imageBytes != null && imageBytes.length > 0) {
                    // Use ImageIO to read bytes into BufferedImage
                    ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
                    BufferedImage bufferedImage = ImageIO.read(bis);
                    
                    //  BufferedImage to Image
                    images[i++] = bufferedImage.getScaledInstance(bufferedImage.getWidth(), bufferedImage.getHeight(), Image.SCALE_SMOOTH);
                }
            }
        } catch (SQLException | IOException e) {
            e.getMessage();
            throw e; 
        } finally {
            
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return images;
    }

          // method in Frame
    public void paint(Graphics g) {
        int x = 20;
        int y = 50;

        // Display each image in a grid
        for (int i = 0; i < numImages; i++) {
            g.drawImage(images[i], x, y, this);
            x += images[i].getWidth(this) + 20;

            // Move to the next row after displaying 3 images
            if ((i + 1) % 3 == 0) {
                x = 20;
                y += images[i].getHeight(this) + 20;
            }
        }
    }

    public void viewPost(int userId) {
        
        new DisplayImageAwt(userId);
    }
}
