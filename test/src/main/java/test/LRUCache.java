package test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache{
    class DLNode{
        int key;
        int val;
        DLNode prev;
        DLNode next;
        public DLNode(){}
        public DLNode(int _key, int _val){key = _key; val = _val;}
    }

    private Map<Integer, DLNode> cache = new HashMap<>();
    private int size;
    private int capacity;
    private DLNode head, tail;

    public LRUCache(int capacity){
        this.size = 0;
        this.capacity = capacity;
        this.head = new DLNode();
        this.tail = new DLNode();
        this.head.next = tail;
        this.tail.prev = head;
    }

    public int get(int key){
        DLNode node = cache.get(key);
        if (node == null) return -1;
        moveToHead(node);
        return node.val;
    }

    public void put(int key, int val){
        DLNode node = cache.get(key);
        if (node == null) {
            node = new DLNode(key, val);
            cache.put(key, node);
            addToHead(node);
            size++;
            if (size>capacity){
                DLNode tail = removeTail();
                cache.remove(tail.key);
                size--;
            }
        }else{
            node.val = val;
            moveToHead(node);
        }
    }

    private void addToHead(DLNode node){
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void moveToHead(DLNode node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
        addToHead(node);
    }

    private DLNode removeTail(){
        DLNode node = tail.prev;
        node.prev.next = node.next;
        node.next.prev = node.prev;
        return node;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(3);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        System.out.println(cache.get(2));
        cache.get(1);
        cache.get(3);
        cache.put(4, 4);
        System.out.println(cache.get(2));
    }
}


//public class LRUCache extends LinkedHashMap<Integer, Integer> {
//    private final int capacity;
//
//    public LRUCache(int capacity) {
//        super(capacity, 0.75f, true);
//        this.capacity = capacity;
//    }
//
//    @Override
//    public Integer get(Object key) {
//        return super.getOrDefault(key, -1);
//    }
//
//    @Override
//    public Integer put(Integer key, Integer value) {
//        return super.put(key, value);
//    }
//
//    @Override
//    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
//        return size() > capacity;
//    }
//
//    public static void main(String[] args) {
//        LRUCache cache = new LRUCache(3);
//        cache.put(1, 1);
//        cache.put(2, 2);
//        cache.put(3, 3);
//        System.out.println(cache);
//        cache.get(1);
//        cache.get(3);
//        cache.put(4, 4);
//        System.out.println(cache);
//    }
//}
