package stiver;

import java.util.Arrays;

public class CountPartitionsWithGivenDifference {

    public static int countPartitionWithGivenDifferenceRecursion(int[] arr, int diff){
        int totalSum = 0;
        for(int i = 0; i < arr.length; i++) totalSum += arr[i];

        if(totalSum - diff < 0 || (totalSum - diff) % 2 != 0){
            return 0;
        }

        int[][] dp = new int[arr.length][totalSum /2 + 1];
        for(int i = 0; i < dp.length; i++){
            Arrays.fill(dp[i], -1);
        }

//        return countPartitionsWithGivenSumRecursionHelperDP(arr, arr.length - 1, (totalSum - diff) / 2, dp);
        return countPartitionsWithGivenSumTabulation(arr, (totalSum - diff) / 2);
    }

    public static int countPartitionsWithGivenSumRecursionHelper(int[] arr, int idx, int sum){
        if(idx == 0){
            if(arr[0] == 0 && sum == 0) return 2;
            if(arr[0] == sum || sum == 0) return 1;
            return 0;
        }

        int excl = countPartitionsWithGivenSumRecursionHelper(arr, idx - 1, sum);

        int incl = 0;
        if(arr[idx] <= sum) incl = countPartitionsWithGivenSumRecursionHelper(arr, idx - 1, sum - arr[idx]);

        return excl + incl;
    }

    public static int countPartitionsWithGivenSumRecursionHelperDP(int[] arr, int idx, int sum, int[][] dp){
        if(idx == 0){
            if(arr[0] == 0 && sum == 0) return 2;
            if(arr[0] == sum || sum == 0) return 1;
            return 0;
        }

        if(dp[idx][sum] != -1) return dp[idx][sum];

        int excl = countPartitionsWithGivenSumRecursionHelperDP(arr, idx - 1, sum, dp);

        int incl = 0;
        if(arr[idx] <= sum) incl = countPartitionsWithGivenSumRecursionHelperDP(arr, idx - 1, sum - arr[idx], dp);

        return dp[idx][sum] = excl + incl;
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
        int[] arr = {3, 2, 2, 5 ,1};
        int diff = 1;

        System.out.println(countPartitionWithGivenDifferenceRecursion(arr, diff));
    }
}
