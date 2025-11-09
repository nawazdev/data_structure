package com.coffee.data_structure.lld.lru.inbuild;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity; // Maximum number of entries allowed

    public LRUCache(int capacity) {
        // true = access order (recently accessed items go to end)
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        // Evict when size exceeds capacity
        return size() > capacity;
    }

    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(3);

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        System.out.println(cache); // {1=A, 2=B, 3=C}

        cache.get(1); // Access 1 → moves to end
        cache.put(4, "D"); // Evicts least recently used (key 2)

        System.out.println(cache); // {3=C, 1=A, 4=D}
    }
}