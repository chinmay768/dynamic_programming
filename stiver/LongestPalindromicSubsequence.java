package stiver;

public class LongestPalindromicSubsequence {

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

    public static String revString(String str){
        StringBuilder res = new StringBuilder();
        res.append(str);
        return res.reverse().toString();
    }

    public static void main(String[] args) {
        String str1 = "bbbab";
        String str2 = revString(str1);

        System.out.println(longestCommonSubsequenceRecursionTabulation(str1, str2));
    }

}
