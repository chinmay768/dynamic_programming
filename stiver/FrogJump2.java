package stiver;

public class FrogJump2 {

    public static int minCost(int[] stones){
        int n = stones.length;
        if(n == 2) return stones[n - 1];
        int maxCost = 0;

        for(int i = 2; i < n; i++){
            maxCost = Math.max(maxCost, stones[i] - stones[i - 2]);
        }
        return maxCost;
    }

    public static void main(String[] args) {
        int[] stones = {0,2,5,6,7};
        System.out.println(minCost(stones));
    }
}
