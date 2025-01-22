package stiver;

import java.util.Arrays;

public class ZeroOneKnapsack {
    public static int knapsackRecursion(int[] weights, int[] values, int space){
        return knapsackRecursionHelper(weights, values, weights.length - 1, space);
    }

    public static int knapsackRecursionHelper(int[] weights, int[] values, int idx, int space){
        if(idx == 0){
            if(weights[0] <= space) return values[0];
            else return 0; // Why can't we take Math.MIN_VALUE here bc if not take then the value will be 0
        }

        int excl = knapsackRecursionHelper(weights, values, idx - 1, space);

        int incl = Integer.MIN_VALUE;
        if(weights[idx] <= space) incl = values[idx] + knapsackRecursionHelper(weights, values, idx - 1, space - weights[idx]);

        return Math.max(excl, incl);
    }

    public static int knapsackRecursionDP(int[] weights, int[] values, int space){
        int[][] dp = new int[weights.length][space + 1];
        for (int i = 0; i < dp.length; i++){
            Arrays.fill(dp[i], -1);
        }
        return knapsackRecursionHelperDP(weights, values, weights.length - 1, space, dp);
    }

    public static int knapsackRecursionHelperDP(int[] weights, int[] values, int idx, int space, int[][] dp){
        if(idx == 0){
            if(weights[0] <= space) return values[0];
            else return 0; // Why can't we take Math.MIN_VALUE here
        }

        if(dp[idx][space] != -1) return dp[idx][space];

        int excl = knapsackRecursionHelperDP(weights, values, idx - 1, space, dp);

        int incl = Integer.MIN_VALUE;
        if(weights[idx] <= space) incl = values[idx] + knapsackRecursionHelperDP(weights, values, idx - 1, space - weights[idx], dp);

        return dp[idx][space] = Math.max(excl, incl);
    }

    public static int knapsackTabulation(int[] weights, int[] values, int space){
        int[][] dp = new int[weights.length][space + 1];

        for(int i = weights[0]; i < space; i++){
            dp[0][i] = values[i];
        }

        for(int i = 1; i < weights.length; i++){
            for(int j = 0; j <= space; j++){
                int excl = dp[i - 1][j];
                int incl = Integer.MIN_VALUE;
                if(weights[i] <= space) incl = values[i] + dp[i - 1][space - weights[i]];

                dp[i][j] = Math.max(excl, incl);
            }
        }

        return dp[dp.length - 1][space];
    }

    public static int knapsackTabulationSpaceOptimized(int[] weights, int[] values, int space){
        int[] prev = new int[space + 1];

        for(int i = weights[0]; i < space; i++){
            prev[i] = values[i];
        }

        for(int i = 1; i < weights.length; i++){
            int[] curr = new int[space + 1];
            for(int j = 0; j <= space; j++){
                int excl = prev[j];
                int incl = Integer.MIN_VALUE;
                if(weights[i] <= space) incl = values[i] + prev[space - weights[i]];

                curr[j] = Math.max(excl, incl);
            }
            prev = curr;
        }

        return prev[space];
    }

    public static void main(String[] args) {
        int[] weights = {5, 4, 6, 3};
        int[] values = {10, 40, 30, 50};

        System.out.println(knapsackTabulationSpaceOptimized(weights, values, 3));
    }
}
