package stiver;

import java.util.Arrays;

public class UniquePaths2 {

    public static int uniquePathsRecursion(int[][] obstacleGrid){
        return uniquePathsRecursionHelper(obstacleGrid, obstacleGrid.length - 1, obstacleGrid[0].length - 1);
    }

    public static int uniquePathsRecursionHelper(int[][] obstacleGrid, int m, int n){
        if(obstacleGrid[m][n] == 1) return 0;

        if(m == 0 && n == 0) return 1;

        int left = 0;
        if(n > 0) left = uniquePathsRecursionHelper(obstacleGrid, m, n - 1);

        int top = 0;
        if(m > 0) top = uniquePathsRecursionHelper(obstacleGrid, m - 1, n);

        return left + top;
    }

    public static int uniquePathsRecursionDP(int[][] obstacleGrid){
        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
        for(int i  = 0; i < dp.length; i++){
            for(int j = 0; j < dp[0].length; j++){
                dp[i][j] = -1;
            }
        }
        return uniquePathsRecursionHelperDP(obstacleGrid, obstacleGrid.length - 1, obstacleGrid[0].length - 1, dp);
    }

    public static int uniquePathsRecursionHelperDP(int[][] obstacleGrid, int m, int n, int[][] dp){
        if(obstacleGrid[m][n] == 1) return 0;

        if(m == 0 && n == 0) return 1;

        if(dp[m][n] != -1) return dp[m][n];

        int left = 0;
        if(n > 0) left = uniquePathsRecursionHelperDP(obstacleGrid, m, n - 1, dp);

        int top = 0;
        if(m > 0) top = uniquePathsRecursionHelperDP(obstacleGrid, m - 1, n, dp);

        return dp[m][n] = left + top;
    }

    public static int uniquePathsTabulation(int[][] obstacleGrid){
        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];

        for(int row = 0; row < dp.length; row++){
            for(int col = 0; col < dp[0].length; col++){
                if(obstacleGrid[row][col] == 1) dp[row][col] = 0;
                else if(row == 0 && col == 0) dp[row][col] = 1;
                else {
                    int right = 0;
                    if(col > 0) right = dp[row][col - 1];

                    int bottom = 0;
                    if(row > 0) bottom = dp[row - 1][col];

                    dp[row][col] = right + bottom;
                }
            }
        }

        return  dp[dp.length - 1][dp[0].length - 1];
    }

    public static int uniquePathsTabulationOptimized(int[][] obstacleGrid){
        int[] prev = new int[obstacleGrid[0].length];
        Arrays.fill(prev, 0);

        for(int row = 0; row < obstacleGrid.length; row++){
            int[] curr = new int[obstacleGrid[0].length];
            for(int col = 0; col < obstacleGrid[0].length; col++){
                if(obstacleGrid[row][col] == 1) curr[col] = 0;
                else if(row == 0 && col == 0) curr[col] = 1;
                else {
                    int right = 0;
                    if(col > 0) right = curr[col - 1];

                    int bottom = 0;
                    if(row > 0) bottom = prev[col];

                    curr[col] = right + bottom;
                }
            }
            prev = curr;
        }

        return  prev[prev.length - 1];
    }

    public static void main(String[] args) {
        int[][] grid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};

        System.out.println(uniquePathsTabulationOptimized(grid));
    }
}
