import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    // Board dimensions
    int boardWidth = 360;
    int boardHeight = 640;

    // Images
    Image backgroundImg;
    Image birdImg;
    Image topPipeImg;
    Image bottomPipeImg;

    // Bird properties
    int birdX = boardWidth / 8;
    int birdY = boardHeight / 2;
    int birdWidth = 34;
    int birdHeight = 24;

    // Bird class
    class Bird {
        int x = birdX;
        int y = birdY;
        int width = birdWidth;
        int height = birdHeight;
        Image img;

        Bird(Image img) {
            this.img = img;
        }
    }

    // Pipe properties
    int pipeX = boardWidth;
    int pipeY = 0;
    int pipeWidth = 64;  // Scaled by 1/6
    int pipeHeight = 512;

    // Pipe class
    class Pipe {
        int x = pipeX;
        int y = pipeY;
        int width = pipeWidth;
        int height = pipeHeight;
        Image img;
        boolean passed = false;

        Pipe(Image img) {
            this.img = img;
        }
    }

    // Game logic variables
    Bird bird;
    int velocityX = -4; // Move pipes to the left (simulate bird moving right)
    int velocityY = 0;  // Vertical velocity of the bird
    int gravity = 1;

    ArrayList<Pipe> pipes;
    Random random = new Random();

    Timer gameLoop;
    Timer placePipeTimer;
    boolean gameOver = false;
    double score = 0;
    int highScore = 0; // Variable to store the high score

    // Constructor
    FlappyBird() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setFocusable(true);
        addKeyListener(this);

        // Load images
        backgroundImg = loadImage("flappybirdbg.png");
        birdImg = loadImage("flappybird.png");
        topPipeImg = loadImage("toppipe.png");
        bottomPipeImg = loadImage("bottompipe.png");

        // Initialize bird and pipes
        bird = new Bird(birdImg);
        pipes = new ArrayList<Pipe>();

        // 1) Load high score from database when game starts
        highScore = DatabaseManager.getHighScore();
        System.out.println("Initial High Score: " + highScore);

        // Place pipes timer (adds new pipes every 1.5 seconds)
        placePipeTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });
        placePipeTimer.start();

        // Game loop timer (60 FPS)
        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();
    }

    // Method to load images with error handling
    private Image loadImage(String path) {
        try {
            Image img = new ImageIcon(getClass().getResource(path)).getImage();
            System.out.println("Loaded image: " + path);
            return img;
        } catch (Exception e) {
            System.err.println("Error loading image: " + path);
            e.printStackTrace();
            return null;
        }
    }

    // Method to place new pipes
    void placePipes() {
        // Randomize the Y position of the top pipe
        int randomPipeY = (int) (pipeY - pipeHeight / 4 - Math.random() * (pipeHeight / 2));
        int openingSpace = boardHeight / 4;

        // Create top pipe
        Pipe topPipe = new Pipe(topPipeImg);
        topPipe.y = randomPipeY;
        pipes.add(topPipe);

        // Create bottom pipe
        Pipe bottomPipe = new Pipe(bottomPipeImg);
        bottomPipe.y = topPipe.y + pipeHeight + openingSpace;
        pipes.add(bottomPipe);
    }

    // Paint component override to draw the game
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    // Method to handle all drawing
    public void draw(Graphics g) {
        // Draw background
        if (backgroundImg != null) {
            g.drawImage(backgroundImg, 0, 0, this.boardWidth, this.boardHeight, null);
        } else {
            g.setColor(Color.CYAN);
            g.fillRect(0, 0, boardWidth, boardHeight);
            System.err.println("Background image is null.");
        }

        // Draw bird
        if (bird.img != null) {
            g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);
        } else {
            g.setColor(Color.YELLOW);
            g.fillOval(bird.x, bird.y, bird.width, bird.height);
            System.err.println("Bird image is null.");
        }

        // Draw pipes
        for (Pipe pipe : pipes) {
            if (pipe.img != null) {
                g.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, null);
            } else {
                g.setColor(Color.GREEN);
                g.fillRect(pipe.x, pipe.y, pipe.width, pipe.height);
                System.err.println("Pipe image is null.");
            }
        }

        // Draw scores
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 24));

        // Current score
        g.drawString("Score: " + (int) score, 10, 35);

        // High score
        g.drawString("High Score: " + highScore, 10, 65);

        // Game Over message
        if (gameOver) {
            g.setFont(new Font("Arial", Font.BOLD, 36));
            g.drawString("Game Over!", boardWidth / 2 - 100, boardHeight / 2 - 50);
            g.setFont(new Font("Arial", Font.PLAIN, 24));
            g.drawString("Press SPACE to Restart", boardWidth / 2 - 120, boardHeight / 2);
        }
    }

    // Method to handle game movement
    public void move() {
        if (gameOver) {
            return; // No movement if game is over
        }

        // Apply gravity to the bird
        velocityY += gravity;
        bird.y += velocityY;
        bird.y = Math.max(bird.y, 0); // Prevent bird from going above the window

        // Move pipes
        ArrayList<Pipe> pipesToRemove = new ArrayList<>();
        for (Pipe pipe : pipes) {
            pipe.x += velocityX;

            // Check if pipe has passed the bird
            if (!pipe.passed && bird.x > pipe.x + pipe.width) {
                score += 0.5; // Each pair of pipes counts as 1 point
                pipe.passed = true;
                System.out.println("Score updated to: " + score);
            }

            // Check collision
            if (collision(bird, pipe)) {
                gameOver = true;
                System.out.println("Collision detected.");
                break;
            }

            // Remove pipes that have gone off-screen
            if (pipe.x + pipe.width < 0) {
                pipesToRemove.add(pipe);
            }
        }
        pipes.removeAll(pipesToRemove);

        // Check if bird hits the ground
        if (bird.y + bird.height > boardHeight) {
            gameOver = true;
            System.out.println("Bird hit the ground.");
        }

        // If game over, handle high score
        if (gameOver) {
            // If current score is higher than high score, update it
            if ((int) score > highScore) {
                System.out.println("New high score! Saving to database...");
                highScore = (int) score; // Update local high score
                DatabaseManager.saveScore(highScore); // Save to database
            } else {
                System.out.println("Score did not exceed high score.");
            }
        }
    }

    // Collision detection between bird and pipe
    boolean collision(Bird a, Pipe b) {
        return a.x < b.x + b.width &&   // a's left edge is left of b's right edge
               a.x + a.width > b.x &&   // a's right edge is right of b's left edge
               a.y < b.y + b.height &&  // a's top edge is above b's bottom edge
               a.y + a.height > b.y;    // a's bottom edge is below b's top edge
    }

    // Timer event handler (game loop)
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();

        if (gameOver) {
            placePipeTimer.stop();
            gameLoop.stop();
        }
    }

    // KeyListener methods
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!gameOver) {
                velocityY = -9; // Make the bird jump
                System.out.println("Space pressed. Bird jumped.");
            } else {
                // Restart the game
                System.out.println("Restarting the game.");
                restartGame();
            }
        }
    }

    // Method to restart the game after game over
    void restartGame() {
        // Reset bird position and velocity
        bird.y = birdY;
        velocityY = 0;

        // Clear existing pipes
        pipes.clear();

        // Reset game state
        gameOver = false;
        score = 0;

        // Reload high score from the database in case it was updated elsewhere
        highScore = DatabaseManager.getHighScore();
        System.out.println("High Score after restart: " + highScore);

        // Restart timers
        gameLoop.start();
        placePipeTimer.start();
    }

    // Unused but required by KeyListener interface
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
