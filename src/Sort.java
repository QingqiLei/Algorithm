import jdk.internal.org.objectweb.asm.tree.MultiANewArrayInsnNode;

import java.util.*;

public class Sort {
    public int mergeSort(int[] arr, int[] temp, int l, int r) {
        int inversions = 0;
        if (r > l) {
            int mid = (r + l) / 2;
            inversions += mergeSort(arr, temp, l, mid);
            inversions += mergeSort(arr, temp, mid + 1, r);
            inversions += merge(arr, temp, l, mid + 1, r);
        }
        return inversions;
    }

    int merge(int[] arr, int[] temp, int l, int m, int r) {
        int i = l, j = m, index = l, swaps = 0;
        while ((i <= m - 1) && j <= r) {
            if (arr[i] <= arr[j]) temp[index++] = arr[i++];
            else {
                temp[index++] = arr[j++];
                swaps += (m - i);
            }
        }
        while (i <= m - 1) temp[index++] = arr[i++];
        while (j <= r) temp[index++] = arr[j++];
        for (i = l; i <= r; i++) arr[i] = temp[i];
        return swaps;
    }

    public static void main(String[] args) {
//        Sort s = new Sort();
//        int[] arr = new int[]{4, 2, 32, 23, 2, 5234, 2, 34, 34, 5, 34, 532, 4, 23, 5, 6, 326, 4, 5, 432};
//        int[] arr1 = new int[]{1, 20, 6, 4, 5};
//        int t = s.mergeSort(arr1, new int[arr1.length], 0, arr1.length - 1);
//        System.out.println(t);
//        for (int i : arr1) System.out.print(i + " ");
//        System.out.println(Math.atan2(2,3)/Math.PI*180 );
//        "w".substring(1);
        String[] sentence = new String[]{"324", "wef"};
        String s = String.join(" ", sentence) + " ";
        double[][] arr = new double[10][2];
        Arrays.sort(arr, (a, b) -> {
            if (b[0] > a[0]) return 1;
            else return -1;
        });
        System.out.println(s);
        Node head= new Node(3,new Node(5,new Node(2, new Node(3,null))));
        System.out.println(new Sort().solve1(head));
        int[][] tasks = new int[2][2];
        Arrays.sort(tasks, (a,b)->{if(a[0] == b[0]) return a[1] - b[1]; else return a[0] - b[0];});
    }

    int solve1(Node node) {
        head = node;
        return helper(node);
    }



    Node head;

    int helper(Node tail) {
        int res = 0;
        if (tail == null) return 0;
        res = Math.max(res, helper(tail.next));
        res = Math.max(res, tail.data + head.data);
        head = head.next;
        return res;
    }
}


class Node {
    int data;
    Node next;

    Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
}
