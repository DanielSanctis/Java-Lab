import javax.swing.*;

public class App {
    public static void main(String[] args) {
        // Define board dimensions
        int boardWidth = 360;
        int boardHeight = 640;

        // Create the main game window
        JFrame frame = new JFrame("Flappy Bird");
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null); // Center the window
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize the game panel
        FlappyBird flappyBird = new FlappyBird();
        frame.add(flappyBird);
        frame.pack(); // Adjust frame to fit the preferred size of its components
        flappyBird.requestFocus(); // Ensure the game panel has focus for key events

        // Make the window visible
        frame.setVisible(true);
    }
}
