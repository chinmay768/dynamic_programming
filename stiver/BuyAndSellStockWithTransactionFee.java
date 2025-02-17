package stiver;

import java.util.Arrays;

public class BuyAndSellStockWithTransactionFee {

    public static int maxProfitRecursion(int[] prices, int fee){
        return maxProfitRecursionHelper(prices, fee, 0, 1);
    }

    public static int maxProfitRecursionHelper(int[] prices, int fee, int idx, int buy){
        if(idx == prices.length) return 0;

        if(buy == 1){
            return Math.max(
                    -prices[idx] + maxProfitRecursionHelper(prices, fee, idx + 1, 0),
                    maxProfitRecursionHelper(prices, fee, idx + 1, 1)
            );
        }else {
            return Math.max(
                    prices[idx] - fee + maxProfitRecursionHelper(prices, fee, idx  + 1, 1),
                    maxProfitRecursionHelper(prices, fee, idx + 1, 0)
            );
        }
    }

    public static int maxProfitRecursionDP(int[] prices, int fee){
        int[][] dp = new int[prices.length + 1][2];
        for(int i = 0; i < dp.length; i++){
            Arrays.fill(dp[i], -1);
        }
        return maxProfitRecursionHelperDP(prices, fee, 0, 1, dp);
    }

    public static int maxProfitRecursionHelperDP(int[] prices, int fee, int idx, int buy, int[][] dp){
        if(idx == prices.length) return 0;

        if(dp[idx][buy] != -1 ) return dp[idx][buy];

        if(buy == 1){
            return dp[idx][buy]  = Math.max(
                    -prices[idx] + maxProfitRecursionHelperDP(prices, fee, idx + 1, 0, dp),
                    maxProfitRecursionHelperDP(prices, fee, idx + 1, 1, dp)
            );
        }else {
            return dp[idx][buy] = Math.max(
                    prices[idx] - fee + maxProfitRecursionHelperDP(prices, fee, idx  + 1, 1, dp),
                    maxProfitRecursionHelperDP(prices, fee, idx + 1, 0, dp)
            );
        }
    }

    public static int maxProfitTabulation(int[] prices, int fee) {
        int[][] dp = new  int[prices.length + 1][2];

        for(int i = 0;  i <=  1; i++){
            dp[prices.length][i] = 0;
        }

        for(int idx = prices.length - 1; idx >= 0; idx--) {
            for (int buy = 0; buy <= 1; buy++) {
                if (buy == 1) {
                    dp[idx][buy] = Math.max(
                            -prices[idx] + dp[idx + 1][0],
                            dp[idx + 1][1]
                    );
                } else {
                    dp[idx][buy] = Math.max(
                            prices[idx] - fee + dp[idx + 1][1],
                            dp[idx + 1][0]
                    );
                }
            }
        }

        return dp[0][1];
    }

    public static void main(String[] args) {
        int[] prices = {1,3,2,8,4,9};
        int fee = 2;

        System.out.println(maxProfitTabulation(prices, fee));
    }
}
