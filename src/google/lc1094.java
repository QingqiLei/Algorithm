package google;

public class lc1094 {
    public boolean carPooling(int[][] trips, int capacity) {
        long[] cnt = new long[1001];
        for(int i = 0; i < trips.length; i++){
            cnt[trips[i][1]]+=trips[i][0];
            cnt[trips[i][2]]-=trips[i][0];
        }
        for(int i = 1; i < cnt.length; i++){
            cnt[i] += cnt[i-1];
            if(cnt[i] > capacity) return false;
        }
        return true;
    }
}
