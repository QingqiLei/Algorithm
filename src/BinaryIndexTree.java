import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentSkipListSet;

public class BinaryIndexTree {

    private int[] node;
    public BinaryIndexTree(int size){
        node = new int[size +1];
    }
    // index is indexed 1
    public void update(int index, int diff){
        while(index < node.length){
            node[index] +=diff;
            index +=index & -index;
        }
    }
    public int query(int index){
        int sum = 0;
        while(index > 0){
            sum += node[index];
            index -= index & -index;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println("\t".length());
        new LinkedHashSet<>();
        LinkedHashSet<Integer> t = new LinkedHashSet<>();
//        t.add(2);
//        t.add(3);
//        t.add(-1);
//        t.add(1);
        for(int i = 0; i  < 10; i++)
        System.out.println(new Random().nextInt(2));
        System.out.println(Integer.toBinaryString(Integer.parseInt("34", 16)));
    }

}
