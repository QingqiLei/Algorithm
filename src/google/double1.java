package google;

import java.util.*;

/*
第一题：给你一个list，double each element in the list and append at the end,
要求return一个它的permutation，这个output要是non-deterministic的
e.g. [1,4,2,3] -> [1,4,2,3,2,8,4,6] (doubled) -> [3,2,4,1,8,4,6,2] (permuted)
第二题：反过来，给你一个permutation返回这个list
 */
public class double1 {
    List<Integer> getDoubledList(List<Integer> list) {
        List<Integer> res = new ArrayList<>(list);
        int n = list.size();
        for (int i = 0; i < n; i++) {
            res.add(res.get(i) * 2);
        }
//        int tmp = list.get(0);
//        list.set(0,list.get(1));
//        list.set(1, tmp);
        Collections.shuffle(res);
        return res;
    }

    List<Integer> getOriginalList(List<Integer> list) {
        if (list.size() % 2 == 1) return new ArrayList<>();
        Map<Integer, Integer> num2Cnt = new HashMap<>();
        for (int i : list) num2Cnt.put(i, num2Cnt.getOrDefault(i, 0) + 1);
        List<Integer> res = new ArrayList<>();
        for (int i : list) {
            if (!num2Cnt.containsKey(i)) continue;
            if (!num2Cnt.containsKey(i * 2)) continue;
            res.add(i);
            num2Cnt.put(i, num2Cnt.get(i) - 1);
            if (num2Cnt.get(i) == 0) num2Cnt.remove(i);
            num2Cnt.put(i * 2, num2Cnt.get(i * 2) - 1);
            if (num2Cnt.get(i * 2) == 0) num2Cnt.remove(i * 2);
        }
        if(res.size() *2 != list.size()) return new ArrayList<>();
        return res;
    }


    public static void main(String[] args) {
        double1 d = new double1();
        List<Integer> doubled = d.getDoubledList(Arrays.asList(1, 2, 6, 5, 3, 1, 7, 5, 2));
        System.out.println(doubled);
        System.out.println(d.getOriginalList(doubled));
    }
}
