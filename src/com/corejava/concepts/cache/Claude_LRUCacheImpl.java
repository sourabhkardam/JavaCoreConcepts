package com.corejava.concepts.cache;

import java.util.HashMap;

class Claude_LRUCache<K, V> {

	private class Node {
		K key;
		V value;
		Node left;
		Node right;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	private Node first; // LRU end (eviction side)
	private Node last; // MRU end (insertion side)
	private final int capacity;
	private final HashMap<K, Node> hashMap = new HashMap<>();

	public Claude_LRUCache(int capacity) {
		if (capacity <= 0)
			throw new IllegalArgumentException("Capacity must be > 0");
		this.capacity = capacity;
	}

	public void add(K key, V value) {
		if (hashMap.containsKey(key)) {
			// FIX: update value AND promote to MRU end
			Node node = hashMap.get(key);
			node.value = value;
			moveToEnd(node);
			return;
		}

		if (hashMap.size() >= capacity) {
			evictLRU();
		}

		addNodeAtEnd(key, value);
	}

	public V get(K key) {
		if (!hashMap.containsKey(key)) {
			return null;
		}

		Node node = hashMap.get(key);
		moveToEnd(node); // FIX: always promote uniformly
		return node.value;
	}

	// Moves an existing node to the MRU end
	private void moveToEnd(Node node) {
		if (node == last)
			return; // already at MRU end, nothing to do

		// Detach node from its current position
		if (node == first) {
			first = node.right;
			first.left = null;
		} else {
			node.left.right = node.right;
			node.right.left = node.left;
		}

		// Attach at MRU end
		node.left = last;
		node.right = null;
		last.right = node;
		last = node;
	}

	// Evicts the LRU node (from the front)
	private void evictLRU() {
		hashMap.remove(first.key);
		if (first == last) {
			// Only one element
			first = null;
			last = null;
		} else {
			Node temp = first;
			first = temp.right;
			first.left = null;
			temp.right = null;
		}
	}

	private void addNodeAtEnd(K key, V value) {
		Node newNode = new Node(key, value);
		if (last == null) {
			// Empty cache
			first = newNode;
			last = newNode;
		} else {
			newNode.left = last;
			last.right = newNode;
			last = newNode;
		}
		hashMap.put(key, newNode);
	}

	@Override
	public String toString() {
		if (first == null)
			return "LRUCache is empty";

		StringBuilder builder = new StringBuilder("LRUCache [");
		Node temp = first;
		while (temp != null) {
			builder.append("(").append(temp.key).append(", ").append(temp.value).append(")");
			if (temp.right != null)
				builder.append(", ");
			temp = temp.right;
		}
		return builder.append("]").toString();
	}
}

public class Claude_LRUCacheImpl {
	public static void main(String[] args) {
		Claude_LRUCache<Integer, String> lruCache = new Claude_LRUCache<>(4);
		lruCache.add(10, "temp");
		lruCache.add(20, "temp");
		lruCache.add(30, "temp");
		lruCache.add(40, "temp");
		System.out.println(lruCache); // [10, 20, 30, 40]

		lruCache.get(10); // 10 becomes MRU
		System.out.println(lruCache); // [20, 30, 40, 10]

		lruCache.add(30, "updatedtemp"); // 30 updated + promoted
		System.out.println(lruCache); // [20, 40, 10, 30]

		lruCache.add(50, "new"); // evicts 20 (LRU)
		System.out.println(lruCache); // [40, 10, 30, 50]
	}
}