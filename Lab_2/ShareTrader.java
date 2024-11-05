public class ShareTrader
{
    static int maxprofit = 0;
    static int buy1 = 0;
    static int sell1 = 0;
    static int buy2 = 0;
    static int sell2 = 0;
    static void best_price(int price[])
    {
        int n = price.length;
        for (int i = 0;i<n;i++)
        {
            for (int j = i+1;j<n;j++)
            {
                for (int k = j+1;k<n;k++)
                {
                    for (int l = k+1;l<n;l++)
                    {
                        if(i<j&&j<k&&k<l)
                        {
                            int profit = 0;
                            profit = -price[i]+price[j]-price[k]+price[l];
                            if(maxprofit<profit)
                            {
                                buy1 = price[i];
                                sell1 = price[j];
                                buy2= price[k];
                                sell2 = price[l];
                                maxprofit=profit;
                            }
                        }
                    }
                }
            }
        }
    }
    
	public static void main(String[] args) {
	    int price[] = {2, 30, 15, 10, 8, 25, 80};
	    ShareTrader.best_price(price);
		System.out.println(maxprofit);
		System.out.println(buy1);
		System.out.println(sell1);
		System.out.println(buy2);
		System.out.println(sell2);
	}
}