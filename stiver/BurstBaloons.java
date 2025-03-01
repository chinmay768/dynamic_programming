package stiver;

import java.util.Arrays;

public class BurstBaloons {

    //TC: Exponential
    public static int maxCoinsRecursion(int[] nums){
        int[] numsModified = new int[nums.length + 2];
        numsModified[0] = 1;
        numsModified[nums.length + 1] = 1;

        for(int i = 0; i < nums.length; i++){
            numsModified[i + 1] = nums[i];
        }

        return maxCoinsRecursionHelper(numsModified, 1, numsModified.length - 2);
    }

    public static int maxCoinsRecursionHelper(int[] nums, int i, int j){
        if(i > j) return 0;

        int maxCoins = Integer.MIN_VALUE;
        for(int k = i; k <= j; k++){
            int currCoins = maxCoinsRecursionHelper(nums, i, k - 1)
                    + maxCoinsRecursionHelper(nums, k + 1, j)
                    + nums[i - 1] * nums[k] * nums[j + 1];

            maxCoins = Math.max(maxCoins, currCoins);
        }

        return maxCoins;
    }

    //TC: O(n*n*n) n^3   // SC: O(n^2) + O(n)
    public static int maxCoinsRecursionDP(int[] nums){
        int[] numsModified = new int[nums.length + 2];
        numsModified[0] = 1;
        numsModified[nums.length + 1] = 1;

        for(int i = 0; i < nums.length; i++){
            numsModified[i + 1] = nums[i];
        }

        int[][] dp = new int[nums.length + 1][nums.length + 1]; //
        for(int i = 0; i < dp.length; i++){
            Arrays.fill(dp[i], -1);
        }

        return maxCoinsRecursionHelperDP(numsModified, 1, numsModified.length - 2, dp);
    }

    public static int maxCoinsRecursionHelperDP(int[] nums, int i, int j, int[][] dp){
        if(i > j) return 0;

        if(dp[i][j] != -1) return dp[i][j];

        int maxCoins = Integer.MIN_VALUE;
        for(int k = i; k <= j; k++){
            int currCoins = maxCoinsRecursionHelperDP(nums, i, k - 1, dp)
                    + maxCoinsRecursionHelperDP(nums, k + 1, j, dp)
                    + nums[i - 1] * nums[k] * nums[j + 1];

            maxCoins = Math.max(maxCoins, currCoins);
        }

        return dp[i][j] = maxCoins;
    }

    // TC: O(n^3)   SC: O(n^2)
    public static int maxCoinsTabulation(int[] nums){ // In tabulation we will try all possible combinations of i and j
        int[] modifiedNums = new int[nums.length + 2];
        modifiedNums[0] = 1;
        modifiedNums[nums.length + 1] = 1;
        for(int i = 0; i < nums.length; i++){
            modifiedNums[i + 1] = nums[i];
        }

        int[][] dp = new int[nums.length + 2][nums.length + 2]; // Since the dp will also access i - 1 and j + 1

        for(int i = modifiedNums.length - 2; i >= 1; i--){
            for(int j = 1; j <= modifiedNums.length - 2; j++){
                if(i > j) continue; // This is the base case

                int maxCoins = Integer.MIN_VALUE;
                for(int k = i; k <= j; k++){
                    int currCoins = dp[i][k - 1] + dp[k + 1][j] + modifiedNums[i - 1] * modifiedNums[k] * modifiedNums[j + 1];

                    maxCoins = Math.max(maxCoins, currCoins);
                }

                dp[i][j] = maxCoins;
            }
        }

        return dp[1][nums.length]; // dp[i][j] the original traversable are of nums
    }

    public static void main(String[] args) {
        int[] arr= {3,1,5,8};

        System.out.println(maxCoinsTabulation(arr));
    }
}
