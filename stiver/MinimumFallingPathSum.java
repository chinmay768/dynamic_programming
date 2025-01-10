package stiver;

public class MinimumFallingPathSum {

    public static int minFallingPathSumRecursion(int[][] matrix) {
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < matrix[0].length; i++){
            min = Math.min(min, minFallingPathSumRecursionHelper(matrix, matrix.length - 1, i));
        }

        return min;
    }

    public static int minFallingPathSumRecursionHelper(int[][] matrix, int m, int n){
        if(m == 0) return matrix[m][n];

        int leftDiag = Integer.MAX_VALUE;
        if(n > 0) leftDiag = matrix[m][n] + minFallingPathSumRecursionHelper(matrix, m - 1, n - 1);

        int up = matrix[m][n] + minFallingPathSumRecursionHelper(matrix, m - 1, n);

        int rightDiag = Integer.MAX_VALUE;
        if(n < matrix[m].length - 1) rightDiag = matrix[m][n] + minFallingPathSumRecursionHelper(matrix, m - 1, n + 1);

        return Math.min(leftDiag, Math.min(up, rightDiag));
    }

    public static int minFallingPathSumRecursionDP(int[][] matrix) {
        int[][] dp = new int[matrix.length][matrix[0].length];
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                dp[i][j] = -1;
            }
        }

        int min = Integer.MAX_VALUE;
        for(int i = 0; i < matrix[0].length; i++){
            min = Math.min(min, minFallingPathSumRecursionHelperDP(matrix, matrix.length - 1, i, dp));
        }

        return min;
    }

    public static int minFallingPathSumRecursionHelperDP(int[][] matrix, int m, int n, int[][] dp){
        if(m == 0) return matrix[m][n];

        if(dp[m][n] != -1) return dp[m][n];

        int leftDiag = Integer.MAX_VALUE;
        if(n > 0) leftDiag = matrix[m][n] + minFallingPathSumRecursionHelperDP(matrix, m - 1, n - 1, dp);

        int up = matrix[m][n] + minFallingPathSumRecursionHelperDP(matrix, m - 1, n, dp);

        int rightDiag = Integer.MAX_VALUE;
        if(n < matrix[m].length - 1) rightDiag = matrix[m][n] + minFallingPathSumRecursionHelperDP(matrix, m - 1, n + 1, dp);

        return dp[m][n] = Math.min(leftDiag, Math.min(up, rightDiag));
    }

    public static int minFallingPathSumTabulation(int[][] matrix){
        int[][] dp = new int[matrix.length][matrix[0].length];

        for(int i = 0; i < matrix[0].length; i++){
            dp[0][i] = matrix[0][i];
        }

        for(int i = 1; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                int leftDiag = Integer.MAX_VALUE;
                if(j > 0) leftDiag = matrix[i][j] + dp[i - 1][j - 1];

                int up = matrix[i][j] + dp[i - 1][j];

                int rightDiag = Integer.MAX_VALUE;
                if(j < matrix[i].length - 1) rightDiag = matrix[i][j] + dp[i - 1][j + 1];

                dp[i][j] = Math.min(leftDiag, Math.min(up, rightDiag));
            }
        }

        int res = Integer.MAX_VALUE;
        for(int i = 0; i < matrix[matrix.length - 1].length; i++){
            res = Math.min(res, dp[dp.length - 1][i]);
        }

        return res;
    }

    public static int minFallingPathSumTabulationOptimized(int[][] matrix){
        int[] prev = new int[matrix.length];

        for(int i = 0; i < matrix[0].length; i++){
            prev[i] = matrix[0][i];
        }

        for(int i = 1; i < matrix.length; i++){
            int[] curr = new int[matrix[i].length];
            for(int j = 0; j < matrix[i].length; j++){
                int leftDiag = Integer.MAX_VALUE;
                if(j > 0) leftDiag = matrix[i][j] + prev[j - 1];

                int up = matrix[i][j] + prev[j];

                int rightDiag = Integer.MAX_VALUE;
                if(j < matrix[i].length - 1) rightDiag = matrix[i][j] + prev[j + 1];

                curr[j] = Math.min(leftDiag, Math.min(up, rightDiag));
            }
            prev = curr;
        }

        int res = Integer.MAX_VALUE;
        for(int i = 0; i < matrix[matrix.length - 1].length; i++){
            res = Math.min(res, prev[i]);
        }

        return res;
    }

    public static void main(String[] args) {
        int[][] matrix = {{2,1,3},{6,5,4},{7,8,9}};

        System.out.println(minFallingPathSumTabulationOptimized(matrix));
    }
}
