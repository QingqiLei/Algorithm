import java.util.*;

public class GF {

    //    int m;
    int n;

    GF(int n, int m) {
        this.n = n;
//        this.m = m;
    }

    void devide(TreeMap<Integer, Integer> power2Num, TreeMap<Integer, Integer> mod) {
        int high = mod.lastKey();
        TreeMap<Integer, Integer> left = new TreeMap<>(power2Num);
        while (left.size() > 0 && left.lastKey() >= high) {
            int diff = left.lastKey() - high;
            TreeMap<Integer, Integer> modMul = mulNum(mod, diff);
            left = format(minusMap(left, modMul));
        }

        showMap(left);

    }

    void showMap(TreeMap<Integer, Integer> map) {
        for (int key : map.keySet()) {
            System.out.print(key + " -> " + map.get(key) + ", ");

        }
        System.out.println();
    }

    TreeMap<Integer, Integer> format(TreeMap<Integer, Integer> map) {
        TreeMap<Integer, Integer> res = new TreeMap<>();
        for (int power : map.keySet()) {
            res.put(power, ((map.get(power) % n) + n) % n);
        }
        return res;
    }

    TreeMap<Integer, Integer> minusMap(TreeMap<Integer, Integer> map, TreeMap<Integer, Integer> num) {
        TreeMap<Integer, Integer> res = new TreeMap<>();
        for (int power : map.keySet()) {
            if (map.get(power) - num.getOrDefault(power, 0) != 0)
                res.put(power, map.get(power) - num.getOrDefault(power, 0));
        }
        for (int power : num.keySet()) {
            if (!map.containsKey(power))
                res.put(power, -num.get(power));
        }
        return res;
    }

    TreeMap<Integer, Integer> mulNum(TreeMap<Integer, Integer> map, int num) {
        TreeMap<Integer, Integer> res = new TreeMap<>();
        for (int power : map.keySet()) {
            res.put(power + num, map.get(power));
        }
        return res;
    }


    public static void main(String[] args) {
//        GF g = new GF(2,6);
//        TreeMap<Integer, Integer> num = new TreeMap<>();
//        TreeMap<Integer, Integer> mod = new TreeMap<>();
//        num.put(72,1);
//
//
//
//        mod.put(5,1);
//        mod.put(2,1);
//        mod.put(0,1);
//        g.devide(num, mod);



        System.out.println(new HashMap<>().get(0));




    }


}

class UnionFind {
    Map<Integer, Integer> map = new HashMap<>();

    int find(int n) {
        map.putIfAbsent(n, n);
        while (n != map.get(n)) {
            map.put(n, map.get(map.get(n)));
            n = map.get(n);
        }
        return n;
    }

    void union(int a, int b) {
        map.put(find(a), find(b));
    }
}
