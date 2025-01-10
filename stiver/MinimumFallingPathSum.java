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

    public static void main(String[] args) {
        int[][] matrix = {{2,1,3},{6,5,4},{7,8,9}};

        System.out.println(minFallingPathSumRecursion(matrix));
    }
}
