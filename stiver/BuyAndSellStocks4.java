package stiver;

import java.util.Arrays;

public class BuyAndSellStocks4 {

    public static int maxProfitRecursion(int[] prices, int k){
        return maxProfitRecursionHelper(prices, 0, 1, k);
    }

    public static int maxProfitRecursionHelper(int[] prices, int idx, int buy, int cap){
        if(cap == 0) return 0;
        if(idx == prices.length) return 0;

        if(buy == 1){
            return Math.max(
                    -prices[idx] + maxProfitRecursionHelper(prices, idx + 1, 0, cap),
                    maxProfitRecursionHelper(prices, idx + 1, 1, cap)
            ) ;
        }else {
            return Math.max(
                    prices[idx] + maxProfitRecursionHelper(prices, idx + 1, 1, cap - 1),
                    maxProfitRecursionHelper(prices, idx + 1, 0, cap)
            );
        }
    }

    public static int maxProfitRecursionDP(int[] prices, int k){
        int[][][] dp = new int[prices.length][2][k + 1];
        for(int i = 0; i < prices.length; i++){
            for(int j = 0; j < 2; j++){
                Arrays.fill(dp[i][j] ,-1);
            }
        }
        return maxProfitRecursionHelperDP(prices, 0, 1, k, dp);
    }

    public static int maxProfitRecursionHelperDP(int[] prices, int idx, int buy, int cap, int[][][] dp){
        if(cap == 0) return 0;
        if(idx == prices.length) return 0;

        if(dp[idx][buy][cap] !=  -1) return dp[idx][buy][cap];
        if(buy == 1){
            return dp[idx][buy][cap] = Math.max(
                    -prices[idx] + maxProfitRecursionHelperDP(prices, idx + 1, 0, cap ,dp),
                    maxProfitRecursionHelperDP(prices, idx + 1, 1, cap, dp)
            ) ;
        }else {
            return dp[idx][buy][cap] = Math.max(
                    prices[idx] + maxProfitRecursionHelperDP(prices, idx + 1, 1, cap - 1, dp),
                    maxProfitRecursionHelperDP(prices, idx + 1, 0, cap, dp)
            );
        }
    }

    public static void main(String[] args) {
        int[] prices = {3,2,6,5,0,3};
        int cap = 2;

        System.out.println(maxProfitRecursionDP(prices, cap));
    }
}