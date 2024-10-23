import java.util.Scanner;

public class AlphabetWarGame {
    private char[] leftLetters;
    private int[] leftStrengths;
    private char[] rightLetters;
    private int[] rightStrengths;

    public AlphabetWarGame() {
        leftLetters = new char[] {'w', 'p', 'b', 's'};
        leftStrengths = new int[] {4, 3, 2, 1};

        rightLetters = new char[] {'m', 'q', 'd', 'z'};
        rightStrengths = new int[] {4, 3, 2, 1};
    }

    public AlphabetWarGame(char[] leftLetters, int[] leftStrengths, char[] rightLetters, int[] rightStrengths) {
        this.leftLetters = leftLetters;
        this.leftStrengths = leftStrengths;
        this.rightLetters = rightLetters;
        this.rightStrengths = rightStrengths;
    }

    public String alphabetWar(String word) {
        int leftTotal = 0;
        int rightTotal = 0;

        for (char c : word.toCharArray()) {
            int index;
            if ((index = indexOf(leftLetters, c)) != -1) {
                leftTotal += leftStrengths[index];
            } else if ((index = indexOf(rightLetters, c)) != -1) {
                rightTotal += rightStrengths[index];
            }
        }

        return determineWinner(leftTotal, rightTotal);
    }

    public String alphabetWar(String leftWord, String rightWord) {
        int leftTotal = 0;
        int rightTotal = 0;

        for (char c : leftWord.toCharArray()) {
            int index;
            if ((index = indexOf(leftLetters, c)) != -1) {
                leftTotal += leftStrengths[index];
            }
        }

        for (char c : rightWord.toCharArray()) {
            int index;
            if ((index = indexOf(rightLetters, c)) != -1) {
                rightTotal += rightStrengths[index];
            }
        }

        return determineWinner(leftTotal, rightTotal);
    }

    private String determineWinner(int leftTotal, int rightTotal) {
        if (leftTotal > rightTotal) {
            return "Left side wins!";
        } else if (rightTotal > leftTotal) {
            return "Right side wins!";
        } else {
            return "Let's fight again!";
        }
    }

    private int indexOf(char[] array, char c) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == c) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AlphabetWarGame game = new AlphabetWarGame();

        System.out.println("Enter the word(s) for the Alphabet War:");
        String input = scanner.nextLine();

        String result = game.alphabetWar(input);
        System.out.println(result);

        scanner.close();
    }
}