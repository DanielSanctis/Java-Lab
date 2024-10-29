import java.util.Arrays;

public class TopKFrequentNumbers {
    // Static variable to store the input array of N numbers
    static int[] numbers;

    // Static method to identify the top K numbers with the highest occurrences
    public static void findTopKNumbers(int K) {
        int N = numbers.length;
        Arrays.sort(numbers);

        int[] uniqueNumbers = new int[N];
        int[] frequencies = new int[N];
        int uniqueCount = 0;

        int currentNumber = numbers[0];
        int currentFrequency = 1;

        // Count frequencies of each unique number
        for (int i = 1; i < N; i++) {
            if (numbers[i] == currentNumber) {
                currentFrequency++;
            } else {
                uniqueNumbers[uniqueCount] = currentNumber;
                frequencies[uniqueCount] = currentFrequency;
                uniqueCount++;
                currentNumber = numbers[i];
                currentFrequency = 1;
            }
        }
        // Add the last number and its frequency
        uniqueNumbers[uniqueCount] = currentNumber;
        frequencies[uniqueCount] = currentFrequency;
        uniqueCount++;

        // Sort the uniqueNumbers and frequencies arrays based on frequency and number
        for (int i = 0; i < uniqueCount - 1; i++) {
            for (int j = i + 1; j < uniqueCount; j++) {
                if (frequencies[i] < frequencies[j] || 
                   (frequencies[i] == frequencies[j] && uniqueNumbers[i] < uniqueNumbers[j])) {
                    // Swap frequencies
                    int tempFreq = frequencies[i];
                    frequencies[i] = frequencies[j];
                    frequencies[j] = tempFreq;
                    // Swap numbers
                    int tempNum = uniqueNumbers[i];
                    uniqueNumbers[i] = uniqueNumbers[j];
                    uniqueNumbers[j] = tempNum;
                }
            }
        }

        // Print the top K numbers
        for (int i = 0; i < K && i < uniqueCount; i++) {
            System.out.print(uniqueNumbers[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Sample test case 1
        numbers = new int[]{3, 1, 4, 4, 5, 2, 6, 1};
        int K1 = 2;
        findTopKNumbers(K1); // Expected Output: 4 1

        // Sample test case 2
        numbers = new int[]{7, 10, 11, 5, 2, 5, 5, 7, 11, 8, 9};
        int K2 = 4;
        findTopKNumbers(K2); // Expected Output: 5 11 7 10
    }
}
