package stiver;

import java.util.Arrays;

public class UniquePaths {

    public static int uniquePathsRecursion(int m, int n) {
        return  uniquePathsRecursionHelper(m - 1, n -1);
    }

    public static int uniquePathsRecursionHelper(int m, int n){
        if(m == 0 && n == 0) return 1;

        //Since we are following top-down approach so we will use opposite directions
        int up = 0;
        int left = 0;
        if(m > 0) up = uniquePathsRecursion(m - 1, n);
        if(n > 0) left = uniquePathsRecursion(m, n -1);

        return up + left;
    }

    public static int uniquePathsRecursionDP(int m, int n){
        int[][] dp = new int[m][n];
        for(int i = 0; i < dp.length; i++){
            for(int j = 0; j < dp[0].length; j++){
                dp[i][j]  = -1;
            }
        }
        return uniquePathsRecursionHelperDP(m - 1, n - 1, dp);
    }

    public static int uniquePathsRecursionHelperDP(int m , int n, int[][] dp){
        if(m == 0 && n == 0) return 1;

        if(dp[m][n] != -1) return dp[m][n];

        int up = 0;
        int left = 0;
        if(m > 0) up = uniquePathsRecursionHelperDP(m - 1, n, dp);
        if(n > 0) left = uniquePathsRecursionHelperDP(m, n -1, dp);

        return dp[m][n] = up + left;
    }

    public static int uniquePathsTabulation(int m, int n){
        int[][] dp = new int[m][n];

        dp[0][0] = 1;

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0 && j == 0) dp[0][0] = 1;
                else {
                    int bottom = 0;
                    int right = 0;
                    if(i > 0) bottom = dp[i - 1][j];
                    if(j > 0) right = dp[i][j - 1];
                    dp[i][j] = bottom + right;
                }
            }
        }

        return dp[m - 1][n - 1];
    }

    public static int uniquePathsTabulationSpaceOptimize(int m, int n){
        int[] prevRow = new int[n];
        Arrays.fill(prevRow, 0);

        for(int i = 0; i < m; i++){
            int[] currRow = new int[n];
            for(int j = 0; j < n; j++){
                if(i == 0 && j == 0) currRow[j] = 1;
                else{
                    int bottom = 0;
                    int right = 0;
                    if(i > 0) bottom = prevRow[j];
                    if(j > 0) right = currRow[j - 1];
                    currRow[j] = bottom + right;
                }
            }
            prevRow = currRow;
        }

        return prevRow[n - 1];
    }

    public static void main(String[] args) {
        // Here m x n is the size of the matrix
        int m = 3;
        int n = 7;

        System.out.println(uniquePathsTabulationSpaceOptimize(m, n));
    }
}
