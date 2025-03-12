package stiver;

import java.util.Arrays;

public class PalindromePartitioning2 {

    public static Boolean isPalindrome(String str){
        int i = 0;
        int j = str.length() - 1;

        while (i < j){
            if(str.charAt(i) != str.charAt(j)) return false;
            i++;
            j--;
        }

        return true;
    }

    public static int palindromePartitioningRecursion(String str){
        if(str.length() ==  1) return 0;

        int minPartitions = Integer.MAX_VALUE;
        for(int i = 0; i < str.length(); i++){
            if(isPalindrome(str.substring(0, i + 1))){
                int currCost = 1 + palindromePartitioningRecursion(str.substring(i + 1));
                minPartitions = Math.min(minPartitions, currCost);
            }
        }

        return minPartitions;
    }

    public static int palindromePartitioningRecursionDP(String str){
        int[] dp = new int[str.length()];
        Arrays.fill(dp, -1);

        return palindromePartitioningRecursionHelperDP(str, 0, dp);
    }



    public static int palindromePartitioningRecursionHelperDP(String str, int i, int[] dp){
        if(i == str.length()) return 0;

        if(dp[i] != -1) return dp[i];

        int minPartitions = Integer.MAX_VALUE;
        for(int j = i; j < str.length(); j++){
            if(isPalindrome(str.substring(i, j + 1))){
                int currCost = 1 + palindromePartitioningRecursionHelperDP(str,j + 1, dp);
                minPartitions = Math.min(minPartitions, currCost);
            }
        }

        return dp[i] = minPartitions;
    }

//    public static int palindromePartitioningTabulation(String str){
//
//    }


    public static void main(String[] args) {
        String str = "aab";


        System.out.println(palindromePartitioningRecursionDP(str) - 1);
    }
}
