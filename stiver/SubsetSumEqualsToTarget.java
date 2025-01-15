package stiver;

import java.util.Arrays;

public class SubsetSumEqualsToTarget {

    public static Boolean subsetSumEqualsToTargetRecursion(int[] arr, int idx, int target){
        if(target == 0) return true;
        if(idx == 0) return arr[idx] == target;

        Boolean incl = false;
        if(arr[idx] <= target)
            incl = subsetSumEqualsToTargetRecursion(arr, idx - 1, target - arr[idx]);

        Boolean excl = subsetSumEqualsToTargetRecursion(arr, idx - 1, target);

        return incl || excl;
    }

    public static Boolean subsetSumEqualsToTargetRecursionDP(int[] arr, int idx, int target){
        int[][] dp = new int[arr.length][target + 1];
        for(int i = 0; i < dp.length; i++){
            for(int j = 0; j < dp[0].length; j++){
                dp[i][j] = -1;
            }
        }

        return subsetSumEqualsToTargetRecursionDPHelper(arr, idx, target, dp);
    }

    public static Boolean subsetSumEqualsToTargetRecursionDPHelper(int[] arr, int idx, int target, int[][] dp){
        if(target == 0) return true;
        if(idx == 0) return arr[idx] == target;

        if(dp[idx][target] != -1) return dp[idx][target] == 1;

        Boolean incl = false;
        if(arr[idx] <= target)
            incl = subsetSumEqualsToTargetRecursionDPHelper(arr, idx - 1, target - arr[idx], dp);

        Boolean excl = subsetSumEqualsToTargetRecursionDPHelper(arr, idx - 1, target, dp);

        dp[idx][target] = (incl || excl)? 1 : 0;

        return incl || excl;
    }

    public static Boolean subsetSumEqualsToTargetRecursionTabulation(int[] arr, int target){
        Boolean[][] dp = new Boolean[arr.length][target + 1];
        for(int i = 0; i < dp.length; i++){
            for (int j = 0; j < dp[0].length; j++){
                dp[i][j] = false;
            }
        }

        for(int i = 0; i < arr.length; i++){
            dp[i][0] = true;
        }

        if(target >= arr[0]) dp[0][arr[0]] = true; // If idx is 0 then only the element present at idx 0 is marked as true

        for(int i = 1; i < arr.length; i++){
            for(int tgt = 1; tgt <= target; tgt++){
                boolean excl = dp[i - 1][tgt];

                boolean incl = false;
                if(arr[i] <= tgt) incl = dp[i - 1][tgt - arr[i]];

                dp[i][tgt] = excl || incl;
            }
        }
        return dp[arr.length - 1][target];
    }

    public static Boolean subsetSumEqualsToTargetRecursionTabulationSpaceOptimized(int[] arr, int target){
        Boolean[] prev = new Boolean[target + 1];
        Arrays.fill(prev, false);

        prev[0] = true;

        if(target >= arr[0]) prev[arr[0]] = true; // If idx is 0 then only the element present at idx 0 is marked as true

        for(int i = 1; i < arr.length; i++){
            Boolean[] curr = new Boolean[target + 1];
            curr[0] = true;
            for(int tgt = 1; tgt <= target; tgt++){
                boolean excl = prev[tgt];

                boolean incl = false;
                if(arr[i] <= tgt) incl = prev[tgt - arr[i]];

                curr[tgt] = excl || incl;
            }
            prev = curr;
        }
        return prev[target];
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 3, 9, 3};
        int target = 8;

//        System.out.println(subsetSumEqualsToTargetRecursionDP(arr, arr.length - 1, target));
        System.out.println(subsetSumEqualsToTargetRecursionTabulationSpaceOptimized(arr, target));
    }
}
