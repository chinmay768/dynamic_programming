package stiver;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PrintingLongestCommonSubsequence {

    public static void printLIS(int[] nums){
        int[] dp = new int[nums.length];
        int[] backtrack = new int[nums.length];
        int maxLISLastIdx = -1;

        Arrays.fill(dp, 1);

        int maxLen = 0;
        for(int i = 0; i < nums.length; i++){
            backtrack[i] = i;
            for(int prevIdx = 0; prevIdx < i; prevIdx++){
                if(nums[i] > nums[prevIdx]){
                    dp[i] = Math.max(dp[i], dp[prevIdx] + 1);
                    backtrack[i] = prevIdx;
                }
            }

            if(dp[i] > maxLen){
                maxLen = dp[i];
                maxLISLastIdx = i;
            }
        }

        System.out.println(maxLen);

        List<Integer> list = new LinkedList<>();

        while (backtrack[maxLISLastIdx] != maxLISLastIdx){
            list.addFirst(nums[maxLISLastIdx]);
            maxLISLastIdx = backtrack[maxLISLastIdx];
        }
        list.addFirst(nums[maxLISLastIdx]);

        System.out.println(list);
    }

    public static void main(String[] args) {
        int[] arr = {10,9,2,5,3,7,101,18};

        printLIS(arr);

    }
}
