package stiver;

import java.util.Arrays;

public class CoinChangeORMinCoins {

    public static int coinChangeRecursion(int[] coins, int amount) {
        return coinChangeRecursionHelper(coins, coins.length - 1, amount);
    }

    public static int coinChangeRecursionHelper(int[] coins, int idx, int amount){
        if(idx == 0){
            if(amount % coins[idx] == 0) return amount / coins[idx];
            else return (int) 1e9;
        }

        int excl = coinChangeRecursionHelper(coins, idx - 1, amount);

        int incl = Integer.MAX_VALUE;
        if(coins[idx] <= amount) incl = 1 + coinChangeRecursionHelper(coins, idx, amount - coins[idx]);

        return Math.min(excl, incl);
    }

    public static int coinChangeRecursionDP(int[] coins, int amount) {
        int[][] dp = new int[coins.length][amount + 1];
        for (int i = 0; i < dp.length; i++){
            Arrays.fill( dp[i], -1);
        }
        int res = coinChangeRecursionHelperDP(coins, coins.length - 1, amount, dp);
        if(res == 1e9) return -1;
        else return res;
    }

    public static int coinChangeRecursionHelperDP(int[] coins, int idx, int amount, int[][] dp){
        if(idx == 0){
            if(amount % coins[idx] == 0) return amount / coins[idx];
            else return (int) 1e9;
        }

        if(dp[idx][amount] != -1) return dp[idx][amount];

        int excl = coinChangeRecursionHelperDP(coins, idx - 1, amount, dp);

        int incl = Integer.MAX_VALUE;
        if(coins[idx] <= amount) incl = 1 + coinChangeRecursionHelperDP(coins, idx, amount - coins[idx], dp);

        return dp[idx][amount] = Math.min(excl, incl);
    }

    public static int coinChangeTabulation(int[] coins, int amount){
        int[][] dp = new int[coins.length][amount + 1];
        for(int i = 0; i <= amount; i++){
            if(i % coins[0] == 0) dp[0][i] = i / coins[0];
            else dp[0][i] = (int) 1e9;
        }

        for(int i = 1; i < coins.length; i++){
            for(int j = 0; j <= amount; j++){
                int excl = dp[i - 1][j];

                int incl = Integer.MAX_VALUE;
                if(coins[i] <= j) incl = 1 + dp[i][j - coins[i]];

                dp[i][j] = Math.min(excl, incl);
            }
        }

        int res = dp[dp.length - 1][amount];
        if(res >= 1e9) return -1;
        return res;
    }

    public static int coinChangeTabulationSpaceOptimized(int[] coins, int amount){
        int[] prev = new int[amount + 1];
        for(int i = 0; i <= amount; i++){
            if(i % coins[0] == 0) prev[i] = i / coins[0];
            else prev[i] = (int) 1e9;
        }

        for(int i = 1; i < coins.length; i++){
            int[] curr = new int[amount + 1];
            for(int j = 0; j <= amount; j++){
                int excl = prev[j];

                int incl = Integer.MAX_VALUE;
                if(coins[i] <= j) incl = 1 + curr[j - coins[i]];

                curr[j] = Math.min(excl, incl);
            }
            prev = curr;
        }

        int res = prev[amount];
        if(res >= 1e9) return -1;
        return res;
    }

    public static void main(String[] args) {
        int[] coins = {1,2,5};
        int amount = 11;

        System.out.println(coinChangeTabulationSpaceOptimized(coins, amount));
    }
}
