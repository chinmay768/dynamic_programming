package stiver;

import java.util.Arrays;

public class UnboundedKnapsack {

    public static int unboundedKnapsackRecrusion(int[] weights, int[] values, int space){
         return unboundedKnapsackRecrusionHelper(weights, values, weights.length - 1, space);
    }

    public static int unboundedKnapsackRecrusionHelper(int[] weights, int[] values, int idx,  int space){
        if(idx == 0){
            if(weights[0] <= space){
                return (space / weights[0]) * values[0];
            }else return 0;
        }

        int excl = unboundedKnapsackRecrusionHelper(weights, values, idx - 1, space);

        int incl = 0;
        if(weights[idx] <= space) incl = values[idx] + unboundedKnapsackRecrusionHelper(weights, values, idx, space - weights[idx]);

        return Math.max(incl, excl);
    }

    public static int unboundedKnapsackRecrusionDP(int[] weights, int[] values, int space){
        int[][] dp = new int[weights.length][space + 1];
        for(int i = 0; i < dp.length; i++){
            Arrays.fill(dp[i], -1);
        }

        return unboundedKnapsackRecrusionHelperDP(weights, values, weights.length - 1, space, dp);
    }

    public static int unboundedKnapsackRecrusionHelperDP(int[] weights, int[] values, int idx,  int space, int[][] dp){
        if(idx == 0){
            if(weights[0] <= space){
                return (space / weights[0]) * values[0];
            }else return 0;
        }

        if(dp[idx][space] != -1) return dp[idx][space];

        int excl = unboundedKnapsackRecrusionHelperDP(weights, values, idx - 1, space, dp);

        int incl = 0;
        if(weights[idx] <= space) incl = values[idx] + unboundedKnapsackRecrusionHelperDP(weights, values, idx, space - weights[idx], dp);

        return dp[idx][space] = Math.max(incl, excl);
    }

    public static int unboundedKnapsackTabulation(int[] weights, int[] values, int space){
        int[][] dp = new int[weights.length][space + 1];

        for(int i = 0; i <= space; i++){
            dp[0][i] = (i / weights[0]) * values[0];
        }

        for(int i = 1; i < weights.length; i++){
            for(int j = 0; j <= space; j++){
                int excl = dp[i - 1][j];

                int incl = 0;
                if(weights[i] <= j) incl = values[i] + dp[i][j - weights[i]];

                dp[i][j] = Math.max(excl, incl);
            }
        }

        return dp[dp.length - 1][space];
    }

    public static void main(String[] args) {
        int[] weights = {2, 4, 6};
        int[] values = {5, 11, 13};

        System.out.println(unboundedKnapsackTabulation(weights, values, 10));
    }
}
