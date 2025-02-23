package stiver;
import  java.util.*;

public class LargestDivisibleSubset {

    // TC: O(nlogn + n^2 + n)
    //SC: O(n + n)

    public static List<Integer> largestDivisibleSubset(int[] arr){
        int[] dp = new int[arr.length];
        Arrays.fill(dp, 1);
        int maxLen = 1;

        Arrays.sort(arr);

        for(int i = 1; i < arr.length; i++){
            for(int prevIdx = 0; prevIdx < i; prevIdx++){
                if(arr[i] % arr[prevIdx] == 0){
                    dp[i] = Math.max(dp[i], dp[prevIdx] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        List<Integer> result = new LinkedList<>();

        int prevElm = Integer.MIN_VALUE;
        for(int j = dp.length - 1; j >= 0; j--){
            if(dp[j] == maxLen &&  (prevElm == Integer.MIN_VALUE || prevElm % arr[j] == 0)) {
                result.addFirst(arr[j]);
                maxLen--;
                prevElm = arr[j];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,4,8};

        System.out.println(largestDivisibleSubset(arr));
    }
}
