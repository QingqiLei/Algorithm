package google;

import java.util.*;

/*
设计一个Social Network中用户的数据结构和推荐规则，给用户A推荐出其最可能想认识的人B
input: 用户A output: 用户B
clarify: 找出A不认识的人中，和A拥有最多共同好友的人
follow up:
a. 输出topRecommandation改成Recommandationlist
b. 增加输入level，推荐间接认识的人，例如A - B - C - D - E，给A推荐C，D，E，要求按照中间层数排序
 */
public class friends {
    // from 0,n-1
    public int recommend(int[][] friends, int n, int a) {
        Map<Integer, Set<Integer>> person2friends = new HashMap<>();
        for (int i = 0; i < n; i++) person2friends.put(i, new HashSet<>());
        for (int[] friend : friends) {
            person2friends.get(friend[0]).add(friend[1]);
            person2friends.get(friend[1]).add(friend[0]);
        }
        int recommendedFriend = -1, commonFriends = 0;
        for (int i = 0; i < n; i++) {
            int tmp = 0;
            if (!person2friends.get(a).contains(i) &&
                    (tmp = countCommon(person2friends.get(a), person2friends.get(i))) > commonFriends) {
                recommendedFriend = i;
                commonFriends = tmp;
            }
        }
        return recommendedFriend;
    }

    int countCommon(Set<Integer> set1, Set<Integer> set2) {
        int cnt = 0;
        for (Integer i : set1) {
            if (set2.contains(i)) cnt++;
        }
        return cnt;
    }

    public List<List<Integer>> recommend1(int[][] friends, int n, int a) {
        Set<Integer>[] graph = new Set[n];
        for (int i = 0; i < n; i++) graph[i] = new HashSet<>();
        for (int[] f : friends) {
            graph[f[0]].add(f[1]);
            graph[f[1]].add(f[0]);
        }
        Queue<Integer> q = new LinkedList<>(graph[a]);
        List<List<Integer>> res = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();
        seen.add(a);
        seen.addAll(graph[a]);
        while (q.size() > 0) {
            List<Integer> level = new ArrayList<>();
            int size = q.size();
            while (size-- > 0) {
                int cur = q.poll();
                for (Integer next : graph[cur]) {
                    if (!seen.add(next)) continue;
                    q.add(next);
                }
            }
            res.add(level);
        }
        return res;
    }

}
