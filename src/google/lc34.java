package google;

/**
 * Find First and Last Position of Element in Sorted Array
 */
public class lc34 {
    public int[] searchRange(int[] nums, int target) {
        int left = firstGreaterEqual(nums, target);
        if(left < 0 || left >= nums.length || nums[left] != target) return new int[]{-1,-1};
        return new int[]{left, lastLessEqual(nums, target)};
    }

    int firstGreaterEqual(int[] nums, int target){
        int left = 0, right = nums.length-1;
        while(left <= right){
            int mid = left +(right - left)/2;
            if(nums[mid] >= target) right = mid-1;
            else left = mid+1;
        }
        return left;
    }

    int lastLessEqual(int[] nums, int target){
        int left = 0, right = nums.length-1;
        while(left <= right){
            int mid = left +(right - left)/2;
            if(nums[mid] > target) right = mid-1;
            else left = mid+1;
        }
        return right;
    }

}
