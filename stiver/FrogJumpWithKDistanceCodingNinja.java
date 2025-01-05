package stiver;

import java.util.Arrays;

public class FrogJumpWithKDistanceCodingNinja {

    public static int frogWithKJumpsRecursion(int idx, int k, int[] heights){
        if(idx == 0) return 0;

        int minCost = Integer.MAX_VALUE;
        for(int i = 1; i <= k; i++){
            if(idx - i >= 0){
                int kthCost = frogWithKJumpsRecursion(idx - i, k, heights) + Math.abs(heights[idx] - heights[idx - i]);
                minCost = Math.min(minCost, kthCost);
            }
        }

        return  minCost;
    }

    public static int frogWithKJumpsRecursionDP(int idx, int k, int[] heights, int[] dp){
        if(idx == 0) return 0;
        if(dp[idx] != -1) return dp[idx];

        int minCost = Integer.MAX_VALUE;
        for(int i = 1; i <= k; i++){
            if(idx - i >= 0){
                int kthCost = frogWithKJumpsRecursion(idx - i, k, heights) + Math.abs(heights[idx] - heights[idx - i]);
                minCost = Math.min(minCost, kthCost);
            }
        }

        return  dp[idx] = minCost;
    }

    public static int frogWithKJumpsTabulation(int[] heights, int k){
        int[] dp = new int[heights.length];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for(int i = 1; i < heights.length; i++){
            int minCost = Integer.MAX_VALUE;
            for(int j = 1; j <= k; j++){
                if(i - j >= 0){
                    int currCost = dp[i - j] + Math.abs(heights[i] - heights[i - j]);
                    minCost = Math.min(minCost, currCost);
                }
            }
            dp[i] = minCost;
        }

        return dp[dp.length - 1];
    }

    public static void main(String[] args) {
        int[] heights = {7, 4, 4, 2, 6, 6, 3, 4 };
        int[] dp = new int[heights.length];
        Arrays.fill(dp, -1);

//        System.out.println(frogWithKJumpsRecursionDP(heights.length - 1, 2, heights, dp));

        System.out.println(frogWithKJumpsTabulation(heights, 2));
    }
}
