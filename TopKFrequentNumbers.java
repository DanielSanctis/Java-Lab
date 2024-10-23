import java.util.Arrays;

public class TopKFrequentNumbers {
    static int[] arr = {3, 1, 4, 4, 5, 2, 6, 1};

    public static void findTopKFrequent(int K) {
        int maxVal = Arrays.stream(arr).max().getAsInt();
        int[] frequency = new int[maxVal + 1];

        for (int num : arr) {
            frequency[num]++;
        }

        int[][] freqArray = new int[maxVal + 1][2];

        for (int i = 0; i <= maxVal; i++) {
            freqArray[i][0] = i;
            freqArray[i][1] = frequency[i];
        }

        Arrays.sort(freqArray, (a, b) -> {
            if (b[1] != a[1]) {
                return b[1] - a[1];
            } else {
                return b[0] - a[0];
            }
        });

        System.out.print("Top " + K + " numbers: ");
        int count = 0;
        for (int i = 0; i <= maxVal && count < K; i++) {
            if (freqArray[i][1] > 0) {
                System.out.print(freqArray[i][0] + " ");
                count++;
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        findTopKFrequent(2);

        arr = new int[]{7, 10, 11, 5, 2, 5, 5, 7, 11, 8, 9};
        findTopKFrequent(4);
    }
}
