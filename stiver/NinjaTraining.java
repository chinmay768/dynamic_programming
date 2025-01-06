package stiver;

import java.util.Arrays;

public class NinjaTraining {

    public static int maxPointsRecursion(int day, int last, int[][] points){
        if(day == 0){
            int maxPoints = 0;
            for(int i = 0; i < points[0].length; i++){
                if(i != last){
                    maxPoints = Math.max(maxPoints, points[0][i]);
                }
            }
            return maxPoints;
        }

        int maxPoints = 0;

        for(int task = 0; task < points[0].length; task++){
            int currMax = 0;
            if(task != last){
                currMax = Math.max(currMax, maxPointsRecursion(day - 1, task, points) + points[day][task]);
            }
            maxPoints = Math.max(maxPoints, currMax);
        }
        return maxPoints;
    }

    public static int maxPointsRecursionDP(int day, int last, int[][] points, int[][] dp){
        if(day == 0){
            int maxPoints = 0;
            for(int i = 0; i < points[0].length; i++){
                if(i != last){
                    maxPoints = Math.max(maxPoints, points[0][i]);
                }
            }
            return maxPoints;
        }

        if(dp[day][last] != -1) return dp[day][last];

        int maxPoints = 0;

        for(int task = 0; task < points[0].length; task++){
            int currMax = 0;
            if(task != last){
                currMax = Math.max(currMax, maxPointsRecursion(day - 1, task, points) + points[day][task]);
            }
            maxPoints = Math.max(maxPoints, currMax);
        }
        return dp[day][last] = maxPoints;
    }

    public static int maxPointsTabulation(int n, int l, int[][] points){
        int[][] dp = new int[points.length][points[0].length + 1];

        dp[0][0] = Math.max(points[0][1], points[0][2]);
        dp[0][1] = Math.max(points[0][0], points[0][2]);
        dp[0][2] = Math.max(points[0][0], points[0][1]);
        dp[0][3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

        for(int day = 1;  day < n; day++){
            for(int last = 0; last < points[0].length + 1; last++){
                int currMax = 0;
                for(int task = 0; task < points[0].length; task++){
                    if(task != last){
                        currMax = Math.max(currMax, dp[day - 1][task] + points[day][task]);
                    }
                }
                dp[day][last] = currMax;
            }
        }

        return dp[n - 1][3];
    }

    public static void main(String[] args) {
        int[][] points = {{10, 40, 70}, {20, 50, 80}, {30, 60, 90}};

        int[][] dp = new int[points.length][points[0].length + 1];
        for(int i= 0 ;i < dp.length; i++){
            for(int j = 0; j < dp[0].length; j++){
                dp[i][j] = -1;
            }
        }

        System.out.println(maxPointsRecursionDP(2, 3, points, dp));
        System.out.println(maxPointsTabulation(3, 3, points));
    }
}
