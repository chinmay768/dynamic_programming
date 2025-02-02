package stiver;

public class MinimumInsertionsToMakeStringPalindrome {
    public static int longestCommonSubsequenceTabulation(String str1, String str2){
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];

        for(int i = 0; i < dp.length; i++){
            dp[i][0] = 0;
        }

        for (int j = 0; j < dp[0].length; j++){
            dp[0][j] = 0;
        }

        for(int i = 1; i <= str1.length(); i++){
            for(int j = 1; j <= str2.length(); j++){
                if(str1.charAt(i - 1) == str2.charAt(j - 1)) dp[i][j] = 1 + dp[i - 1][j - 1];
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[str1.length()][str2.length()];
    }

    public static String revString(String str){
        StringBuilder res = new StringBuilder();
        res.append(str);
        return res.reverse().toString();
    }

    public static void main(String[] args) {
        //The ans for minimum no. of deletions to make a string palindrome will also be same because if we just remove the
        // characters which are not a part of the lps(longest palindromic subsequence) we'll get the ans...which is same as n-len(lps)
        String str1 = "leetcode";
        String str2 = revString(str1);

        int lcs = longestCommonSubsequenceTabulation(str1, str2);
        System.out.println(str1.length() - lcs);
    }
}
