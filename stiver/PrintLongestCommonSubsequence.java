package stiver;

public class PrintLongestCommonSubsequence {

    public static String printLCSTabulation(String str1, String str2){
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 0; i < dp.length; i++)
            dp[i][0] = 0;

        for(int i = 0; i < dp[0].length; i++)
            dp[0][i] = 0;

        for(int i = 1; i < dp.length; i++){
            for(int j = 1; j < dp[0].length; j++){
                if(str1.charAt(i - 1) == str2.charAt(j - 1)) dp[i][j] = 1 + dp[i - 1][j - 1];
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        int res =  dp[dp.length - 1][dp[0].length - 1];

        String lcs = "";
        int i = str1.length();
        int j = str2.length();
        while (i > 0 && j > 0){
            if(str1.charAt(i - 1) == str2.charAt(j - 1)){
                lcs = str1.charAt(i - 1) + lcs;
                i--; j--;
            }else if(dp[i - 1][j] > dp[i][j - 1]){
                i--;
            }else {
                j--;
            }
        }

        return lcs;
    }

    public static void main(String[] args) {
        String str1 = "abcde";
        String str2 = "bdgek";

        System.out.println(printLCSTabulation(str1, str2));
    }
}
