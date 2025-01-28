package stiver;

import java.util.Arrays;

public class LongestCommonSubsequence {

    public static int longestCommonSubsequenceRecursion(String str1, String str2){
        return longestCommonSubsequenceRecursionHelper(str1, str2, str1.length() - 1, str2.length() - 1);
    }

    public static int longestCommonSubsequenceRecursionHelper(String str1, String str2, int idx1, int idx2){

        // Here the base case is not 0 because if the string lengths are diff and we want one idx to stay at idx 0 while
        // the other one continues the recurrence
        if(idx1 == -1 || idx2 == -1){
            return 0;
        }

        if(str1.charAt(idx1) == str2.charAt(idx2)){
            return 1 + longestCommonSubsequenceRecursionHelper(str1, str2, idx1 - 1, idx2 - 1);
        }else {
            return Math.max(
                    longestCommonSubsequenceRecursionHelper(str1, str2, idx1 - 1, idx2),
                    longestCommonSubsequenceRecursionHelper(str1, str2, idx1, idx2 - 1)
            );
        }
    }

    //TC O(N * M)
    //SC O(N * M) + O(N + M)
    public static int longestCommonSubsequenceRecursionDP(String str1, String str2){
        int[][] dp = new int[str1.length()][str2.length()];
        for (int i = 0; i < dp.length; i++){
            Arrays.fill(dp[i], -1);
        }
        return longestCommonSubsequenceRecursionHelperDP(str1, str2, str1.length() - 1, str2.length() - 1, dp);
    }

    public static int longestCommonSubsequenceRecursionHelperDP(String str1, String str2, int idx1, int idx2, int[][] dp){
        // Here the base case is not 0 because if the string lengths are diff and we want one idx to stay at idx 0 while
        // the other one continues the recurrence
        if(idx1 == -1 || idx2 == -1){
            return 0;
        }

        if(dp[idx1][idx2]  != -1) return dp[idx1][idx2];

        if(str1.charAt(idx1) == str2.charAt(idx2)){
            return 1 + longestCommonSubsequenceRecursionHelperDP(str1, str2, idx1 - 1, idx2 - 1, dp);
        }else {
            return dp[idx1][idx2] = Math.max(
                    longestCommonSubsequenceRecursionHelperDP(str1, str2, idx1 - 1, idx2, dp),
                    longestCommonSubsequenceRecursionHelperDP(str1, str2, idx1, idx2 - 1, dp)
            );
        }
    }

    public static int longestCommonSubsequenceRecursionTabulation(String text1, String text2){
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];

        for(int i = 0; i < dp.length; i++){
            dp[i][0] = 0;
        }

        for (int j = 0; j < dp[0].length; j++){
            dp[0][j] = 0;
        }

        for (int i = 1; i <= text1.length(); i++){
            for(int j = 1; j <= text2.length(); j++){
                if(text1.charAt(i - 1) == text2.charAt(j - 1)) dp[i][j] = 1 + dp[i - 1][j - 1];
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[text1.length()][text2.length()];
    }

    public static void main(String[] args) {
        String str1 = "bl";
        String str2 = "yby";

        System.out.println(longestCommonSubsequenceRecursionTabulation(str1, str2));
    }
}

