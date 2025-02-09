package stiver;

import java.util.Arrays;

public class EditDistance {

    //TC: Exponential O(3^n)  SC: O(n + m)
    public static int editDistanceRecursion(String str1, String str2){
        return editDistanceRecursionHelper(str1, str2, str1.length() - 1, str2.length() - 1);
    }

    public static int editDistanceRecursionHelper(String str1, String str2, int i, int j){
        if(i < 0) return j + 1;
        if(j < 0) return i + 1;

        if(str1.charAt(i) == str2.charAt(j))
            return editDistanceRecursionHelper(str1, str2, i - 1, j - 1);

        return Math.min(
                1 + editDistanceRecursionHelper(str1, str2, i, j - 1), // insert
                Math.min(
                        1 + editDistanceRecursionHelper(str1, str2, i - 1, j), // delete
                        1 + editDistanceRecursionHelper(str1, str2, i - 1, j - 1) // replace
                )
        );
    }

    // TC: O(n * m)   SC: O(n * m) + O(n + m)
    public static int editDistanceRecursionDP(String str1, String str2){
        int[][] dp = new int[str1.length()][str2.length()];
        for(int i = 0; i < dp.length; i++){
            Arrays.fill(dp[i], -1);
        }
        return editDistanceRecursionHelperDP(str1, str2, str1.length() - 1, str2.length() - 1, dp);
    }

    public static int editDistanceRecursionHelperDP(String str1, String str2, int i, int j, int[][] dp){
        if(i < 0) return j + 1;
        if(j < 0) return i + 1;

        if(dp[i][j] != -1) return dp[i][j];

        if(str1.charAt(i) == str2.charAt(j))
            return dp[i][j] = editDistanceRecursionHelperDP(str1, str2, i - 1, j - 1, dp);

        return dp[i][j] = Math.min(
                1 + editDistanceRecursionHelperDP(str1, str2, i, j - 1, dp), // insert
                Math.min(
                        1 + editDistanceRecursionHelperDP(str1, str2, i - 1, j, dp), // delete
                        1 + editDistanceRecursionHelperDP(str1, str2, i - 1, j - 1, dp) // replace
                )
        );
    }

    public static int editDistanceRecursionTabulation(String str1, String str2){
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];

        for(int j = 0; j < dp[0].length; j++)
            dp[0][j] = j; // if(i < 0) return j + 1; but since it is 1 based indexing we are returning j

        for(int i = 0; i < dp.length; i++){
            dp[i][0] = i; // if(j < 0) return i + 1; but since it is 1 based indexing we are returning i
        }

        for(int i = 1; i < dp.length; i++){
            for(int j = 1; j < dp[0].length; j++){
                if(str1.charAt(i - 1) == str2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else {
                    dp[i][j] = Math.min(
                            1 + dp[i][j - 1],
                            Math.min(1 + dp[i - 1][j],
                                1 + dp[i - 1][j - 1])
                    );
                }
            }
        }

        return dp[str1.length()][str2.length()];
        // Event though we use 1 based indexing the result stored in particular index remains same as 0 based indexing
    }

    public static void main(String[] args) {
        String str1 = "intention";
        String str2 = "execution";

        System.out.println(editDistanceRecursionTabulation(str1, str2));
    }
}
