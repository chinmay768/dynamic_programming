package stiver;

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

    public static int lengthOfLISTabulation(int[] nums){
        int[][] dp = new int[nums.length + 1][nums.length + 1];

//        for(int prevIdx = 0; prevIdx <= nums.length; prevIdx++){// for any prevIdx if idx is -1 then return 0
//            dp[nums.length][prevIdx] = 0;
//        }
//        idx 0 to n and  prev 0 to n
        for(int idx = nums.length - 1; idx >= 0; idx--){// start form nums.len - 1 because base case is nums.len
            for(int prevIdx = nums.length; prevIdx > idx ; prevIdx--){
                int takeLen = 0;
                if(prevIdx == nums.length || nums[idx] > nums[prevIdx]){// Take      // We are not doing idx - 1 and prevIdx - 1 because
                    takeLen = 1 + dp[idx + 1][prevIdx];
                }

                int notTakeLen = dp[idx + 1][prevIdx];

                dp[idx][prevIdx] = Math.max(takeLen, notTakeLen);
            }
        }
        return  dp[0][0];
    }


    public static void main(String[] args) {
        int[] nums = {4,10,4,3,8,9};

        System.out.println(lengthOfLISTabulation(nums));
    }
}
