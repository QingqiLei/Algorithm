package google;

/*
二叉树不断删除叶子节点，直到整个树删空为止，输出数组
（任意的顺序都ok）follow up:
a. 要求新的叶子节点出现后，必须立刻删除新节点。
b. 要求新的叶子节点出现后，不能必须立刻删除新节点。

a. 要求新的叶子节点出现后，尽可能晚地删除新节点。clarify: 树不再是二叉树，要求重新设计节点。
先遍历出所有的叶子放进一个queue里面，每次删叶子的时候检查parent有没有变成叶子，是的话就把parent放进queue。
b. 删除叶子节点，顺序不限，要求输出所有可能删除顺序的总数量

 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class removeNodes {

    public List<List<Node>> getRemoveOrder(Node root){
        List<List<Node>> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }

    int dfs(Node root, List<List<Node>> res ){
        int index = 0;
        for(Node child: root.children){
            index = Math.max(index, dfs(child, res));
        }
        if(res.size() <=index) res.add( new ArrayList<>());
        res.get(index).add(root);
        return index +1;
    }

    public List<List<Node>> getRemoveOrder1(Node root){
        Queue<Node> q = new LinkedList<>();
        findLeaves(root, q);
        List<List<Node>> res = new ArrayList<>();
        while(q.size() > 0){
            int size = q.size();
            List<Node> level = new ArrayList<>();
            while(size -- > 0){
                Node cur = q.poll();
                level.add(cur);
                cur.parent.children.remove(cur);
                if(cur.parent.children.size() == 0) q.add(cur.parent);
            }
        }
        return res;
    }
    void findLeaves(Node root, Queue<Node> q){
        if(root.children.size() == 0)
            q.add(root);

        for(Node child: root.children){
            findLeaves(child, q);
        }
    }






    void test(){
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        Node d = new Node("d");
        Node e = new Node("e");
        Node f = new Node("f");
        a.children.add(b);
        a.children.add(c);
        b.children.add(d);
        d.children.add(e);
        d.children.add(f);

        System.out.println(getRemoveOrder(a));
    }

    public static void main(String[] args) {
        new removeNodes().test();
    }
    class Node{
        String val = "";
        List<Node> children = new ArrayList<>();
        Node parent = null;
        Node(String val){
            this.val = val;
        }

        @Override
        public String toString() {
            return val;
        }
    }


}

/*

class TreeNode:
    def __init__(self) -> None:
        self.val = 0
        self.parent = None
        self.children = set()

def deleteLeaves( root: TreeNode) -> int:
    mask = {}
    i = 1
    stack = [root]
    while stack:
        node = stack.pop()
        mask[node] = i
        i *= 2
        for child in node.children:
            stack.append( child)
        dp = [0] * i
        dp[0] = 1
        curSet, nextSet = set(), set()
        curSet.add(0)
        while curSet or nextSet:
            if not curSet:
                curSet, nextSet = nextSet, set()
                continue
            curMask = curSet.pop()
            for node in mask:
                isLeaf = True if not node.children else not any( mask[child] | curMask != mask for child in node.children)
                if isLeaf and mask[node] | curMask != curMask:
                    dp[ curMask | mask[node]] += dp[curMask]
                    nextSet.add( curMask | mask[node])
        return dp[-1]

 */