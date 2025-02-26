package stiver;

import java.util.Arrays;

public class MatrixChainMultiplication {

    // TC: exponential SC:
    public static int mcmRecursion(int[] arr, int i, int j){// Top Down Soln
        if(i == j) return 0;

        int minSteps = Integer.MAX_VALUE;
        for(int k = i; k < j; k++){
            int currSteps = mcmRecursion(arr, i, k) + mcmRecursion(arr, k + 1, j)
                    + arr[i - 1] * arr[k] * arr[j]; // This formula get previous partition's mcm and next partition's mcm

            minSteps = Math.min(minSteps, currSteps);
        }

        return minSteps;
    }

    // TC: O(n * n) * n  SC: O(n^2)
    public static int mcmRecursionDP(int[] arr){
        int[][] dp = new int[arr.length][arr.length];
        for(int idx = 0; idx < dp.length; idx++){
            Arrays.fill(dp[idx], -1);
        }
        return mcmDPHelper(arr, 1, arr.length - 1, dp);
    }

    public static int mcmDPHelper(int[] arr, int i, int j, int[][] dp){
        if(i == j) return 0;

        if(dp[i][j] != -1) return dp[i][j];

        int minSteps = Integer.MAX_VALUE;
        for(int k = i; k < j; k++){
            int currSteps = mcmDPHelper(arr, i, k, dp) + mcmDPHelper(arr, k + 1, j, dp)
                    + arr[i - 1] * arr[k] * arr[j]; // This formula get previous partition's mcm and next partition's mcm

            minSteps = Math.min(minSteps, currSteps);
        }

        return dp[i][j] = minSteps;
    }

    public static int mcmTabulation(int[] arr){// Bottom Up Soln
        int[][] dp = new int[arr.length][arr.length];

        for(int i = arr.length - 1; i >= 1; i--){
            for(int j = i + 1; j < arr.length; j++){ // These two loops define the travesable area
                int minSteps = Integer.MAX_VALUE;

                for(int k = i; k < j ; k++){
                    int currSteps = dp[i][k] + dp[k + 1][j]
                            + arr[i - 1] * arr[k] * arr[j];

                    minSteps = Math.min(minSteps, currSteps);
                }

                dp[i][j] = minSteps;
            }
        }

        return dp[1][arr.length - 1];
    }


    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 3};

        System.out.println(mcmTabulation(arr));// We are passing i as 1 because the traversable part starts from 1
    }

}
