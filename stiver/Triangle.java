package stiver;
import java.util.*;

public class Triangle {

    public static int minPathSumRecursion(List<List<Integer>> triangle) {

        return minPathSumRecursionHelper(triangle, 0, 0);
    }

    public static int minPathSumRecursionHelper(List<List<Integer>> triangle, int m, int n){
        if(m == triangle.size() - 1) return triangle.get(m).get(n);

        int diagRight = triangle.get(m).get(n) + minPathSumRecursionHelper(triangle, m + 1, n + 1);
        int down = triangle.get(m).get(n) + minPathSumRecursionHelper(triangle, m + 1, n);

        return Math.min(diagRight, down);
    }

    public static int minPathSumRecursionDP(List<List<Integer>> triangle) {
        int[][] dp = new int[triangle.size()][triangle.size()];

        for(int i = 0; i < dp.length; i++){
            for(int j = 0; j < dp[0].length; j++){
                dp[i][j] = -1;
            }
        }

        return minPathSumRecursionHelperDP(triangle, 0, 0, dp);
    }

    public static int minPathSumRecursionHelperDP(List<List<Integer>> triangle, int m, int n, int[][] dp){
        if(m == triangle.size() - 1) return triangle.get(m).get(n);

        if(dp[m][n] != -1) return dp[m][n];

        int diagRight = triangle.get(m).get(n) + minPathSumRecursionHelperDP(triangle, m + 1, n + 1, dp);
        int down = triangle.get(m).get(n) + minPathSumRecursionHelperDP(triangle, m + 1, n, dp);

        return dp[m][n] = Math.min(diagRight, down);
    }

    public static int minPathSumTabulation(List<List<Integer>> triangle){
        int[][] dp = new int[triangle.size()][triangle.size()];

        for(int i = 0; i < dp.length; i++){
            dp[dp.length - 1][i] = triangle.get(dp.length - 1).get(i);
        }

        for(int i = dp.length - 2; i >= 0; i--){
            for(int j = i; j >= 0; j--){
                int bottom = triangle.get(i).get(j) + dp[i + 1][j];
                int diagRight = triangle.get(i).get(j) + dp[i + 1][j + 1];
                dp[i][j] = Math.min(bottom, diagRight);
            }
        }

        return dp[0][0];
    }

    public static int minPathSumTabulationOptimized(List<List<Integer>> triangle){
        int[] prev = new int[triangle.size()];

        for(int i = 0; i < triangle.size(); i++){
            prev[i] = triangle.get(triangle.size() - 1).get(i);
        }

        for(int i = triangle.size() - 2; i >= 0; i--){
            int[] curr = new int[i + 1]; // Need to take +1 because i is idx not length
            for(int j = i; j >= 0; j--){
                int bottom = triangle.get(i).get(j) + prev[j];
                int diagRight = triangle.get(i).get(j) + prev[j + 1];
                curr[j] = Math.min(bottom, diagRight);
            }
            prev = curr;
        }

        return prev[0];
    }

    public static void main(String[] args) {
        Integer[][] triangle = {{2},{3,4},{6,5,7},{4,1,8,3}};
        List<List<Integer>> list = new ArrayList<>();

        for (Integer[] array : triangle) {
            list.addAll(Collections.singleton(Arrays.asList(array)));
        }

        System.out.println(minPathSumTabulationOptimized(list));
    }
}
