package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RangeList {

    private final List<int[]> ranges = new ArrayList<>();

    public void add(int[] range){
        if(range[0] ==range[1]){
            return;
        }
        int index = get(range);
        if(index != -1){
            return;
        }
        ranges.add(range);
        merge();
    }

    public void remove(int[] range){
        if(range[0] ==range[1]){
            return;
        }
        int index = get(range);
        if(index != -1){
            ranges.remove(index);
            return;
        }
        List<int[]> list = new ArrayList<>();
        int begin = range[0];
        int end = range[1];
        for(int i = 0; i < ranges.size(); i++){
            int[] cur = ranges.get(i);
            if(cur[1] <= begin || cur[0] >= end){
                list.add(cur);
                continue;
            } else if(begin <= cur[0] && cur[1] <= end){
                continue;
            } else{
                if(begin <= cur[0] && end <= cur[1]){
                    list.add(new int[]{end, cur[1]});
                }else if(begin >= cur[0] && end <= cur[1]){
                    list.add(new int[]{cur[0], begin});
                    list.add(new int[]{end, cur[1]});
                }else {
                    list.add(new int[]{cur[0], begin});
                }
            }
        }
        copy(list);
    }

    public String print(){
        List<int[]> list = this.ranges;
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < list.size(); i++){
            int[] cur = list.get(i);
            if(i != 0){
                res.append(" ");
            }
            res.append("[").append(cur[0]).append(", ").append(cur[1]).append(")");
        }
        return res.toString();
    }

    private void merge(){
        if(ranges.size() == 0){
            return;
        }
        ranges.sort((o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
        List<int[]> list = new ArrayList<>();
        int begin = ranges.get(0)[0];
        int end = ranges.get(0)[1];
        for(int i = 1; i < ranges.size(); i++){
            int[] cur = ranges.get(i);
            if(cur[0] > end){
                list.add(new int[]{begin, end});
                begin = cur[0];
                end = cur[1];
            } else {
                begin = Math.min(begin, cur[0]);
                end = Math.max(end, cur[1]);
            }
        }
        list.add(new int[]{begin, end});
        copy(list);
    }


    private void copy(List<int[]> list){
        ranges.clear();
        for(int[] cur : list){
            if(cur == null || cur[0] == cur[1]){
                continue;
            }
            ranges.add(cur);
        }
    }

    private int get(int[] range){
        if(ranges.size() == 0){
            return  -1;
        }
        for(int i = 0; i < ranges.size(); i++){
            int[] cur = ranges.get(i);
            if(Arrays.equals(cur, range)){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        RangeList r1 = new RangeList();
        r1.add(new int[]{1, 5});
        System.out.println(r1.print());
        r1.add(new int[]{10, 20});
        System.out.println(r1.print());
        r1.add(new int[]{20, 20});
        System.out.println(r1.print());
        r1.add(new int[]{20, 21});
        System.out.println(r1.print());
        r1.add(new int[]{2, 4});
        System.out.println(r1.print());
        r1.add(new int[]{3, 8});
        System.out.println(r1.print());
        r1.remove(new int[]{10, 10});
        System.out.println(r1.print());
        r1.remove(new int[]{10, 11});
        System.out.println(r1.print());
        r1.remove(new int[]{15, 17});
        System.out.println(r1.print());
        r1.remove(new int[]{3, 19});
        System.out.println(r1.print());
    }
}
