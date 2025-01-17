package stiver;

import java.util.*;

public class SubsetsWithMinimumAbsoluteSumDifference {

    public static int subsetsMinAbsoluteSumDifference(int[] arr){
        int totalSum = Arrays.stream(arr).sum();

        boolean[][] dp = new boolean[arr.length][totalSum + 1];
        dp[0][arr[0]] = true; // No need to add check for arr[0] less than target as the target in sum of all elms and arr[0] will always be less than totalSum
        for(int i = 0; i < dp.length; i++)
            dp[i][0] = true;

        for (int i = 1; i < dp.length; i++){
            for(int tgt = 1; tgt <= totalSum; tgt++){
                boolean excl = dp[i - 1][tgt];

                boolean incl = false;
                if(arr[i] <= tgt) incl = dp[i - 1][tgt - arr[i]];

                dp[i][tgt] = excl || incl;
            }
        }

        int minDiff = Integer.MAX_VALUE;

        for(int i = 0; i <= totalSum; i++){
            if(dp[dp.length - 1][i]){
                int s1 = i;
                int s2 = totalSum - i;
                minDiff = Math.min(minDiff, Math.abs(s2 - s1));
            }
        }

        return minDiff;
    }


    public static void main(String[] args) {
        int[] arr = {8, 6, 5};

        System.out.println(subsetsMinAbsoluteSumDifference(arr));
    }
}
