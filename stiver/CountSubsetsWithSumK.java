package stiver;

import javax.print.DocFlavor;

public class CountSubsetsWithSumK {

    public static int countSubsetsWithSumKRecursion(int[] arr, int k){
        return countSubsetsWithSumKHelper(arr, arr.length - 1, k);
    }

    public static int countSubsetsWithSumKHelper(int[] arr, int idx, int sum){
        if(sum == 0) return 1;

        if(idx == 0){
            return (arr[0] == sum)? 1 : 0;
        }

        int excl = countSubsetsWithSumKHelper(arr, idx - 1, sum);

        int incl = 0;
        if(arr[idx] <= sum) incl = countSubsetsWithSumKHelper(arr, idx - 1, sum - arr[idx]);

        return excl + incl;
    }

    public static int countSubsetsWithSumKRecursionDP(int[] arr, int k){
        int[][] dp = new int[arr.length][k + 1];
        for(int i =0; i < dp.length; i++){
            for(int j = 0; j < dp[i].length; j++){
                dp[i][j] = -1;
            }
        }
        return countSubsetsWithSumKHelperDP(arr, arr.length - 1, k, dp);
    }

    public static int countSubsetsWithSumKHelperDP(int[] arr, int idx, int sum, int[][] dp){
        if(sum == 0) return 1;

        if(idx == 0){
            return (arr[0] == sum)? 1 : 0;
        }

        if(dp[idx][sum] != -1) return dp[idx][sum];

        int excl = countSubsetsWithSumKHelperDP(arr, idx - 1, sum, dp);

        int incl = 0;
        if(arr[idx] <= sum) incl = countSubsetsWithSumKHelperDP(arr, idx - 1, sum - arr[idx], dp);

        return dp[idx][sum] = excl + incl;
    }

    public static int countSubsetsWithSumTabulation(int[] arr, int k){
        int[][] dp = new int[arr.length][k + 1];

        for(int i = 0; i < dp.length; i++){
            dp[i][0] = 1;
        }

        if(arr[0] <= k) dp[0][arr[0]] = 1;

        for(int i = 1; i < dp.length; i++){
            for(int sum = 1; sum < dp[i].length; sum++){
                int excl = dp[i - 1][sum];

                int incl = 0;
                if(arr[i] <= sum) incl = dp[i - 1][sum - arr[i]];

                dp[i][sum] = excl + incl;
            }
        }

        return dp[dp.length - 1][k];
    }

    public static int countSubsetsWithSumTabulationSpaceOptimized(int[] arr, int k){
        int[] prev = new int[k + 1];
        prev[0] = 1;

        if(arr[0] <= k) prev[arr[0]] = 1;

        for(int i = 1; i < arr.length; i++){
            int[] curr = new int[k + 1];
            curr[0] = 1;
            for(int sum = 1; sum <= k; sum++){
                int excl = prev[sum];

                int incl = 0;
                if(arr[i] <= sum) incl = prev[sum - arr[i]];

                curr[sum] = excl + incl;
            }
            prev = curr;
        }

        return prev[k];
    }

    public static void main(String[] args) {
        int[] arr = {2, 1 , 3, 5, 2};

        System.out.println(countSubsetsWithSumTabulationSpaceOptimized(arr, 5));
    }
}
