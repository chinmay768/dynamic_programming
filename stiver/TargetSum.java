package stiver;

import java.util.Arrays;

public class TargetSum {

    public static int countPartitionWithGivenDifferenceRecursion(int[] arr, int diff){
        int totalSum = 0;
        for(int i = 0; i < arr.length; i++) totalSum += arr[i];

        if(totalSum - diff < 0 || (totalSum - diff) % 2 != 0) return 0;

        return countPartitionsWithGivenSumTabulation(arr, (totalSum - diff) / 2);
    }

    public static int countPartitionsWithGivenSumTabulation(int[] arr, int sum){
        int[][] dp = new int[arr.length][sum + 1];

        if(arr[0] == 0) dp[0][0] = 2;
        else dp[0][0] = 1;

        if(arr[0] != 0 && arr[0] <= sum) dp[0][arr[0]] = 1;

        for(int i = 1; i < arr.length; i++){
            for(int j = 0; j <= sum; j++){ // Here we are not starting from one because we want to go down to 0th idx
                int excl = dp[i - 1][j];
                int incl = 0;
                if(arr[i] <= j ) incl = dp[i - 1][j - arr[i]];

                dp[i][j] = excl + incl;
            }
        }

        return dp[dp.length - 1][sum];
    }

    public static void main(String[] args) {
        int[] arr = {1,1,1,1,1};
        int target = 3;

        System.out.println(countPartitionWithGivenDifferenceRecursion(arr, target));
    }
}
