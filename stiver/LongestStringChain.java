package stiver;

import java.util.*;

class StringLengthComparator implements Comparator<String> {

    @Override
    public int compare(String s1, String s2) {
        return s1.length() - s2.length();
    }
}

public class LongestStringChain {

    public static int LongestStringChainLen(String[] words){
        // Since we have to return a sequence and not subsequence thus we don't have to pick elements in a specific order
        Arrays.sort(words, new StringLengthComparator());

        int[] dp = new int[words.length];
        Arrays.fill(dp, 1);

        int maxLen = 1;
        for(int i = 1; i < words.length; i++){
            for (int prevIdx = 0; prevIdx < i; prevIdx++){
                if(isOneCharExtra(words[i], words[prevIdx])){
                    dp[i] = Math.max(dp[i], dp[prevIdx] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    public static Boolean isOneCharExtra(String str1, String str2){
        if(str1.length() != str2.length() + 1) return false;

        int i = 0;
        int j = 0;

        while (i != str1.length() && j != str2.length()){
            if(str1.charAt(i) == str2.charAt(j)) {
                i++; j++;
            }else{
                i++;
            }
        }

        return  j == str2.length(); // Since at mismatch we are only moving i so if j is at the end that means it a match
    }

    public static void main(String[] args) {
        String[] strArr = {"xbc","pcxbcf","xb","cxbc","pcxbc"};

//        Arrays.sort(strArr, new StringLengthComparator());

        System.out.println(LongestStringChainLen(strArr));
    }
}
