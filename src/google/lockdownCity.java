package google;

import java.util.*;

/*
疫情期间快递从city s 到city e 要求经过最少的lockdown city
 */
public class lockdownCity {

    int miniLockDownCity(int n, int[][] edges, boolean[] lockdown, int a, int b){
        List<Integer>[]  graph = new List[n];
        for(int i = 0; i < n; i++)graph[i]= new ArrayList<>();
        for(int[] edge: edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        // dijkstra
        int[] mini = new int[n];
        Arrays.fill(mini, Integer.MAX_VALUE);
        // [city, lockdownCities]
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(arr-> arr[1]));
        mini[a] = (lockdown[a] ? 1:0);
        pq.add(new int[]{a, mini[a]});
        while(pq.size() > 0){
            int cur[] = pq.poll(), city = cur[0], lock = cur[1];
            for(int next: graph[city]){
                int nextLock = lock;
                if(lockdown[next]) nextLock++;
                if(nextLock <mini[next]){
                    mini[next] = nextLock;
                    pq.add(new int[]{next, nextLock});
                }
            }

        }
        return mini[b];
    }
}
