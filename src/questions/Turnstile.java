package questions;

import java.util.LinkedList;
import java.util.Queue;

public class Turnstile {
    static int[] getTimes(int[] times, int[] directions) {
        int last = 1, recordTime = -1, n = times.length, res[] = new int[n],
                index = 0, time = 0;
        // the times array is sorted,
        Queue<Integer> entry = new LinkedList<>();
        Queue<Integer> exit = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            // if the two queue is empty, reassign time
            if (entry.size() == 0 && exit.size() == 0) time = times[index];
            // times is sorted, let people comes now to enter the queue.
            while (index < n && times[index] <= time) {
                if (directions[index] == 0) entry.add(index);
                else exit.add(index);
                index++;
            }
            // two queue are not empty
            if (entry.size() != 0 && exit.size() != 0) {
                if (time == recordTime + 1) {
                    if (last == 1) {
                        res[exit.poll()] = time;
                    } else if (last == 0) {
                        res[entry.poll()] = time;
                    }
                }
            }
            // if one queue is not empty or last second is not used.
            else if (exit.size() != 0) {
                last = 1;
                res[exit.poll()] = time;
            } else {
                last = 0;
                res[entry.poll()] = time;
            }
            recordTime = time;
            time++;
        }
        return res;
    }

    private static void test(int[] result, int[] expects) {
        for (int i : result) System.out.print(i + " ");
        System.out.println();
        assert (result.length == expects.length);
        for (int i = 0; i < result.length; i++) {
            assert (result[i] == expects[i]);
        }
    }


    public static void main(String[] args) {

        int[] times = {0, 0, 1, 5};
        int[] directions = {0, 1, 1, 0};
        int[] expects = {2, 0, 1, 5};
        test(getTimes(times, directions), expects);

        times = new int[]{1, 2, 4};
        directions = new int[]{0, 1, 1};
        expects = new int[]{1, 2, 4};
        test(getTimes(times, directions), expects);
//
        times = new int[]{1, 1};
        directions = new int[]{1, 1};
        expects = new int[]{1, 2};
        test(getTimes(times, directions), expects);
//
        times = new int[]{1, 1, 3, 3, 4, 5, 6, 7, 7};
        directions = new int[]{1, 1, 0, 0, 0, 1, 1, 1, 1};
        expects = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        test(getTimes(times, directions), expects);

    }
}
