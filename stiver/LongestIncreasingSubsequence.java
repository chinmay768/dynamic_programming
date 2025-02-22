package stiver;

import java.util.ArrayList;
import java.util.Arrays;

public class LongestIncreasingSubsequence {

    public static int lengthOfLISRecursion(int[] nums) {
        return lengthOfLISRecursionHelper(nums, nums.length - 1, nums.length);
    }

    public static int lengthOfLISRecursionHelper(int[] nums, int idx, int prevIdx){
        if(idx < 0) return 0;

        int takeLen = 0;
        if(prevIdx == nums.length || nums[idx] < nums[prevIdx]){// Take
            takeLen = 1 + lengthOfLISRecursionHelper(nums, idx - 1, idx);
        }

        int notTakeLen = lengthOfLISRecursionHelper(nums, idx - 1, prevIdx);

        return Math.max(takeLen, notTakeLen);
    }

    public static int lengthOfLISRecursionDP(int[] nums) {
        int[][] dp = new int[nums.length + 1][nums.length + 1];
        for(int i = 0; i < dp.length; i++){
            Arrays.fill(dp[i], -1);
        }
        return lengthOfLISRecursionHelperDP(nums, nums.length - 1, nums.length, dp);
    }

    public static int lengthOfLISRecursionHelperDP(int[] nums, int idx, int prevIdx, int[][] dp){
        if(idx < 0) return 0;

        if(dp[idx][prevIdx] != -1) return dp[idx][prevIdx];

        int takeLen = 0;
        if(prevIdx == nums.length || nums[idx] < nums[prevIdx]){// Take
            takeLen = 1 + lengthOfLISRecursionHelperDP(nums, idx - 1, idx, dp);
        }

        int notTakeLen = lengthOfLISRecursionHelperDP(nums, idx - 1, prevIdx, dp);

        return dp[idx][prevIdx] = Math.max(takeLen, notTakeLen);
    }

//    public static int lengthOfLISTabulation(int[] nums) { Do not try for solution where you have to select one form multiple indexes
//        int[][] dp = new int[nums.length + 1][nums.length + 1];
//
//        for(int prevIdx = 0; prevIdx <= nums.length; prevIdx++){// for any prevIdx if idx is -1 then return 0
//            dp[0][prevIdx] = 0;
//        }
//
//        for(int idx = 1; idx <= nums.length; idx++){
//            for(int prevIdx = 0; prevIdx < idx; prevIdx++){
//
//                int takeLen = 0;
//                if(prevIdx == 0 || nums[idx - 1] > nums[prevIdx - 1]){// Take
//                    takeLen = 1 + dp[idx - 1][prevIdx];
//                }
//
//                int notTakeLen = dp[idx - 1][prevIdx];
//
//                dp[idx][idx] = Math.max(takeLen, notTakeLen);
//            }
//        }
//        int res = 0;
//        for(int i = 0; i <= nums.length; i++){
//            res = Math.max(res, dp[nums.length][i]);
//        }
//        return res;
//    }

    // FIXME: Longest Increasing Subsequence

    public static int lengthOfLISTabulation(int[] nums){
        int[] dp = new int[nums.length];

        // Because every element is a part of subsequence starting from itself
        Arrays.fill(dp, 1);

        int maxLen = 1; // Max len is 1 bc every element is a part of subsequence starting from itself
        for(int i = 1; i < nums.length; i++){ // Skipping first idx bc its always be 1 as there's not elm before it
            for(int prev_idx = 0; prev_idx < i; prev_idx++){
                if(nums[i] > nums[prev_idx]){
                    dp[i] = Math.max(dp[i], dp[prev_idx] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }


    public static void main(String[] args) {
        int[] nums = {10,9,2,5,3,7,101,18};

        System.out.println(lengthOfLISTabulation(nums));
    }
}
