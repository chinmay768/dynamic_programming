package stiver;

import java.util.Arrays;

public class RodCutting {

    public static int rodCuttigRecursion(int[] price, int n){
        return rodCuttingRecursionHelper(price, price.length - 1, n);
    }

    public static int rodCuttingRecursionHelper(int[] price, int idx, int n) {
        if(idx == 0)
            return n * price[idx];

        int excl = rodCuttingRecursionHelper(price, idx - 1, n);

        int incl = 0;
        if(price[idx] <= n)
            incl = rodCuttingRecursionHelper(price, idx, n - price[idx]) + price[idx];

        return Math.max(excl , incl);
    }

    public static int rodCuttigRecursionDP(int[] price, int n){
        int[][] dp = new int[price.length][n + 1];
        for(int i = 0; i < dp.length; i++)
            Arrays.fill(dp[i], -1);

        return rodCuttingRecursionHelperDP(price, price.length - 1, n, dp);
    }

    public static int rodCuttingRecursionHelperDP(int[] price, int idx, int n, int[][] dp) {
        if(idx == 0)
            return n * price[idx];

        if(dp[idx][n] != -1) return dp[idx][n];

        int excl = rodCuttingRecursionHelper(price, idx - 1, n);

        int incl = 0;
        if(price[idx] <= n)
            incl = rodCuttingRecursionHelper(price, idx, n - price[idx]) + price[idx];

        return dp[idx][n] = Math.max(excl , incl);
    }

    public static int rodCuttingTabulation(int[] price, int n){
        int[][] dp = new int[n][n + 1];

        for(int i = 0; i <= n; i++){
            dp[0][i] = i * price[0];
        }

        for(int i = 1; i < n; i++){
            for(int j = 0; j <= n; j++) {
                int excl = dp[i - 1][j];

                int incl = 0;
                if(i + 1 < j) incl = price[i] + dp[i][j - (i + 1)];

                dp[i][j] = Math.max(excl, incl);
            }
        }

        return  dp[n - 1][n];
    }

    public static void main(String[] args) {
        int price[] = {3, 5, 8, 9, 10, 17, 17, 20};
        int n = 8;

        System.out.println(rodCuttingTabulation(price, n));
    }
}
