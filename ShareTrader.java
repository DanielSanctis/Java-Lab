public class ShareTrader {
    static int maxProfit;

    public static void findMaxProfit(int[] price) {
        int N = price.length;
        if (N == 0) {
            maxProfit = 0;
            return;
        }

        int[] maxProfitLeft = new int[N];
        int[] maxProfitRight = new int[N];

        int minPrice = price[0];
        maxProfitLeft[0] = 0;
        for (int i = 1; i < N; i++) {
            minPrice = Math.min(minPrice, price[i]);
            maxProfitLeft[i] = Math.max(maxProfitLeft[i - 1], price[i] - minPrice);
        }

        int maxPrice = price[N - 1];
        maxProfitRight[N - 1] = 0;
        for (int i = N - 2; i >= 0; i--) {
            maxPrice = Math.max(maxPrice, price[i]);
            maxProfitRight[i] = Math.max(maxProfitRight[i + 1], maxPrice - price[i]);
        }

        int maxTotalProfit = 0;
        for (int i = 0; i < N; i++) {
            int totalProfit = maxProfitLeft[i] + maxProfitRight[i];
            if (totalProfit > maxTotalProfit) {
                maxTotalProfit = totalProfit;
            }
        }

        maxProfit = maxTotalProfit;
    }

    public static void main(String[] args) {
        int[] price1 = {10, 22, 5, 75, 65, 80};
        findMaxProfit(price1);
        System.out.println("Maximum Profit: " + maxProfit); // Expected Output: 87

        int[] price2 = {2, 30, 15, 10, 8, 25, 80};
        findMaxProfit(price2);
        System.out.println("Maximum Profit: " + maxProfit); // Expected Output: 100

        int[] price3 = {100, 90, 80, 70, 60};
        findMaxProfit(price3);
        System.out.println("Maximum Profit: " + maxProfit); // Expected Output: 0
    }
}
