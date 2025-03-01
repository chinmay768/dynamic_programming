package stiver;

import java.util.Arrays;

public class BuyAndSellStocks2 {

    public static int maxProfitRecursion(int[] prices){
        return maxProfitRecursionHelper(prices, 0, 1);
    }

    public static int maxProfitRecursionHelper(int[] prices, int idx, int buy ){
        if(idx >= prices.length){
            return 0;
        }

        if(buy == 1){
            return Math.max(
                    -prices[idx] + maxProfitRecursionHelper(prices, idx + 1, 0),
                    maxProfitRecursionHelper(prices, idx + 1, 1)
            );
        }else {
            return Math.max(
                    prices[idx] + maxProfitRecursionHelper(prices, idx + 1, 1),
                    maxProfitRecursionHelper(prices, idx + 1, 0)
            );
        }
    }

    public static int maxProfitRecursionDP(int[] prices){
        int[][] dp = new int[prices.length][2];
        for(int i = 0; i < dp.length; i++){
            Arrays.fill(dp[i], - 1);
        }
        return maxProfitRecursionHelperDP(prices, 0, 1, dp);
    }

    public static int maxProfitRecursionHelperDP(int[] prices, int idx, int buy, int[][] dp){
        if(idx >= prices.length){
            return 0;
        }

        if(dp[idx][buy] != -1) return dp[idx][buy];
        if(buy == 1){
            return dp[idx][buy] = Math.max(
                    -prices[idx] + maxProfitRecursionHelperDP(prices, idx + 1, 0, dp),
                    maxProfitRecursionHelperDP(prices, idx + 1, 1, dp)
            );
        }else {
            return dp[idx][buy] = Math.max(
                    prices[idx] + maxProfitRecursionHelperDP(prices, idx + 1, 1, dp),
                    maxProfitRecursionHelperDP(prices, idx + 1, 0, dp)
            );
        }
    }


    public static int maxProfitTabulation(int[] prices){
        int[][] dp = new int[prices.length + 1][2];

        dp[prices.length][0] = dp[prices.length][1] = 0;

        for(int i = prices.length - 1; i >= 0; i--){
            for(int j = 0; j <= 1; j++){
                if(j == 0){ // Sell
                    dp[i][j] = Math.max(
                            prices[i] + dp[i + 1][1],
                            dp[i + 1][0]
                    );
                }else { // Buy
                    dp[i][j] = Math.max(
                            -prices[i] + dp[i + 1][0],
                            dp[i + 1][1]
                    );
                }
            }
        }

        return dp[0][1];// Here we know that the first action will always be buy as you cant sell before buying
    }

    public static void main(String[] args) {
        int[] prices = {1,2,3,4,5};

        System.out.println(maxProfitTabulation(prices));
    }
}
