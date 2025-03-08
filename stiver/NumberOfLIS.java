package stiver;

import java.util.Arrays;

public class NumberOfLIS {

    public static int numberOfLIS(int[] nums){
        if(nums.length == 0) return 0; // This is to handle empty array case
        int[] len = new int[nums.length];
        int[] count = new int[nums.length];

        Arrays.fill(len, 1);
        Arrays.fill(count, 1);

        int maxLen = 1;
        for(int i = 1; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                if(nums[i] > nums[j]){
                    if(len[j] + 1 > len[i]){
                        len[i] = len[j] + 1;
                        count[i] = count[j];
                    }else if(len[j] + 1 == len[i]){
                        count[i] += count[j];
                    }
                }
            }
            maxLen = Math.max(maxLen, len[i]);
        }

        int noOfLIS = 0;
        for(int i = 0; i < nums.length; i++){
            if(len[i] == maxLen){
                noOfLIS += count[i];
            }
        }
        return noOfLIS;
    }

    public static void main(String[] args) {
        int[] arr = {2,2,2,2,2};

        System.out.println(numberOfLIS(arr));
    }
}
