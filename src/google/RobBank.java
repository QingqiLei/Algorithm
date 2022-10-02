package google;

import java.util.*;

/*
一个劫匪想要打劫周边的银行并在规定时间回到原点。有一个图，有到各银行的距离和银行的钱数，问他怎么走能拿到更多钱
 */
public class RobBank {
    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        int n = values.length;
        List<int[]>[] graph = new List[n];
        for(int i = 0; i < n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int[] edge: edges){
            graph[edge[0]].add(new int[]{edge[1], edge[2]});
            graph[edge[1]].add(new int[]{edge[0], edge[2]});
        }

        dfs(0, graph, values, new HashSet<>(Arrays.asList(0)), values[0], maxTime);
        return maxMoney;
    }

    int maxMoney = 0;
    void dfs(int cur, List<int[]>[] graph,int[] values, Set<Integer> visited, int money, int time){

        if(cur == 0) maxMoney = Math.max(money, maxMoney);

        for(int[] next: graph[cur]){
            int nextBank = next[0], w = next[1];
            if(time < w) continue;
            int money1 = money;
            boolean contains = visited.contains(nextBank);
            if(!contains) money1 +=  values[nextBank];
            visited.add(nextBank);
            dfs(nextBank, graph, values, visited, money1, time - w);
            if(!contains) visited.remove(nextBank);
        }
    }
}
