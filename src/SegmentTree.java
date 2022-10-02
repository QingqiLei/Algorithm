import javafx.util.Pair;

import java.util.*;

class SegmentTree {
    int[] tree;
    int size;


    SegmentTree(int size) {
        tree = new int[size * 2];
        this.size = size;
    }

    public void update(int index, int val) {
        tree[index + size] = val;
        for (int i = (index + size) / 2; i > 0; i /= 2) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    // including, including
    public int sumRange(int left, int right) {
        int res = 0;
        for (int i = left + size, j = right + size; i <= j; i /= 2, j /= 2) {
            if (i % 2 == 1) {
                res += tree[i];
                i++;
            }
            if (j % 2 == 0) {
                res += tree[j];
                j--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        SegmentTree s = new SegmentTree(5);
        for(int i = 0; i < 5; i++) s.update(i,i+1);
        System.out.println(Arrays.toString(s.tree));
        System.out.println(s.sumRange(0,4));
    }
}

