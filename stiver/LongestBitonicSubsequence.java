package stiver;

import java.util.Arrays;

public class LongestBitonicSubsequence {

    public static int longestBitonicSubsequence(int[] arr){
        int[] dp1 = new int[arr.length];
        int[] dp2 = new int[arr.length];

        int maxBitonicLen = 1;


        for(int i = 1; i < arr.length; i++){
            dp1[i] = 1;
            for(int prevIdx = 0; prevIdx < i; prevIdx++){
                dp1[i] = Math.max(dp1[i],  dp1[prevIdx] + 1);
            }
        }


        for(int i = arr.length - 2; i <= 0; i--){
            dp2[i] = 1;
            for(int j = arr.length - 1; j > i; j--){
                dp2[i] = Math.max(dp2[i], dp2[j] + 1);
            }
        }

        for(int i = 0; i < arr.length; i++){
            maxBitonicLen = Math.max(maxBitonicLen, dp1[i] + dp2[i] - 1);
        }

        return maxBitonicLen;
    }



    public static void main(String[] args) {
        int[] arr = {1, 11, 2, 10, 4, 5, 2, 1};

        System.out.println(longestBitonicSubsequence(arr));
    }
}
