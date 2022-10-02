package google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
给你有向图的边，请统计出度为0的点总数，
输出这些出度为0的点，输出这些点所在的边
 */
public class graph1 {
    int calc(int[][] edges){
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int[] edge : edges){
            map.putIfAbsent(edge[0], new ArrayList<>());
            map.putIfAbsent(edge[1],new ArrayList<>());
            map.get(edge[0]).add(edge[1]);
        }
        int cnt = 0;
        for(int from: map.keySet()){
            if(map.get(from).size() == 0)  cnt++;
        }
        return cnt;
    }
}
