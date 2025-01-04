package stiver;

import java.util.Arrays;

public class HouseRobberORMaxSumNonAdjacentElms {

    static int getMaxSum(int[] nums, int i, int[] dp){
        if(i >= nums.length) return 0;

        if(dp[i] != -1) return dp[i];

        int incl = getMaxSum(nums, i + 2, dp) + nums[i];
        int excl = getMaxSum(nums, i + 1, dp);

        return dp[i] = Math.max(incl, excl);
    }

    public static int rob(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        return getMaxSum(nums, 0, dp);
    }

    public static int getMaxSumTabulation(int[] nums){
        int[] dp = new int[nums.length];
        dp[dp.length - 1] = nums[nums.length - 1];

        for(int i = nums.length - 2; i >= 0; i--){
            int incl = nums[i];
            if(i + 2 < dp.length) incl += dp[i + 2];

            int excl = dp[i + 1];

            dp[i] = Math.max(incl, excl);
        }

        return dp[0];
    }

    public int getMaxSumTabulationSpaceOptimized(int[] nums) {
        int prev = nums[nums.length - 1];
        int prev2 = 0;
        int curr = 0;

        for(int i = nums.length - 2; i >= 0; i--){
            int incl = nums[i] + prev2;

            int excl = prev;

            curr = Math.max(incl, excl);
            prev2 = prev;
            prev = curr;
        }

        return prev;
        // Need to return prev incase array size is 1 and the for loop never runs
    }

    public static void main(String[] args) {
        int[]  nums = {1,2,3,1};

        System.out.println(getMaxSumTabulation(nums));
    }
}
