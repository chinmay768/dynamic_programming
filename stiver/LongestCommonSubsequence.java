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

//    public static int longestCommonSubsequenceRecursionTabulation(String text1, String text2){
//
//    }

    public static void main(String[] args) {
        String str1 = "abcde";
        String str2 = "ace";

        System.out.println(longestCommonSubsequenceRecursionDP(str1, str2));
    }
}
