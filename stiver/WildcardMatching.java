package stiver;

import java.util.Arrays;

public class WildcardMatching {

    // TC: Exponential SC: O(n + m)
    public static boolean wildcardMatchingRecursion(String str1, String str2){
        return wildcardMatchingRecursionHelper(str1, str2, str1.length() - 1, str2.length() - 1);
    }

    public static boolean wildcardMatchingRecursionHelper (String str1, String str2, int i, int j){
        if(i < 0 && j < 0) return true;
        if(i < 0 && j >= 0) return false;

        if(j < 0 && i >= 0){
            for(int ind = 0; ind <= i; ind++){
                if(str1.charAt(ind) != '*') return false;
            }
            return true;
        }

        if(str1.charAt(i) == str2.charAt(j) || str1.charAt(i) == '?'){
            return wildcardMatchingRecursionHelper(str1, str2, i - 1, j - 1);
        }

        if(str1.charAt(i) == '*'){
            return wildcardMatchingRecursionHelper(str1, str2, i - 1, j) ||
                    wildcardMatchingRecursionHelper(str1, str2, i, j - 1);
        }

        return false;
    }

    //TC: O(n * m)  SC: O(n * m) + (n + m)
    public static boolean wildcardMatchingRecursionDP(String str1, String str2){
        int[][] dp = new int[str1.length()][str2.length()];
        for(int i = 0; i < dp.length; i++){
            Arrays.fill(dp[i], -1);
        }
        return wildcardMatchingRecursionHelperDP(str1, str2, str1.length() - 1, str2.length() - 1 , dp);
    }

    public static boolean wildcardMatchingRecursionHelperDP(String str1, String str2, int i, int j, int[][] dp){
        if(i < 0 && j < 0) return true;
        if(i < 0 && j >= 0) return false;

        if(j < 0 && i >= 0){
            for(int ind = 0; ind <= i; ind++){
                if(str1.charAt(ind) != '*') return false;
            }
            return true;
        }

        if(dp[i][j] != -1) return dp[i][j] == 1;

        if(str1.charAt(i) == str2.charAt(j) || str1.charAt(i) == '?'){
            boolean res = wildcardMatchingRecursionHelperDP(str1, str2, i - 1, j - 1, dp);
            dp[i][j] = res? 1 : 0;
            return res;
        }

        if(str1.charAt(i) == '*'){
            boolean res1 = wildcardMatchingRecursionHelperDP(str1, str2, i - 1, j, dp);
            boolean res2 = wildcardMatchingRecursionHelperDP(str1, str2, i, j - 1, dp);
            dp[i][j] = (res1 || res2)? 1 : 0;
            return res1 || res2;
        }

        dp[i][j] = 0;
        return false;
    }

    public static boolean wildcardMatchingTabulation(String str1, String str2){
        boolean dp[][] = new boolean[str1.length() + 1][str2.length() + 1];

        dp[0][0] = true;
        for(int j = 1; j <= str2.length(); j++)
            dp[0][j] = false;

        for (int i = 1; i <= str1.length(); i++){
            boolean flag = true;
            for(int ind = 1; ind <= i; ind++){
                if(str1.charAt(ind - 1) != '*') {
                    flag = false;
                    break;
                }
            }
            dp[i][0] = flag;
        }

        for(int i = 1; i <= str1.length(); i++){
            for(int j = 1; j <= str2.length(); j++){
                if(str1.charAt(i - 1) == str2.charAt(j - 1) ||
                str1.charAt(i - 1) == '?'){
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else if(str1.charAt(i - 1) == '*'){
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }else {
                    dp[i][j] = false;
                }
            }
        }

        return dp[str1.length()][str2.length()];
    }

    public static void main(String[] args) {
        String str1 = "?a";
        String str2 = "aa";

        System.out.println(wildcardMatchingTabulation(str1, str2));
    }
}
