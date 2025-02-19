package stiver;

import java.util.Arrays;

public class LongestIncreasingSubsequenceBinarySearch {

    public static int longestCommonSubsequenceLength(int[] nums){
        int[] dp = new int[nums.length];
        int idxToFill = 0;

        dp[idxToFill] = nums[0];
        idxToFill++;

        for(int i = 1; i < nums.length; i++){
            if(nums[i] > dp[idxToFill - 1]){
                dp[idxToFill] = nums[i];
                idxToFill++;
            }else{
                int idxToReplace = lowerBoundBinarySearch(Arrays.copyOfRange(dp, 0, idxToFill) , nums[i]);
                dp[idxToReplace] = nums[i];
            }
        }

        return idxToFill;
    }

    // This method return the idx of the key or the next greater element than key
    public static int lowerBoundBinarySearch(int[] nums, int key){
        int low = 0;
        int high = nums.length - 1;

        while (low < high){
            int mid = low + (high - low) / 2;

            if(nums[mid] == key){
                return mid;
            }else if (nums[mid] > key){
                high = mid;
            }else {
                low = mid + 1;
            }
        }

        return low; // Because low will point at the closest idx which is greater than key
    }

    public static void main(String[] args) {
        int[] arr = {10,9,2,5,3,7,101,18};

        System.out.println(longestCommonSubsequenceLength(arr));
    }
}
