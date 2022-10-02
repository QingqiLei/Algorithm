import java.sql.Array;
import java.util.*;

public class test {

    public static void main(String[] args) {
//        System.out.println(solve(new String[][]{{"10", "d0", "d1"},{"15", "EMPTY", "EMPTY"},{"20", "d1", "empty"}},
//                new String[][]{{"d0", "1","27"},{"d1", "2", "5"}}));
//
//        System.out.println(Arrays.toString(solve3(new String[]{"listen", "silent", "it", "is"}, new String[]{"listen it is silent"})));
//        System.out.println(minimumDifference(new int[]{37,27,39,45,45,11,50,4,5,38,13,33,28,38,9,14,20,28,42,16,25,29,37,50,17,43,24,33,2,5}));
        System.out.println(maximumANDSum(new int[]{8, 13, 3, 15, 3, 15, 2, 15, 5, 7, 6}, 8));
    }

    public static int  maximumANDSum(int[] nums, int numSlots) {
        int[][] places = new int[numSlots][2];
        helper(places, nums, 0, nums.length, 0);
        new ArrayList<>().remo
        return res;
    }

    static  int  res =0;

    static void  helper(int[][] places, int[] nums, int index, int n, int sum) {
        if (index == n) {
            res = Math.max(res, sum);
            return;
        }
        for (int i = 0; i < places.length; i++) {
            for (int j = 0; j < 2; j++) {
                if (places[i][j] != 0) continue;
                places[i][j] = nums[index];
                helper(places, nums, index + 1, n, sum + ((i + 1) & nums[index]));
                places[i][j] = 0;
                break;
            }
        }
    }

    public static long minimumDifference(int[] nums) {
        int n = nums.length / 3;
        TreeMap<Integer, Integer> left = new TreeMap<>(), mid = new TreeMap<>(), right = new TreeMap<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if ((i / (nums.length / 3)) == 0) {
                left.put(nums[i], left.getOrDefault(nums[i], 0) + 1);
            } else list.add(nums[i]);
        }
        System.out.println(list);
        Collections.sort(list, (a, b) -> (Integer.compare(b, a)));
        System.out.println(list);

        for (int i = 0; i < 2 * n; i++) {
            if (i < n)
                right.put(list.get(i), right.getOrDefault(list.get(i), 0) + 1);
            else mid.put(list.get(i), mid.getOrDefault(list.get(i), 0) + 1);
        }

        long res = 0, sum = 0;
        for (int key : left.keySet()) {

            sum += (long) key * ((long) left.get(key));
        }
        for (int key : right.keySet()) {

            sum -= (long) key * ((long) right.get(key));
        }
        res = sum;


        System.out.println("rightkey " + right.keySet());
        System.out.println("midkey " + mid.keySet());
        System.out.println(res);

        for (int i = n; i < 2 * n; i++) {
            int num = nums[i];
            if (num >= right.firstKey()) {
                sum = sum + num - mid.lastKey();
                System.out.println(num + " mid " + mid.lastKey() + " first " + right.firstKey() + " " + right.get(num));
                right.put(num, right.get(num) - 1);
                if (right.get(num) == 0) right.remove(num);
                right.put(mid.lastKey(), right.getOrDefault(mid.lastKey(), 0) + 1);
            } else {
                mid.put(num, mid.get(num) - 1);
                if (mid.get(num) == 0) mid.remove(num);
            }

            if (num < left.lastKey()) {
//                System.out.println(num +"  left "+left.lastKey());
                sum = sum + num - left.lastKey();
                left.put(num, left.getOrDefault(num, 0) + 1);
                left.put(left.lastKey(), left.get(left.lastKey()) - 1);
                if (left.get(left.lastKey()) == 0) left.remove(left.lastKey());
            }

            res = Math.min(res, sum);


        }

        return res;

    }

    static int solve(String[][] products, String[][] discounts) {
        Map<String, int[]> discount = new HashMap<>();
        for (String[] d : discounts) {
            discount.put(d[0], new int[]{Integer.parseInt(d[1]), Integer.parseInt(d[2])});
        }
        int res = 0;
        for (String[] product : products) {

            res += helper(product, discount);
        }
        return res;

    }

    static int helper(String[] product, Map<String, int[]> discount) {
        double price = Double.parseDouble(product[0]);
        double res = price;
        for (int i = 1; i < product.length; i++) {
            if (discount.containsKey(product[i])) {
                int[] dis = discount.get(product[i]);
                if (dis[0] == 0) {
                    res = Math.min(res, dis[1]);
                } else if (dis[0] == 1) {
                    res = Math.min(res, Math.round(price * (100 - dis[1]) / 100));
                } else if (dis[0] == 2) {
                    res = Math.min(res, price - dis[1]);
                }

            }
        }
        return (int) res;

    }

    static int[] solve3(String[] words, String[] sentences) {
        Map<String, Integer> cnt = new HashMap<>();
        for (String word : words) {
            cnt.put(sortStr(word), cnt.getOrDefault(sortStr(word), 0) + 1);
        }
        int[] res = new int[sentences.length];
        for (int i = 0; i < sentences.length; i++) {
            String sentence = sentences[i];
            Map<Integer, Integer> map = new HashMap<>();
            for (String word : sentence.split(" ")) {
                int t = cnt.getOrDefault(sortStr(word), 0);
                map.put(t, map.getOrDefault(t, 0) + 1);
            }
            int sum = 1;
            for (int key : map.keySet()) {
                sum = sum * key * map.get(key);
            }
            res[i] = sum;
        }
        return res;

    }

    static String sortStr(String s) {
        char[] cs = s.toCharArray();
        Arrays.sort(cs);

        return new String(cs);

    }


}

