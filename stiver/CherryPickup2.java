package stiver;

public class CherryPickup2 {

    public static int cherryPickupRecursion(int[][] grid){
        return cherryPickupRecursionHelper(grid, 0, 0, grid[0].length - 1);
    }

    public static int cherryPickupRecursionHelper(int[][] grid, int i, int j1, int j2){
        if(j1 < 0 || j1 >= grid[i].length || j2 < 0 || j2 >= grid[i].length)
            return Integer.MIN_VALUE;

        if(i == grid.length - 1){
            if(j1 == j2) return grid[i][j1];
            else {
                return grid[i][j1] + grid[i][j2];
            }
        }

        int max = Integer.MIN_VALUE;

        for (int dj1 = -1; dj1 <= 1; dj1++){
            for(int dj2 = -1; dj2 <= 1; dj2++){
                int value = 0;
                if(j1 == j2) value = grid[i][j1];
                else value = grid[i][j1] + grid[i][j2];

                //It's not good to add safe check here because we have to do multiple checks instead do it at the base case
//                if(j1 + dj1 >= 0 && j1 + dj1 < grid[i].length && j2 + dj2 >= 0 && j2 + dj2 <= grid[i].length - 1)
                value += cherryPickupRecursionHelper(grid, i + 1, j1 + dj1, j2 + dj2);

                max = Math.max(max, value);
            }
        }

        return max;
    }

    public static int cherryPickupRecursionDP(int[][] grid){
        int[][][] dp = new int[grid.length][grid[0].length][grid[0].length];
        for(int i = 0; i < dp.length; i++){
            for(int j = 0; j < dp[0].length; j++){
                for(int k = 0; k < dp[0].length; k++){
                    dp[i][j][k] = -1;
                }
            }
        }
        return cherryPickupRecursionHelperDP(grid, 0, 0, grid[0].length - 1, dp);
    }

    public static int cherryPickupRecursionHelperDP(int[][] grid, int i, int j1, int j2, int[][][] dp){
        if(j1 < 0 || j1 >= grid[i].length || j2 < 0 || j2 >= grid[i].length)
            return Integer.MIN_VALUE;

        if(i == grid.length - 1){
            if(j1 == j2) return grid[i][j1];
            else {
                return grid[i][j1] + grid[i][j2];
            }
        }

        if(dp[i][j1][j2] != -1) return dp[i][j1][j2];

        int max = Integer.MIN_VALUE;

        for (int dj1 = -1; dj1 <= 1; dj1++){
            for(int dj2 = -1; dj2 <= 1; dj2++){
                int value = 0;
                if(j1 == j2) value = grid[i][j1];
                else value = grid[i][j1] + grid[i][j2];

                //It's not good to add safe check here because we have to do multiple checks instead do it at the base case
//                if(j1 + dj1 >= 0 && j1 + dj1 < grid[i].length && j2 + dj2 >= 0 && j2 + dj2 <= grid[i].length - 1)
                value += cherryPickupRecursionHelperDP(grid, i + 1, j1 + dj1, j2 + dj2, dp);

                max = Math.max(max, value);
            }
        }

        return dp[i][j1][j2] = max;
    }

    public static int cherryPickupTabulation(int[][] grid){
        int[][][] dp = new int[grid.length][grid[grid.length - 1].length][grid[grid.length - 1].length];

        for(int j1 = 0; j1 < grid[0].length; j1++){
            for(int j2 = 0; j2 < grid[0].length; j2++){
                if(j1 == j2) dp[grid.length - 1][j1][j2] = grid[grid.length - 1][j1];
                else dp[grid.length - 1][j1][j2] = grid[grid.length - 1][j1] + grid[grid.length - 1][j2];
            }
        }

        for(int row = grid.length - 2; row >= 0; row--){
            for (int j1 = 0; j1 < grid[0].length; j1++){
                for(int j2 = 0; j2 < grid[0].length; j2++){
                    int max = Integer.MIN_VALUE;
                    for(int dj1 = -1; dj1 <= 1; dj1++){
                        for(int dj2 = -1; dj2 <= 1; dj2++){
                             int value = 0;
                             if(j1 == j2) value = grid[row][j1];
                             else value = grid[row][j1] + grid[row][j2];

                             if(j1 + dj1 >= 0 && j1 + dj1 < grid[row].length && j2 + dj2 >= 0 && j2 + dj2 <= grid[row].length - 1)
                                value += dp[row + 1][j1 + dj1][j2 + dj2];
                             else
                                 value = Integer.MIN_VALUE;

                            max = Math.max(max, value);
                        }
                    }
                    dp[row][j1][j2] = max;
                }
            }
        }

        return dp[0][0][dp[0].length - 1];
    }

    public static void main(String[] args) {
        int[][] grid = {{3,1,1},{2,5,1},{1,5,5},{2,1,1}};

        System.out.println(cherryPickupTabulation(grid));
    }
}
