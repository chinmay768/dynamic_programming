package stiver;

import java.util.Arrays;

public class CountSubsetsWithSumKIncl0 {

    public static int countSubsetsWithSumRecursion(int[] arr, int k){

        return countSubsetsWithSumHelper(arr, k, arr.length - 1);
    }

    public static int countSubsetsWithSumHelper(int[] arr, int sum, int idx){
        if(idx == 0){
            if(arr[idx] == 0 && sum == 0) return 2;
            if(arr[idx] == sum || sum == 0) return 1;
            return 0;
        }

        int excl = countSubsetsWithSumHelper(arr, sum , idx - 1);

        int incl = 0;
        if(arr[idx] <= sum) incl = countSubsetsWithSumHelper(arr, sum - arr[idx], idx - 1);

        return incl + excl;
    }

    public static int countSubsetsWithSumRecursionDP(int[] arr, int k){
        int[][] dp = new int[arr.length][k + 1];
        for(int i = 0; i< arr.length; i++){
            Arrays.fill(dp[i], -1);
        }
        return countSubsetsWithSumHelperDP(arr, k, arr.length - 1, dp);
    }

    public static int countSubsetsWithSumHelperDP(int[] arr, int sum, int idx, int[][] dp){
        if(idx == 0){
            if(arr[idx] == 0 && sum == 0) return 2;
            if(arr[idx] == sum || sum == 0) return 1;
            return 0;
        }

        if(dp[idx][sum] != -1) return dp[idx][sum];

        int excl = countSubsetsWithSumHelperDP(arr, sum , idx - 1, dp);

        int incl = 0;
        if(arr[idx] <= sum) incl = countSubsetsWithSumHelperDP(arr, sum - arr[idx], idx - 1, dp);

        return dp[idx][sum] = incl + excl;
    }

    public static int countSubsetsWithSumTabulation(int[] arr, int sum){
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
        int[] arr = {0, 0, 1};

        System.out.println(countSubsetsWithSumTabulation(arr, 1));
    }
}
