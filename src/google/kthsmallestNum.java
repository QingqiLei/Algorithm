package google;

/*
给一个有序数组，找出第k个绝对值最小的数，这个题楼主觉得答得不是很好，
没有直接想到O(n)的解法，先用二分+双指针做出来了，
然后意识到了可以不需要二分找中心点，讲出了更优解法，followup继续优化时间复杂度，
口述divide and conquer解法，类似梨口第肆题。
 */
public class kthsmallestNum {
    int INVALID = Integer.MIN_VALUE;

    public int kthsmallestAbslute(int[] nums, int k) {
        if (k > nums.length) return INVALID;
        int index = firstNonNegative(nums);
        if (index == nums.length) { // all elements are negative
            return nums[nums.length  - k];
        } else if (index == 0) { // positive
            return nums[k - 1];
        } else {
            return findKth(nums, index -1, index, k);
        }
    }

    int findKth(int[] nums, int neg, int pos, int k) {
        if (neg < 0) return nums[pos + k - 1];
        if (pos >= nums.length) return nums[neg - k + 1];
        if (k == 1) return Math.min(-nums[neg], nums[pos]);
        int nextNeg = Integer.MAX_VALUE, nextPos = Integer.MAX_VALUE;
        if (neg - k / 2 + 1 >= 0) nextNeg = -nums[neg - k / 2 + 1];
        if (pos + k / 2 - 1 < nums.length) nextPos = nums[pos + k / 2 - 1];
        if (nextNeg < nextPos) {
            return findKth(nums, neg - k / 2, pos, k - k / 2);
        } else {
            return findKth(nums, neg, pos + k / 2, k - k / 2);
        }
    }


    int firstNonNegative(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= 0) right = mid - 1;
            else left = mid + 1;
        }
        // -2
        return left;
    }

    public static void main(String[] args) {
        System.out.println(new kthsmallestNum().kthsmallestAbslute(new int[]{-6,-5,-4,-3,-2,-1}, 4));
    }
}
