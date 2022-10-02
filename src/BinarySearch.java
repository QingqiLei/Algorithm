import java.util.HashMap;
import java.util.TreeMap;

public class BinarySearch {

    public static int firstEqualGreater(int[] A, int target){
        int left = 0, right = A.length-1;
        while(left <= right){
            int mid = left + ( right - left)/2;
            if(A[mid] < target) left = mid+1;
            else right = mid-1;
        }
        return left;
    }

    public static int lastEqualLess(int[] A, int target){
        int left = 0, right = A.length-1;
        while(left <= right){
            int mid = left + ( right - left +1)/2;
            if(A[mid] > target) right = mid -1;
            else left = mid+1;
        }
        return right;
    }


}
