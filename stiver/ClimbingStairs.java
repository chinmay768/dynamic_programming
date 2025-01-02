package stiver;

public class ClimbingStairs {

    public int climbStairs(int n) {
        if (n == 0 || n == 1)
            return 1;

        int left = climbStairs(n- 1);
        int right = climbStairs(n - 2);

        return left + right;
    }

    public static void main(String[] args) {

    }
}
