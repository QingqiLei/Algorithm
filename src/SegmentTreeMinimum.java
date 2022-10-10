

public class SegmentTreeMinimum {
    int[] tree;
    int size;

    public SegmentTreeMinimum(int[] nums) {

        size = nums.length;
        tree = new int[size * 2];
        for (int i = 0; i < size; i++) {
            update(i, nums[i]);
        }

    }
    // tree[i] is the parent of tree[i*2] and tree[i*2+1]
    public void update(int index, int val) {
        tree[index + size] = val;
        for (int i = (index + size) / 2; i > 0; i /= 2) {
            tree[i] = Math.min(tree[i * 2], tree[i * 2 + 1]);
        }
    }

    public int getMinimum(int left, int right) {
        int minimum = Integer.MAX_VALUE;
        for(int i = left + size, j = right + size; i <=j; i/=2, j/=2){
            if(i%2 == 1){
                minimum = Math.min(minimum, tree[i]);
                i++;
            }
            if(j%2 == 0) {
                minimum = Math.min(minimum, tree[j]);
                j--;
            }
        }
        return minimum;
    }

    public static void main(String[] args) {

    }
}
