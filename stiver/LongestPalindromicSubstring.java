package stiver;

import java.util.Arrays;

public class LongestPalindromicSubstring {

    public static String longestPalindromicSubstringRecursion(String s){
        int maxLen = 0;
        int startIdx = -1;
        for(int i = 0; i < s.length(); i++){
            for(int j = i; j < s.length(); j++){
                if(isPalindrome(s.substring(i, j + 1))){
                    int currLen = j - i + 1;
                    if(maxLen < currLen){
                        maxLen = currLen;
                        startIdx = i;
                    }
                }
            }
        }

        return s.substring(startIdx, startIdx + maxLen);
    }

    public static Boolean isPalindrome(String s){
        int i = 0; int j = s.length() - 1;

        if(i >= j) return true;

        if(s.charAt(i) == s.charAt(j)) return isPalindrome(s.substring(i + 1, j));
        else return false;
    }

    public static String longestPalindromicSubstringRecursionDP(String s){
        int[][] dp = new int[s.length()][s.length()];
        for(int i = 0; i < dp.length; i++){
            Arrays.fill(dp[i], -1);
        }

        int maxLen = 0;
        int startIdx = -1;
        for(int i = 0; i < s.length(); i++){
            for(int j = i; j < s.length(); j++){
                if(isPalindromeDP(s, i, j, dp)){
                    int currLen = j - i + 1;
                    if(maxLen < currLen){
                        maxLen = currLen;
                        startIdx = i;
                    }
                }
            }
        }

        return s.substring(startIdx, startIdx + maxLen);
    }

    public static Boolean isPalindromeDP(String s, int i , int j, int[][] dp){
        if(i >= j) return true;

        if(dp[i][j] != -1) return dp[i][j] == 1;

        if(s.charAt(i) == s.charAt(j)) {
            Boolean res = isPalindromeDP(s, i + 1, j - 1, dp);
            if(res) dp[i][j] = 1;
            else dp[i][j] = 0;
            return res;
        }
        else {
            dp[i][j] = 0;
            return dp[i][j] == 1;
        }
    }

    public static void main(String[] args) {
        String str = "asbabsat";

        System.out.println(longestPalindromicSubstringRecursionDP(str));
    }
}
