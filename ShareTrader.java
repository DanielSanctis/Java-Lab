public class ShareTrader {
    // Static variables to store the maximum profit and the respective buy/sell prices
    static int maxProfit = 0;
    static int buy1 = 0;
    static int sell1 = 0;
    static int buy2 = 0;
    static int sell2 = 0;

    // Method to calculate the best prices for maximum profit with 2 transactions
    static void bestPrice(int[] prices) {
        int n = prices.length;

        // Iterate through all possible buy/sell combinations for 2 transactions
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int l = k + 1; l < n; l++) {
                        // Ensure the transactions are in the correct order
                        if (i < j && j < k && k < l) {
                            int profit = -prices[i] + prices[j] - prices[k] + prices[l];

                            // Update maxProfit and respective buy/sell prices if profit is greater
                            if (maxProfit < profit) {
                                buy1 = prices[i];
                                sell1 = prices[j];
                                buy2 = prices[k];
                                sell2 = prices[l];
                                maxProfit = profit;
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] prices = {2, 30, 15, 10, 8, 25, 80};
        ShareTrader.bestPrice(prices);

        // Output the results
        System.out.println("Maximum Profit: " + maxProfit);
        System.out.println("First Transaction - Buy at: " + buy1 + ", Sell at: " + sell1);
        System.out.println("Second Transaction - Buy at: " + buy2 + ", Sell at: " + sell2);
    }
}
