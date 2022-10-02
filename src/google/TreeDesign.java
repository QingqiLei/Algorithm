package google;

import java.util.ArrayList;
import java.util.List;

/*
自己设计一个类似于bst形状的class. 属性有string和类型为整型长度变量，
e.g. 现在有abcd.....xyz的字符串，
 树的形状是 11， 15， 15 下面又细分6， 9。 对每个点， root 是26， 字符串空，
 Node11: 有属性int len = 11, string s = "abcdefghijk",
 相当于截取输入字符串的index 0 - 10的内容，
 node 15, 字符串空， 长度15，
 node 6 :字符串截取输入字符串index 11- 16的内容，len = 6;
 node 9 : 字符串截取输入字符串index 17- 25的内容，len = 9。
 当时读题读了快五分钟，也没给提示和例子，反复确认了好久。
 后来面试官觉得我还是没get， 就画了个图上面写的例子给我看， 我这才反应过来。
 然后要写一个函数，输入string s, int index.要求返回index在对应node的相对位置。
  后来又是自己定义类， 又是自己写函数.
  follow up 用几种corner case, 并要求修改之前写的函数。
  这一轮我答得不是很好，题目理解的不是很到位总感觉云里雾里，
  所以导致我定义类属性想了好久，想着要定义那几个。
 */
public class TreeDesign {


    int getNode(String str, int index, TreeNode root) {
        if (index >= root.len) return -1;
        if (root.left == null && root.right == null) return index;

        int sum = 0;
        if (root.left != null) {
            if (root.left.len > index) {
                return getNode(str, index, root.left);
            }
            sum += root.left.len;
        }

        if (root.right != null) return getNode(str.substring(sum), index - sum, root.right);
        return -1;
    }

    class TreeNode {
        int len;
        TreeNode left;
        TreeNode right;
        String value;
    }

    class Node{
        int len;
        List<Node> children = new ArrayList<>();
        String value;
    }

    int getNode(int index, Node root) {
        if(index >= root.len) return -1;
        if(root.children.size() == 0) return index;

        for(Node child: root.children){
            if(index >=child.len ) index -= child.len;
            else{
                return getNode(index, child);
            }
        }

        return -1;
    }
}
