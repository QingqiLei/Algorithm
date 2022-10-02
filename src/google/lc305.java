package google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class lc305 {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> ans = new ArrayList<>();
        UnionFind uf = new UnionFind();
        for(int[] pos: positions){
            int r = pos[0],c = pos[1], code = encode(n,r,c);
            uf.find(code);
            if(r-1 >=0 && uf.map.containsKey(encode(n, r-1, c))) uf.union(encode(n, r-1, c), code);

            if(r+1 < m && uf.map.containsKey(encode(n, r+1, c))) uf.union(encode(n, r+1, c), code);
            if(c-1 >=0 && uf.map.containsKey(encode(n, r, c-1))) uf.union(encode(n, r, c-1), code);
            if(c+1 < n && uf.map.containsKey(encode(n, r, c+1))) uf.union(encode(n, r, c+1), code);

            ans.add(uf.parts);
        }
        return ans;
    }
    int encode(int n, int r, int c){
        return r*n + c;
    }
    class UnionFind{
        int parts = 0;
        Map<Integer, Integer> map = new HashMap<>();

        int find(int n){
            if(!map.containsKey(n)){
                parts++;
                map.put(n,n);
            }
            while(n!= map.get(n)){
                map.put(n, map.get(map.get(n)));
                n = map.get(n);
            }
            return n;
        }

        void union(int a, int b){
            if(find(a) != find(b)) parts--;
            map.put(find(a), find(b));
        }
    }



}
