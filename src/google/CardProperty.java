package google;

/*

给一个M*N的二维数组。横行是card纵行是attribute，在一个纵行中，假如每个数字都相同或不同就可以match。给一个二维数组，返回每个纵行是否全都match‍‌‌‌‍‌‍‌‍‌‍‌‍‍‌‌‍‍‌‌。
follow up:
a. 假设*可以为任何数字，数组中可能存在*的情况下，该二维数组是否可以match。clarify: 不考虑恰好超出数量限制的情况，即假设attribute取值无限多
b. 在M*N的数组中挑选K行，K*N可以match，总共有多少种可能。



一堆卡片，每个卡片上有四个property（a,b,c,d），每个property有三个value(1,2,3).
对于每三张卡片来说，如果它们四个property值都一样，或者都不一样，则它们属于一个set。
eg:[1,2,3,1],[1,2,3,1],[1,2,3,1] 或者[1,2,3,1][2,3,1,2][3,1,2,3].
判断三张卡片是否构成一个set。追问，给一堆卡片，从里面找出一个set

问的有点多，我分开答：
1. 先用counter计数，然后检查每种相同的card数量是否>=k，是的话就形成mathch的组合，计算C(k,n)累加。
然后用counter的keys作为输入进行backtrack，发现成立就把组成数组card的数量相乘后加到最终结果。这是我事后想的思路，不一定对。

 */

import java.util.*;

public class CardProperty {
    // give 3 card, check whether it is a set
    boolean valid(List<int[]> cards) {
        Set<Integer> properties = new HashSet<>(Arrays.asList(1, 2, 3));
        if (Arrays.equals(cards.get(0), cards.get(1)) && Arrays.equals(cards.get(2), cards.get(1)))
            return true;
        for (int i = 0; i < cards.get(0).length; i++) {
            if (cards.get(0)[i] == cards.get(1)[i]) return false;
            if (cards.get(2)[i] != 6 - cards.get(0)[i] - cards.get(1)[i]) return false;
        }
        return true;
    }

    // give a list of cards, find a set
    List<int[]> findSet(List<int[]> cards) {
        Map<String, List<int[]>> code2scard = new HashMap<>();
        for (int[] card : cards) {
            StringBuilder sb = new StringBuilder();
            for (int p : card) {
                sb.append(p);
                sb.append(":");
            }
            code2scard.putIfAbsent(sb.toString(), new ArrayList<>());
            code2scard.get(sb.toString()).add(card);
        }
        for (List<int[]> card : code2scard.values()) {
            if (card.size() >= 3) return Arrays.asList(card.get(0), card.get(1), card.get(2));
        }
        for (int i = 0; i < cards.size(); i++) {
            for (int j = 0; j < cards.size(); j++) {
                boolean valid = true;
                StringBuilder nextCard = new StringBuilder();
                for (int k = 0; k < cards.get(i).length; k++) {
                    if (cards.get(i)[k] == cards.get(j)[k]) {
                        valid = false;
                        break;
                    }
                    nextCard.append(6 - cards.get(i)[k] - cards.get(j)[k]);
                    nextCard.append(":");
                }
                if (valid && code2scard.containsKey(nextCard.toString()))
                    return Arrays.asList(cards.get(i), cards.get(j), code2scard.get(nextCard.toString()).get(0));

            }
        }
        return new ArrayList<>();
    }

    // give a m*n, find k cards as a set
    List<int[]> findSet(List<int[]> cards, int k) {
        Map<String, List<int[]>> code2scard = new HashMap<>();
        for (int[] card : cards) {
            StringBuilder sb = new StringBuilder();
            for (int p : card) {
                sb.append(p);
                sb.append(":");
            }
            code2scard.putIfAbsent(sb.toString(), new ArrayList<>());
            code2scard.get(sb.toString()).add(card);
        }
        for (List<int[]> card : code2scard.values()) {
            if (card.size() >= k) return Arrays.asList(card.get(0), card.get(1), card.get(2));
        }
        Set<Integer>[] properties = new Set[cards.get(0).length];
        for (int i = 0; i < properties.length; i++) properties[i] = new HashSet<>();
        List<int[]> set = new ArrayList<>();
        backtrack(properties, k, set, cards, 0);
        return set;
    }

    boolean backtrack(Set<Integer>[] properties, int k, List<int[]> set, List<int[]> cards, int index) {
        if (k == 0) return true;
        for (int i = index; i < cards.size(); i++) {
            boolean valid = true;
            for (int j = 0; j < cards.get(i).length; j++) {
                if (properties[j].contains(cards.get(i)[j])) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                for (int j = 0; j < cards.get(i).length; j++) {
                    properties[j].add(cards.get(i)[j]);
                }
                set.add(cards.get(i));
                if (backtrack(properties, k - 1, set, cards, i + 1)) return true;
                else {
                    for (int j = 0; j < cards.get(i).length; j++) {
                        properties[j].remove(cards.get(i)[j]);
                    }
                    set.remove(set.size() - 1);
                }
            }
        }
        return false;
    }


    int countSet(List<int[]> cards, int k) {
        int res = 0;
        Map<String, List<int[]>> code2scard = new HashMap<>();
        for (int[] card : cards) {
            StringBuilder sb = new StringBuilder();
            for (int p : card) {
                sb.append(p);
                sb.append(":");
            }
            code2scard.putIfAbsent(sb.toString(), new ArrayList<>());
            code2scard.get(sb.toString()).add(card);
        }
        for (List<int[]> card : code2scard.values()) {
            // find 3 cards in 5 cards, 4 + 3 + 2 + 1
            if (card.size() >= k) res += countSelection(k, card.size());
        }
        Set<Integer>[] properties = new Set[cards.get(0).length];
        for (int i = 0; i < properties.length; i++) properties[i] = new HashSet<>();
        List<int[]> set = new ArrayList<>();
        res+=backtrack1(properties, k, set, cards, 0);
        return res;
    }
    int countSelection(int a, int b){
        int res = 1;
        for(int i = 0; i < a; i++){
            res *= (b-i);
        }
        for(int i = 1; i <= a; i++){
            res /= i;
        }
        return res;
    }

    int backtrack1(Set<Integer>[] properties, int k, List<int[]> set, List<int[]> cards, int index) {
        if (k == 0) return 1;
        int res = 0;
        for (int i = index; i < cards.size(); i++) {
            boolean valid = true;
            for (int j = 0; j < cards.get(i).length; j++) {
                if (properties[j].contains(cards.get(i)[j])) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                for (int j = 0; j < cards.get(i).length; j++) {
                    properties[j].add(cards.get(i)[j]);
                }
                set.add(cards.get(i));
                res+=backtrack1(properties, k - 1, set, cards, i + 1) ;

                    for (int j = 0; j < cards.get(i).length; j++) {
                        properties[j].remove(cards.get(i)[j]);
                    }
                    set.remove(set.size() - 1);

            }
        }
        return res;
    }

    public static void main(String[] args) {
//        for (int[] card : new CardProperty().findSet(Arrays.asList(new int[]{1, 2, 3, 1}, new int[]{2, 3, 1, 2}, new int[]{3, 1, 2, 3},new int[]{4, 4, 4, 4}), 4))
//            System.out.println(Arrays.toString(card));
        System.out.println( new CardProperty().countSet(Arrays.asList(new int[]{1, 2, 3, 1}, new int[]{2, 3, 1, 2}, new int[]{3, 1, 2, 3},new int[]{4, 4, 4, 4}), 2));
        System.out.println( new CardProperty().countSet(Arrays.asList(new int[]{4, 4, 4, 4}, new int[]{4, 4, 4, 4}, new int[]{4, 4, 4, 4},new int[]{4, 4, 4, 4}), 2));

    }
}
