package stiver;

import java.util.Arrays;

public class MinimumCostToCutAStick {


    // TC: Exponential SC:
    public static int minCostRecursion(int n, int[] cuts) {
        Arrays.sort(cuts);
        int[] cutsModified = new int[cuts.length + 2];
        cutsModified[0] = 0;
        cutsModified[cutsModified.length - 1] = n;
        for(int i = 1; i <= cuts.length; i++){
            cutsModified[i] = cuts[i - 1];
        }

        System.out.println(Arrays.toString(cutsModified));
        return minCostRecursionHelper(cutsModified, 1, cutsModified.length - 2);
    }

    public static int minCostRecursionHelper(int[] cuts, int i, int j) {
        if(i > j) return 0; // Because i == j cost will be 1

        int min = Integer.MAX_VALUE;
        for(int k = i; k <= j; k++){
            int cost = cuts[j + 1] - cuts[i - 1]
                    + minCostRecursionHelper(cuts, i, k - 1)
                    + minCostRecursionHelper(cuts, k + 1, j);

            min = Math.min(min, cost);
        }

        return min;
    }

    public static int minCostDP(int n, int[] cuts) {
        Arrays.sort(cuts);
        int[] cutsModified = new int[cuts.length + 2];
        cutsModified[0] = 0;
        cutsModified[cutsModified.length - 1] = n;
        for(int i = 1; i <= cuts.length; i++){
            cutsModified[i] = cuts[i - 1];
        }

        int[][] dp = new int[cuts.length + 1][cuts.length + 1]; // +1 Because we have added one element in front which has shifted the indices by 1
        for(int i = 0; i < dp.length; i++){
            Arrays.fill(dp[i], -1);
        }

        return minCostDPHelper(cutsModified, 1, cutsModified.length - 2, dp);
    }

    public static int minCostDPHelper(int[] cuts, int i, int j, int[][] dp) {
        if(i > j) return 0; // Because i == j cost will be 1

        if(dp[i][j] != -1) return dp[i][j];
        int min = Integer.MAX_VALUE;
        for(int k = i; k <= j; k++){
            int cost = cuts[j + 1] - cuts[i - 1]
                    + minCostDPHelper(cuts, i, k - 1, dp)
                    + minCostDPHelper(cuts, k + 1, j, dp);

            min = Math.min(min, cost);
        }

        return dp[i][j] = min;
    }

    public static void main(String[] args) {
        int[] cuts = {3, 5, 1, 4};

        System.out.println(minCostDP(7, cuts));
    }
}
