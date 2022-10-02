package  google;
import java.util.HashMap;
import java.util.Map;

public class LRU {

    int capacity;
    DoubleLinkedNode head;
    DoubleLinkedNode tail;
    Map<Integer, DoubleLinkedNode> cache = new HashMap<>();



    public LRU(int capacity) {
        this.capacity = capacity;
        head = new DoubleLinkedNode();
        tail = new DoubleLinkedNode();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {

        if(cache.containsKey(key)) {
            DoubleLinkedNode cur = cache.get(key);
            remove(cur);
            addNode(cur);
            return cache.get(key).value;

        }

        else return -1;
    }

    public void put(int key, int value) {
        // move the node to head
        if(cache.containsKey(key)){
            DoubleLinkedNode cur = cache.get(key);
            // remove 
            remove(cur);
            cur.value = value;
            // insert to head
            addNode(cur);
        }else{
            DoubleLinkedNode cur = new DoubleLinkedNode(key,value, head, head.next);
            cache.put(key, cur);
            addNode(cur);
        }
        if(cache.size() > capacity){
            DoubleLinkedNode cur = tail.pre;
            cache.remove(cur.key);
            remove(cur);
        }
    }

    void remove(DoubleLinkedNode cur){
        cur.pre.next = cur.next;
        cur.next.pre = cur.pre;
    }
    void addNode(DoubleLinkedNode cur){
        cur.next = head.next;
        cur.pre = head;
        cur.next.pre = cur;
        head.next = cur;
    }






    class DoubleLinkedNode{
        DoubleLinkedNode pre = null;
        DoubleLinkedNode next = null;
        int value;
        int key;
        DoubleLinkedNode(){

        }
        DoubleLinkedNode(int key, int value, DoubleLinkedNode pre,DoubleLinkedNode next ){
            this.key = key;
            this.value = value;
            this.pre = pre;
            this.next = next;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */