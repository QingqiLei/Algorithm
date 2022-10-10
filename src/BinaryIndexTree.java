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
        BinaryIndexTree bit = new BinaryIndexTree(5);
        bit.update(1,4);
        bit.update(2,2);
        bit.update(5,5);
        System.out.println(bit.query(1));
        System.out.println(bit.query(2));
        System.out.println(bit.query(5));

    }

}
