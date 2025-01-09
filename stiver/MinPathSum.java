package stiver;

public class MinPathSum {

    public static int minPathSumRecursion(int[][] grid){
        return  minPathSumRecursionHelper(grid, grid.length - 1, grid[0].length - 1);
    }

    public static int minPathSumRecursionHelper(int[][] grid, int m, int n){
        if(m == 0 && n == 0) return grid[m][n];

        int right = Integer.MAX_VALUE;
        if(n > 0) right = minPathSumRecursionHelper(grid, m, n - 1) + grid[m][n];

        int bottom = Integer.MAX_VALUE;
        if(m > 0) bottom = minPathSumRecursionHelper(grid, m - 1, n) + grid[m][n];

        return Math.min(right, bottom);
    }

    public static int minPathSumRecursionDP(int[][] grid){
        int[][] dp = new int[grid.length][grid[0].length];
        for(int i = 0; i < dp.length; i++){
            for(int j = 0; j < dp[0].length; j++){
                dp[i][j] = -1;
            }
        }

        return  minPathSumRecursionHelperDP(grid, grid.length - 1, grid[0].length - 1, dp);
    }

    public static int minPathSumRecursionHelperDP(int[][] grid, int m, int n, int[][] dp){
        if(m == 0 && n == 0) return grid[m][n];

        if(dp[m][n] != -1) return dp[m][n];

        int right = Integer.MAX_VALUE;
        if(n > 0) right = minPathSumRecursionHelperDP(grid, m, n - 1, dp) + grid[m][n];

        int bottom = Integer.MAX_VALUE;
        if(m > 0) bottom = minPathSumRecursionHelperDP(grid, m - 1, n, dp) + grid[m][n];

        return dp[m][n] = Math.min(right, bottom);
    }

    public static int minPathSumTabulation(int[][] grid){
        int[][] dp = new int[grid.length][grid[0].length];

        for(int row = 0; row < grid.length; row++){
            for(int col = 0; col < grid[0].length; col++){
                if(row == 0 && col == 0) dp[row][col] = grid[row][col];
                else {
                    int right = Integer.MAX_VALUE;
                    if(col > 0) right = dp[row][col - 1] + grid[row][col];

                    int bottom = Integer.MAX_VALUE;
                    if(row > 0) bottom = dp[row - 1][col] + grid[row][col];

                    dp[row][col] = Math.min(right, bottom);
                }
            }
        }

        return dp[dp.length - 1][dp[0].length - 1];
    }

    public static int minPathSumTabulationOptimized(int[][] grid){
        int[] prev = new int[grid[0].length];

        for(int row = 0; row < grid.length; row++){
            int[] curr = new int[grid[0].length];
            for(int col = 0; col < grid[0].length; col++){
                if(row == 0 && col == 0) curr[col] = grid[row][col];
                else {
                    int right = Integer.MAX_VALUE;
                    if(col > 0) right = curr[col - 1] + grid[row][col];

                    int bottom = Integer.MAX_VALUE;
                    if(row > 0) bottom = prev[col] + grid[row][col];

                    curr[col] = Math.min(right, bottom);
                }
            }
            prev = curr;
        }

        return prev[prev.length - 1];
    }

    public static void main(String[] args) {
        int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};

        System.out.println(minPathSumTabulationOptimized(grid));
    }
}
