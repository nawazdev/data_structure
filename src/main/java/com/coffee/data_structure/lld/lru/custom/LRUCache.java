package com.coffee.data_structure.lld.lru.custom;

import java.util.HashMap;
import java.util.Map;

class LRUCache {
    class Node {
        int key, value;
        Node prev, next;

        Node(int k, int v) {
            key = k;
            value = v;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> map;
    private final Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();

        // Dummy head & tail for simplicity
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node node = map.get(key);
        moveToHead(node); // recently used
        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value; // update existing
            moveToHead(node);
        } else {
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            addNode(newNode);

            if (map.size() > capacity) {
                Node lru = popTail();
                map.remove(lru.key);
            }
        }
    }

    // Helper methods
    private void addNode(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addNode(node);
    }

    private Node popTail() {
        Node res = tail.prev;
        removeNode(res);
        return res;
    }

    // Display cache state (for debugging)
    public void displayCache() {
        Node curr = head.next;
        System.out.print("Cache State: ");
        while (curr != tail) {
            System.out.print("(" + curr.key + ":" + curr.value + ") ");
            curr = curr.next;
        }
        System.out.println();
    }

    // ---- MAIN METHOD FOR DEMO ----
    public static void main(String[] args) {
        System.out.println("=== LRU Cache Demo ===");

        LRUCache cache = new LRUCache(2);

        cache.put(1, 10);
        System.out.println("Put(1, 10)");
        cache.displayCache();

        cache.put(2, 20);
        System.out.println("Put(2, 20)");
        cache.displayCache();

        System.out.println("Get(1): " + cache.get(1)); // returns 10
        cache.displayCache();

        cache.put(3, 30); // removes key 2
        System.out.println("Put(3, 30)");
        cache.displayCache();

        System.out.println("Get(2): " + cache.get(2)); // returns -1 (evicted)
        cache.displayCache();

        cache.put(4, 40); // removes key 1
        System.out.println("Put(4, 40)");
        cache.displayCache();

        System.out.println("Get(1): " + cache.get(1)); // -1
        System.out.println("Get(3): " + cache.get(3)); // 30
        System.out.println("Get(4): " + cache.get(4)); // 40
        cache.displayCache();
    }
}
