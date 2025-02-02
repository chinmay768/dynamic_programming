package stiver;

public class MinOperationsToConvertStringAtoB {
    public static int longestCommonSubsequenceTabulationOrMinDistance(String str1, String str2){
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

    public static void main(String[] args) {
        String str1 = "sea";
        String str2 = "eat";

        int lcs = longestCommonSubsequenceTabulationOrMinDistance(str1, str2);
        int res = str1.length() - lcs + str2.length() - lcs;
        System.out.println(res);
    }
}
