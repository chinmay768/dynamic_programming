package stiver;

public class ShortestCommonSupersequence {

    public static String shortestCommonSupersequence(String str1, String str2){
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];

        for(int i = 0; i < dp.length; i++){
            dp[i][0] = 0;
        }

        for(int j = 0; j< dp[0].length; j++){
            dp[0][j] = 0;
        }

        for(int i = 1; i <= str1.length(); i++){
            for(int j = 1; j <= str2.length(); j++){
                if(str1.charAt(i - 1) == str2.charAt(j - 1)) dp[i][j] = 1 + dp[i - 1][j - 1];
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        int i = dp.length - 1;
        int j = dp[0].length - 1;
        String res = "";
        while (i > 0 && j > 0){
            if(str1.charAt(i - 1) == str2.charAt(j - 1)) {
                res = str1.charAt(i - 1) + res;
                i--;
                j--;
            }
            else if(dp[i - 1][j] > dp[i][j - 1]){
                res = str1.charAt(i - 1) + res;
                i--;
            }else {
                res = str2.charAt(j - 1) + res;
                j--;
            }
        }

        while (i > 0) {
            res = str1.charAt(i - 1) + res;
            i--;
        }
        while (j > 0) {
            res = str2.charAt(j - 1) + res;
            j--;
        }

        return res;
    }

    public static void main(String[] args) {
        String str1 = "abac";
        String str2 = "cab";

        System.out.println(shortestCommonSupersequence(str1, str2));
    }
}
