package stiver;

import java.util.Arrays;

public class DistinctSubsequences {
    //TC : O(2^n)  SC: O(n)
    public static int distinctSubsequencesRecursion(String str1, String str2){
        return distinctSubsequencesRecursionHelper(str1, str2, str1.length() - 1, str2.length() - 1);
    }

    public static int distinctSubsequencesRecursionHelper(String str1, String str2, int i, int j){
        if(j < 0) return 1;
       if(i < 0) return 0;

       if(str1.charAt(i) == str2.charAt(j)) {
            return  distinctSubsequencesRecursionHelper(str1, str2, i - 1, j - 1)
                    + distinctSubsequencesRecursionHelper(str1, str2, i - 1, j);
       }

        return distinctSubsequencesRecursionHelper(str1, str2, i - 1, j);
    }

    // TC: O(N * M) SC: O(N * M) + O(N)
    public static int distinctSubsequencesRecursionDP(String str1, String str2){
        int[][] dp = new int[str1.length()][str2.length()];
        for(int i = 0; i < dp.length; i++){
            Arrays.fill(dp[i], - 1);
        }

        return distinctSubsequencesRecursionHelperDP(str1, str2, str1.length() - 1, str2.length() - 1, dp);
    }

    public static int distinctSubsequencesRecursionHelperDP(String str1, String str2, int i, int j, int[][] dp){
        if(j < 0) return 1; // We are checking j before i because there are cases where i is exhausted and also j is exhausted
        if(i < 0) return 0;

        if(dp[i][j] != -1) return dp[i][j];

        if(str1.charAt(i) == str2.charAt(j)) {
            return dp[i][j] = distinctSubsequencesRecursionHelperDP(str1, str2, i - 1, j - 1, dp)
                    + distinctSubsequencesRecursionHelperDP(str1, str2, i - 1, j, dp);
        }

        return dp[i][j] = distinctSubsequencesRecursionHelperDP(str1, str2, i - 1, j, dp);
    }

    public static int distinctSubsequenceTabulation(String str1, String str2){
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];

        // Here also first j is computed and then i because if both i and j are exhausted
        for (int j = 0; j < dp[0].length; j++){
            dp[0][j] = 0;
        }

        for(int i = 0; i < dp.length; i++){
            dp[i][0] = 1;
        }

        for(int i = 1; i < dp.length; i++){
            for (int j = 1; j < dp[0].length; j++){
                if(str1.charAt(i - 1) == str2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[dp.length - 1][dp[0].length - 1];
    }

    public static void main(String[] args) {
        String str1 = "rabbbit";
        String str2 = "rabbit";

        System.out.println(distinctSubsequenceTabulation(str1, str2));
    }
}
