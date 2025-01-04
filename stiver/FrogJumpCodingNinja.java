package stiver;

import java.util.Arrays;

public class FrogJumpCodingNinja {

    public static int frogJumpRecursive(int idx, int[] heights, int[] dp){
        if(idx == 0) return 0;
        if(dp[idx] != -1) return dp[idx];

        int left = frogJumpRecursive(idx - 1, heights, dp) + Math.abs(heights[idx] - heights[idx - 1]);
        int right = Integer.MAX_VALUE;
        if(idx > 1)
            right = frogJumpRecursive(idx - 2, heights, dp) + Math.abs(heights[idx] - heights[idx - 2]);

        return Math.min(left, right);
    }

    public static int frogJumpTabulation(int[] heights){
        int[] dp = new int[heights.length];
        dp[0] = 0;

        for(int i = 1; i < heights.length; i++){
            int firtStep = dp[i - 1] + Math.abs(heights[i] - heights[i - 1]);
            int secondStep = Integer.MAX_VALUE;
            if(i > 1){
                secondStep = dp[i - 2] + Math.abs(heights[i] - heights[i - 2]);
            }
            dp[i] = Math.min(firtStep, secondStep);
        }

        return dp[dp.length - 1];
    }

    public static int frogJumpTabulationSpaceOptimized(int[] heights){
        int prev = 0;
        int prev2 = 0;
        int curr = 0;

        for(int i = 1; i < heights.length; i++){
            int firtStep = prev + Math.abs(heights[i] - heights[i - 1]);
            int secondStep = Integer.MAX_VALUE;
            if(i > 1){
                secondStep = prev2 + Math.abs(heights[i] - heights[i - 2]);
            }
            curr = Math.min(firtStep, secondStep);
            prev2 = prev;
            prev = curr;
        }

        return curr;
    }

    public static void main(String[] args) {
        int[] heights = {7, 4, 4, 2, 6, 6, 3, 4 };
        int[] dp = new int[heights.length];
        Arrays.fill(dp, -1);

        int res = frogJumpRecursive(7, heights, dp);
        int res2 = frogJumpTabulation(heights);
        int res3 = frogJumpTabulationSpaceOptimized(heights);
        System.out.println(res3);
    }
}
