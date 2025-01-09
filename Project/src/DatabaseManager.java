import java.sql.*;

public class DatabaseManager {
    
    // Database connection parameters
    private static final String URL = "jdbc:mysql://localhost:3306/flappybird_db?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root"; // Replace with your MySQL username
    private static final String PASSWORD = "Daniel@772002"; // Replace with your MySQL password

    /**
     * Retrieves the highest score from the Scores table.
     * @return the highest score as an integer. Returns 0 if no scores are present or an error occurs.
     */
    public static int getHighScore() {
        int highScore = 0;
        String sql = "SELECT MAX(score) AS max_score FROM Scores";

        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("Database connection successful for getHighScore.");

            if (rs.next()) {
                highScore = rs.getInt("max_score");
                System.out.println("Retrieved high score: " + highScore);
            } else {
                System.out.println("No scores found in the database.");
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving high score:");
            e.printStackTrace();
        }

        return highScore;
    }

    /**
     * Saves a new score to the Scores table.
     * @param score The score to be saved as an integer.
     */
    public static void saveScore(int score) {
        String sql = "INSERT INTO Scores (score) VALUES (?)";

        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            System.out.println("Database connection successful for saveScore.");

            pstmt.setInt(1, score);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Score saved successfully: " + score);
            } else {
                System.out.println("Failed to save the score: " + score);
            }

        } catch (SQLException e) {
            System.err.println("Error saving high score:");
            e.printStackTrace();
        }
    }
}
