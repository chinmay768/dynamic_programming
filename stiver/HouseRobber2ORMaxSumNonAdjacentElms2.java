package stiver;
import java.util.*;

public class HouseRobber2ORMaxSumNonAdjacentElms2 {

    public static int robHelper(int[] nums){
        int prev = nums[0];
        int prev2 = -1;
        int curr = prev;

        for(int i = 1; i < nums.length; i++){
            int incl = nums[i];
            if(prev2 != -1) incl += prev2;

            int excl = prev;

            curr = Math.max(incl, excl);
            prev2 = prev;
            prev = curr;
        }
        return prev;
    }

    public static int rob(int[] nums) {
        if(nums.length == 1) return nums[0];
        int[] inclFirst = Arrays.copyOfRange(nums, 0, nums.length-1);
        int[] inclLast = Arrays.copyOfRange(nums, 1, nums.length);
        return Math.max(robHelper(inclFirst), robHelper(inclLast));
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        System.out.println(rob(nums));
    }
}
