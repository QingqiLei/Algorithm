package google;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/*
I will run a couple of simple examples to illustrate my thoughts.



给了一个function bad_commit(commit1, commit2) 如果commit 1 到 2的时候对performance有负面影响就会return true
然后给了个list让找出所有bad commit [commit1, commit2, commit3, commit4]
这道题应该是用binary search 解，但是有些不少条件都没给 导致聊了很久都没想到具体应该怎么用
最后说 如果bad_commit(commit1, commit4) 会返回false 就说明1-4都没有bad commit 但是只剩下10分钟了
后面她说给个hint 结果就给我定义了一个function😂，说可以用recursion，最后随便写了写 这轮应该是挂了
主要一直没问bad_commit(commit1, commit4)的这个情况
 */
public class bad_comment {

    boolean bad_commit(List<String> commits, int left, int right){
        while(left <= right){
            if(commits.get(left).contains("bad")) return true;
            left++;
        }
        return false;
    }

    List<String> res = new ArrayList<>();
    public List<String> findBadCommit(List<String> commits){
        findBadCommit(commits, 0, commits.size()-1);
        return res;
    }

    public void findBadCommit(List<String> commits, int left, int right){
        if(left > right) return;
        if(left == right) {
            res.add(commits.get(left));
            return;
        }
        int mid = left + (right - left)/2;
        if(mid - left >=0 && bad_commit(commits, left, mid))  findBadCommit(commits, left, mid);
        if(right - mid -1 >=0 && bad_commit(commits, mid+1, right)) findBadCommit(commits, mid+1, right);
    }

    public static void main(String[] args) {

//        bad_comment b = new bad_comment();
//        System.out.println(b.findBadCommit(Arrays.asList("good1", "bad1","good2", "bad3","good1", "bad")));
        TreeMap<Integer, Integer> map  = new TreeMap<>();
        map.put(1,0);
        map.put(2,0);
        map.put(3,0);
        map.put(7,0);

        System.out.println(map.ceilingKey(0));
        System.out.println(map.floorKey(11));

    }
}
