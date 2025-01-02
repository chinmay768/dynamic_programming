package stiver;
import java.util.*;

public class FrogJump {

    public static boolean canCross(int[] stones) {
        Map<Integer, HashSet<Integer>> map = new HashMap<>();

        for(int i = 0; i < stones.length; i++){
            map.put(stones[i], new HashSet<>());
        }

        map.get(0).add(1);
        for(int i = 0; i < stones.length; i++){
            int currentStone = stones[i];
            HashSet<Integer> jumps = map.get(currentStone);
            for(int jump : jumps){
                int newPos = currentStone + jump;
                if(newPos == stones[stones.length - 1]) return true;

                if(map.containsKey(newPos)){
                    if(jump - 1 > 0) map.get(newPos).add(jump - 1);
                    map.get(newPos).add(jump);
                    map.get(newPos).add(jump + 1);
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] stones = {0,1,3,5,6,8,12,17};
        System.out.println(canCross(stones));
    }
}
