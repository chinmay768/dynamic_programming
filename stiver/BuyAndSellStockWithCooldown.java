package stiver;

import java.util.Arrays;

public class BuyAndSellStockWithCooldown {

    public static int maxProfitRecursion(int[] prices){
        return maxProfitRecursionHelper(prices, 0, 1);
    }

    public static int maxProfitRecursionHelper(int[] prices, int idx, int buy){
        if(idx >= prices.length) // >= and not == because we are doing +2 to idx if sell
            return 0;

        if(buy == 1){
            return Math.max(
                    -prices[idx] + maxProfitRecursionHelper(prices, idx + 1, 0),
                    maxProfitRecursionHelper(prices, idx + 1, 1)
            );
        }else {
            return Math.max(
                    prices[idx] + maxProfitRecursionHelper(prices, idx + 2, 1),// + 2 because of cooldown
                    maxProfitRecursionHelper(prices, idx + 1, 0)
            );
        }
    }

    public static int maxProfitRecursionDP(int[] prices){
        int[][] dp = new int[prices.length + 2][2]; // Length + 2 as if we sell at last idx then to handle call for idx + 2
        for(int i = 0; i < dp.length; i++){
            Arrays.fill(dp[i], -1);
        }
        return maxProfitRecursionHelperDP(prices, 0, 1 , dp);
    }

    public static int maxProfitRecursionHelperDP(int[] prices, int idx, int buy, int[][] dp){
        if(idx >= prices.length) // >= and not == because we are doing +2 to idx if sell
            return 0;

        if(dp[idx][buy] != -1) return dp[idx][buy];

        if(buy == 1){
            return dp[idx][buy] = Math.max(
                    -prices[idx] + maxProfitRecursionHelperDP(prices, idx + 1, 0, dp),
                    maxProfitRecursionHelperDP(prices, idx + 1, 1 ,dp)
            );
        }else {
            return dp[idx][buy] = Math.max(
                     prices[idx] + maxProfitRecursionHelperDP(prices, idx + 2, 1, dp),// + 2 because of cooldown
                    maxProfitRecursionHelperDP(prices, idx + 1, 0, dp)
            );
        }
    }

    public static int maxProfitTabulation(int[] prices){
        int[][] dp = new int[prices.length + 2][2];

        for(int idx = prices.length - 1; idx >= 0; idx--){
            for(int buy = 0; buy <= 1; buy++){
                if(buy == 1){
                    dp[idx][buy] = Math.max(
                            -prices[idx] + dp[idx + 1][0],
                            dp[idx + 1][1]
                    );
                }else {
                    dp[idx][buy] = Math.max(
                            prices[idx] + dp[idx + 2][1],
                            dp[idx + 1][0]
                    );
                }
            }
        }

        return dp[0][1];
    }

    public static void main(String[] args) {
        int[] prices = {6,1,3,2,4,7};
        System.out.println(maxProfitTabulation(prices));
    }
}
