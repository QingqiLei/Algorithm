package google;

import java.util.*;

/*
文件系统，题目有点像蠡口690 employee importance。
操作系统中，文件夹或者文件有一个ID，
问给定一个ID，返回其下面的所有文件夹和文件的大小。
这一题的难点也是怎么定义class，
因为这个class要适用文件和文件夹，定义完class和写完函数，要求拿example具体run一遍。
follow up1 ： 在真实的file system structure中，那些是valid, 换个问法就是那些是不valid的，
他强调了是structure上面的。
我回答说迭代的时候不能有死循环，一个文件夹下面有另外一个文件夹，这个文件夹下面有父文件夹。
面试官问如何改正，然后我就修改了代码。又问有没有其他的invalid的例子（相当于在问corner case). 没答出来，
给了hint， 会不会有两个文件夹下面都含有同一个文件。
follow up 2: 如果这个文件系统在服务器端，有大量的读写操作，如何优化，我回答说在class里定义一个boolean flag,
int total_size 如果有写操作，先读取total_size, 把flag设置成true, 下次访问更新total_size,
如果flag 是false,直接读取。 答完刚好到点，然后我随便问了几个问题。
 */
public class FileSystem {
    class directoryNode{
        List<directoryNode> subdirectories = new ArrayList<>();
        int size ;
        directoryNode parent;
        directoryNode(int size){
            this.size = size;
        }
        List<fileNode> files;
    }
    class fileNode{
    }

    int INVALID = Integer.MIN_VALUE;
    Map<directoryNode, Integer> cache  = new HashMap<>();
    // detect cycle
    Set<directoryNode> inDfs = new HashSet<>();

    int dfs(directoryNode n){
        if(cache.containsKey(n)) return cache.get(n);
        inDfs.add(n);
        int totalSize = n.size;
        for(directoryNode child: n.subdirectories){
            if(inDfs.contains(child)) return INVALID;
                totalSize += dfs(child);
        }
        cache.put(n, totalSize);
        inDfs.remove(n);
        return totalSize;
    }

    Map<directoryNode, Integer> getSize(List<directoryNode> nodes){
        Map<directoryNode, Integer> res = new HashMap<>();
        for(directoryNode node: nodes){
            if(cache.containsKey(node)){
                res.put(node, cache.get(node));
            }else res.put(node, dfs(node));
        }
        return res;
    }

    void add(directoryNode parent,directoryNode subdirectory){
        parent.subdirectories.add(subdirectory);
        int subdirectorySize = getSize(Arrays.asList(subdirectory)).get(subdirectory);
        while (parent != null){
            parent.size += subdirectorySize;
            parent = parent.parent;
        }
    }

}
