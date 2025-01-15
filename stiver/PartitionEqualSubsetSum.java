package stiver;
import java.util.*;

public class PartitionEqualSubsetSum {
    public static boolean canPartition(int[] nums) {
        int sum  = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }

        if(sum % 2 != 0) return false;

        return subsetSumEqualsToTargetRecursionTabulationSpaceOptimized(nums, sum/2);
    }

    public static Boolean subsetSumEqualsToTargetRecursionTabulationSpaceOptimized(int[] arr, int target){
        Boolean[] prev = new Boolean[target + 1];
        Arrays.fill(prev, false);

        prev[0] = true;

        if(target >= arr[0]) prev[arr[0]] = true; // If idx is 0 then only the element present at idx 0 is marked as true

        for(int i = 1; i < arr.length; i++){
            Boolean[] curr = new Boolean[target + 1];
            curr[0] = true;
            for(int tgt = 1; tgt <= target; tgt++){
                boolean excl = prev[tgt];

                boolean incl = false;
                if(arr[i] <= tgt) incl = prev[tgt - arr[i]];

                curr[tgt] = excl || incl;
            }
            prev = curr;
        }
        return prev[target];
    }

    public static void main(String[] args) {
        int[] nums = {1,5,11,5};

        System.out.println(canPartition(nums));
    }
}
