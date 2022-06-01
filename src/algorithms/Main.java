package algorithms;// 本题为考试多行输入输出规范示例，无需提交，不计分。

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        List<Integer> result = get(new int[]{1, 3, 10, 5}, new int[]{3, 0, 5, 3}, 0);
        for (int i : result) {
            System.out.print(" " + i);
        }
    }

    private static List<Integer> get(int[] pids, int[] ppids, int kill) {
        if(kill == 0){
            List<Integer> result = new ArrayList<>();
            for(int i : pids){
                result.add(i);
            }
            return result;
        }
        if (!contain(pids, kill)) {
            return new ArrayList<>();
        }
        Set<Integer> res = new HashSet<>();
        res.add(kill);
        while (true){
            boolean flag = false;
            for (int i = 0; i < pids.length; i++) {
                if (res.contains(ppids[i])) {
                    flag = true;
                    ppids[i] = -1;
                    res.add(pids[i]);
                }
            }
            if(!flag){
                break;
            }
        }
        return new ArrayList<>(res);
    }

    private static boolean contain(int[] pids, int kill){
        if(pids == null){
            return false;
        }
        for(int i : pids){
            if(kill == i){
                return true;
            }
        }
        return false;
    }
}