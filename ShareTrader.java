class ShareTrader {
    static int maxProfit = 0;

    public static int findMaxProfit(int[] prices) {
        int n = prices.length;
        int[] profitBefore = new int[n];
        int[] profitAfter = new int[n];

        int minPrice = prices[0];
        for (int i = 1; i < n; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            profitBefore[i] = Math.max(profitBefore[i - 1], prices[i] - minPrice);
        }

        int maxPrice = prices[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            maxPrice = Math.max(maxPrice, prices[i]);
            profitAfter[i] = Math.max(profitAfter[i + 1], maxPrice - prices[i]);
        }

        for (int i = 0; i < n; i++) {
            maxProfit = Math.max(maxProfit, profitBefore[i] + profitAfter[i]);
        }
        
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] price1 = {10, 22, 5, 75, 65, 80};
        int[] price2 = {2, 30, 15, 10, 8, 25, 80};
        
        System.out.println("Max Profit for price1: " + findMaxProfit(price1));
        maxProfit = 0;
        System.out.println("Max Profit for price2: " + findMaxProfit(price2));
    }
}
