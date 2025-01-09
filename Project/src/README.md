Flappy Bird Game

A simple Java implementation of the classic Flappy Bird game, built with Swing and AWT for the GUI. This project demonstrates game mechanics, basic animation, and interaction with a MySQL database for saving and retrieving high scores.

Features

Flappy Bird mechanics: Jump through randomly generated pipes.

Score tracking and high score saving to a MySQL database.

Object-oriented design for easy code maintenance.

Custom graphics with dynamic animation.

Project Structure

Project/
├── .vscode/                   # VS Code settings
│   └── settings.json
├── lib/                       # Libraries
│   └── mysql-connector-j-9.1.0.jar
├── src/                       # Source code
│   ├── App.java               # Main entry point
│   ├── FlappyBird.java        # Game logic and rendering
│   ├── DatabaseManager.java   # Database interaction
├── resources/                 # Resources (images)
│   └── images/
│       ├── flappybird.png
│       ├── flappybirdbg.png
│       ├── toppipe.png
│       └── bottompipe.png
└── README.md                  # Project documentation

Requirements

Java Development Kit (JDK) 8 or higher

MySQL Server

MySQL Connector/J (included in lib/ directory)

A code editor (VS Code recommended)

Setup

1. Clone the Repository

git clone https://github.com/your-repo/flappy-bird-java.git
cd flappy-bird-java

2. Setup MySQL Database

Create a database for the game:

CREATE DATABASE flappybird_db;
USE flappybird_db;

CREATE TABLE Scores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    score INT NOT NULL
);

Update the DatabaseManager.java file with your database credentials:

private static final String URL = "jdbc:mysql://localhost:3306/flappybird_db?useSSL=false&serverTimezone=UTC";
private static final String USERNAME = "your_username";
private static final String PASSWORD = "your_password";

3. Organize Project Files

Ensure your project structure matches the one outlined above, especially placing images in the resources/images/ folder.

4. Add MySQL Connector to Classpath

Add the mysql-connector-j-9.1.0.jar to your classpath. If using VS Code, make sure your .vscode/settings.json includes:

{
    "java.project.referencedLibraries": [
        "lib/**/*.jar",
        "resources/**/*"
    ]
}

5. Compile and Run the Project

Compile the source code:

javac -cp "lib/*" -d out src/*.java

Run the game:

java -cp "out;lib/*" App

How to Play

Start the Game:

Upon running the program, a new window will open displaying the game.

The bird will start falling due to gravity.

Controls:

Press the SPACE bar to make the bird jump.

Objective:

Avoid the pipes and survive for as long as possible.

Each set of pipes you pass increases your score.

High Score:

Your high score will be saved to a MySQL database and displayed in the game.

Restart:

Press the SPACE bar when the game is over to restart.

Troubleshooting

Images Not Loading:

Ensure all images are correctly placed in the resources/images/ folder.

Confirm that settings.json includes the resources/**/* path.

Database Issues:

Verify that your MySQL server is running.

Double-check database credentials in DatabaseManager.java.

Ensure the Scores table exists in your database.

Game Does Not Start:

Confirm that you’ve compiled all .java files.

Ensure MySQL Connector/J is added to the classpath.

Customization

Change Images:

Replace the image files in resources/images/ with your own. Ensure the file names remain the same or update paths in FlappyBird.java.

Modify Game Difficulty:

Adjust the bird's gravity or pipe movement speed in FlappyBird.java:

int gravity = 1;       // Increase for faster falling
int velocityX = -4;    // Increase for faster pipe movement

Alter Pipe Spacing:

Modify the placePipes method in FlappyBird.java to change the pipe gap size.

Credits

Game Design: Inspired by the classic Flappy Bird.

Programming: Daniel Sanctis

License

This project is licensed under the MIT License. See the LICENSE file for details.