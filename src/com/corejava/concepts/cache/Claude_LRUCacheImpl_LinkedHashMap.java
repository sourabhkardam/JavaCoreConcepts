package com.corejava.concepts.cache;

import java.util.LinkedHashMap;
import java.util.Map;

class LRUCache_Short<K, V> extends LinkedHashMap<K, V> {

	private final int capacity;

	public LRUCache_Short(int capacity) {
		super(capacity, 0.75f, true); // accessOrder=true
		if (capacity <= 0)
			throw new IllegalArgumentException("Capacity must be > 0");
		this.capacity = capacity;
	}

	@Override
	protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
		return size() > capacity;
	}

	public V getEntry(K key) {
		return getOrDefault(key, null);
	}

	public void addEntry(K key, V value) {
		put(key, value);
	}

	@Override
	public String toString() {
		if (isEmpty())
			return "LRUCache is empty";
		StringBuilder sb = new StringBuilder("LRUCache [");
		forEach((k, v) -> sb.append("(").append(k).append(", ").append(v).append("), "));
		sb.setLength(sb.length() - 2);
		return sb.append("]").toString();
	}
}

public class Claude_LRUCacheImpl_LinkedHashMap {
	public static void main(String[] args) {
		LRUCache_Short<Integer, String> lruCache = new LRUCache_Short<>(4);
		lruCache.addEntry(10, "temp");
		lruCache.addEntry(20, "temp");
		lruCache.addEntry(30, "temp");
		lruCache.addEntry(40, "temp");
		System.out.println(lruCache); // [(10,temp),(20,temp),(30,temp),(40,temp)]

		lruCache.getEntry(10);
		System.out.println(lruCache); // [(20,temp),(30,temp),(40,temp),(10,temp)]

		lruCache.addEntry(30, "updatedtemp");
		System.out.println(lruCache); // [(20,temp),(40,temp),(10,temp),(30,updatedtemp)]

		lruCache.addEntry(50, "new"); // evicts 20
		System.out.println(lruCache); // [(40,temp),(10,temp),(30,updatedtemp),(50,new)]
	}
}
