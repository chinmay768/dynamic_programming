package stiver;

public class EvaluateExpressionToTrue {


    public static int evaluateExpressionRecursion(String exp){
        return evaluateExpressionRecursionHelper(exp, 0, exp.length() - 1, true);
    }

    public static int evaluateExpressionRecursionHelper(String exp, int i, int j, boolean isTrue){
        if(i > j) return 0;

        if(i == j){
            if(isTrue) return exp.charAt(i) == 'T'? 1 : 0;
            else return exp.charAt(i) == 'F'? 1 : 0;
        }

        int ways = 0;
        for(int k = i + 1; k <= j - 1; k++){
            int lt = evaluateExpressionRecursionHelper(exp, i, k - 1, true);
            int lf = evaluateExpressionRecursionHelper(exp, i, k - 1, false);
            int rt = evaluateExpressionRecursionHelper(exp, k + 1, j, true);
            int rf = evaluateExpressionRecursionHelper(exp, k + 1, j, false);

            // Note in case of big result you will have to mod after every multiplication (lt * rf) % mod + ( lf * rt) % mod
            if(exp.charAt(i) == '&'){
                if(isTrue) ways += lt * rt;
                else ways += lf * rt + lt * rf + lf * rf;
            }else if(exp.charAt(i) == '|'){
                if(isTrue) ways += lt * rt + lf * rt + lt * rf;
                else ways += lf * rf;
            }else {
                if(isTrue) ways += lt * rf + lf * rt;
                else ways += lt * rt + lf * rf;
            }
        }

        return ways;
    }

    public static void main(String[] args) {
        String exp = "F|T^F";
        System.out.println(evaluateExpressionRecursion(exp));
    }
}
