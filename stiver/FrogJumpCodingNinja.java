package stiver;

import java.util.Arrays;

public class FrogJumpCodingNinja {

    public static int func(int idx, int[] heights, int[] dp){
        if(idx == 0) return 0;
        if(dp[idx] != -1) return dp[idx];

        int left = func(idx - 1, heights, dp) + Math.abs(heights[idx] - heights[idx - 1]);
        int right = Integer.MAX_VALUE;
        if(idx > 1)
            right = func(idx - 2, heights, dp) + Math.abs(heights[idx] - heights[idx - 2]);

        return Math.min(left, right);
    }

    public static void main(String[] args) {
        int[] heights = {7, 4, 4, 2, 6, 6, 3, 4 };
        int[] dp = new int[heights.length];
        Arrays.fill(dp, -1);

        int res = func(7, heights, dp);
        System.out.println(res);
    }
}
