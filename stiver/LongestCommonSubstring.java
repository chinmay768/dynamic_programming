package stiver;

import java.util.Arrays;

public class LongestCommonSubstring {

    public static int longestCommonSubstringRecursion(String str1, String str2){
        return longestCommonSubstringRecursionHelper(str1, str2, str1.length() - 1, str2.length() - 1 , 0);
    }

    public static int longestCommonSubstringRecursionHelper(String str1, String str2, int i, int j, int lenSoFar){
        if(i < 0 || j < 0) return lenSoFar;

        int matchCount = lenSoFar;
        if(str1.charAt(i) == str2.charAt(j)){
            matchCount = longestCommonSubstringRecursionHelper(str1, str2, i - 1, j -1, 1 + lenSoFar);
        }

        // Reset the counter because you've encountered a mismatch
        int exclI = longestCommonSubstringRecursionHelper(str1, str2, i - 1, j, 0);
        int excclJ = longestCommonSubstringRecursionHelper(str1, str2, i, j - 1, 0);

        return Math.max(matchCount, Math.max(exclI, excclJ));
    }

    public static int longestCommonSubstringTabulation(String str1, String str2){
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];

        for(int i = 0; i < dp[0].length; i++){
            dp[0][i] = 0;
        }
        for(int j = 0; j < dp.length; j++){
            dp[j][0] = 0;
        }


        int res = 0; // We are using this variable to store max res because we are resetting in case of mismatch
        for(int i  = 1; i < dp.length; i++){
            for(int j = 1; j < dp[0].length; j++){
                if(str1.charAt(i - 1) == str2.charAt(j - 1)){
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    res = Math.max(res, dp[i][j]);
                }else {
                    dp[i][j] = 0;
                }
            }
        }

        return res;
    }


    public static void main(String[] args) {
//        String str1 = "pbannychinmayweas";
//        String str2 = "pcanchinmaydsde";
//
        String str1 = "tsi";
        String str2 = "sit";

        System.out.println(longestCommonSubstringRecursion(str1, str2));
    }
}
