package stiver;

import java.util.Arrays;

public class BuyAndSellStocks3 {

    public static int maxProfitRecursion(int[] prices){
        return maxProfitRecursionHelper(prices, 0, 1, 2);
    }

    public static int maxProfitRecursionHelper(int[] prices, int idx, int buy, int cap){
        if(cap == 0) return 0;
        if(idx >= prices.length) return 0;

        if(buy == 1){
            return Math.max(
                    -prices[idx] + maxProfitRecursionHelper(prices, idx + 1, 0, cap),
                    maxProfitRecursionHelper(prices, idx + 1, 1, cap)
            );
        }else {
            return Math.max(
                    prices[idx] + maxProfitRecursionHelper(prices, idx + 1, 1, cap - 1),
                    maxProfitRecursionHelper(prices, idx + 1, 0, cap)
            );
        }
    }

    public static int maxProfitRecursionDP(int[] prices){
        int[][][] dp = new int[prices.length][2][3];
        for(int i = 0; i < dp.length; i++){
            for(int j = 0; j < dp[i].length; j++){
                Arrays.fill(dp[i][j], -1);
            }
        }
        return maxProfitRecursionHelperDP(prices, 0, 1, 2, dp);
    }

    public static int maxProfitRecursionHelperDP(int[] prices, int idx, int buy, int cap, int[][][] dp){
        if(cap == 0) return 0;
        if(idx >= prices.length) return 0;

        if(dp[idx][buy][cap] != -1) return dp[idx][buy][cap];

        if(buy == 1){
            return dp[idx][buy][cap] = Math.max(
                    -prices[idx] + maxProfitRecursionHelperDP(prices, idx + 1, 0, cap, dp),
                    maxProfitRecursionHelperDP(prices, idx + 1, 1, cap, dp)
            );
        }else {
            return dp[idx][buy][cap] = Math.max(
                    prices[idx] + maxProfitRecursionHelperDP(prices, idx + 1, 1, cap - 1 ,dp),
                    maxProfitRecursionHelperDP(prices, idx + 1, 0, cap, dp)
            );
        }
    }

    public static int maxProfitRecursionTabulation(int[] prices){
        int[][][] dp = new int[prices.length + 1][2][3];

        // This is also not necessary as by default its initialized to 0
        // For cap == 0 index and buy can be anything
        for(int i = 0; i < dp.length; i++){
            for(int j = 0; j < dp[0].length; j++){
                dp[i][j][0] = 0;
            }
        }

        // This is also not necessary as by default its initialized to 0
        // For index == n buy and cap can be anything
        for(int i = 0; i < dp[0].length; i++){
            for(int j = 0; j < dp[0][0].length; j++){
                dp[prices.length][i][j] = 0;
            }
        }


        for(int idx = prices.length - 1; idx >= 0; idx--){
            for(int buy = 0; buy <= 1; buy++){
                for(int cap = 1; cap <= 2; cap++) { // We are starting from one because 0 was done in base case
                    if(buy == 1){
                        dp[idx][buy][cap] = Math.max(
                                -prices[idx] + dp[idx + 1][0][cap],
                                dp[idx + 1][1][cap]
                        );
                    }else {
                        dp[idx][buy][cap] = Math.max(
                                prices[idx] + dp[idx + 1][1][cap - 1],
                                dp[idx + 1][0][cap]
                        );
                    }
                }
            }
        }
        return dp[0][1][2];
    }

    public static void main(String[] args) {
        int[] prices = {3,3,5,0,0,3,1,4};

        System.out.println(maxProfitRecursionTabulation(prices));
    }
}
