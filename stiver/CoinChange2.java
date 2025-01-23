package stiver;

import java.util.Arrays;

public class CoinChange2 {

    public static int coinChangeRecursion(int amount, int[] coins){
        return coinChangeRecursionHelper(coins, coins.length - 1, amount);
    }

    public static int coinChangeRecursionHelper(int[] arr, int idx, int amount){
        if(idx == 0){
            if(amount % arr[idx] == 0) return 1;
            else return 0;
        }

        int excl = coinChangeRecursionHelper(arr, idx - 1, amount);

        int incl = 0;
        if(arr[idx] <= amount) incl = coinChangeRecursionHelper(arr, idx, amount - arr[idx]);

        return excl + incl;
    }

    public static int coinChangeRecursionDP(int amount, int[] coins){
        int[][] dp = new int[coins.length][amount + 1];
        for(int i = 0; i < coins.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        return coinChangeRecursionHelperDP(coins, coins.length - 1, amount, dp);
    }

    public static int coinChangeRecursionHelperDP(int[] arr, int idx, int amount, int[][] dp){
        if(idx == 0){
            if(amount % arr[idx] == 0) return 1;
            else return 0;
        }

        if(dp[idx][amount] != -1) return dp[idx][amount];
        int excl = coinChangeRecursionHelperDP(arr, idx - 1, amount, dp);

        int incl = 0;
        if(arr[idx] <= amount) incl = coinChangeRecursionHelperDP(arr, idx, amount - arr[idx], dp);

        return dp[idx][amount] = excl + incl;
    }

    public static void main(String[] args) {
        int[] coins = {1,2,5};

        int amount = 5;

        System.out.println(coinChangeRecursionDP(amount, coins));
    }
}
