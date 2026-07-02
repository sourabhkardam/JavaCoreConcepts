package com.corejava.concepts.cache;

import java.util.HashMap;

class LRUCache<K, V> {
	private class Node {
		K key;
		V value;
		Node left;
		Node right;

		public Node(K data, V value) {
			this.key = data;
			this.value = value;
		}
	}

	private Node first;
	private Node last;

	int capacity;

	public LRUCache(int capacity) {
		this.capacity = capacity;
	}

	HashMap<K, Node> hashMap = new HashMap<K, Node>();

	public void add(K key, V value) {
		if (hashMap.isEmpty()) {
			Node newNode = new Node(key, value);
			first = newNode;
			last = newNode;

			hashMap.put(key, newNode);
			return;
		}

		if (hashMap.containsKey(key)) {
			Node node = hashMap.get(key);
			node.value = value;
			return;
		}

		if (hashMap.size() < capacity) {
			addNodeAtEnd(key, value);
		} else {
			hashMap.remove(first.key);
			Node temp = first.right;
			first.right = null;
			first = temp;
			first.left = null;

			addNodeAtEnd(key, value);
		}

	}

	public V get(K key) {
		if (!hashMap.containsKey(key)) {
			return null;
		}

		Node node = hashMap.get(key);

		// If element is at end or only one element present, do nothing
		if (node.right == null || (node.left == null && node.right == null)) {
			return node.value;
		}

		// If element is present at start, move it to end.
		if (node.left == null) {
			first = node.right;

			first.left = null;

			last.right = node;

			node.left = last;
			node.right = null;

			last = node;
		} else {
			// If element in middle, move it to end.
			Node leftNode = node.left;
			Node rightNode = node.right;
			leftNode.right = rightNode;
			rightNode.left = leftNode;

			last.right = node;

			node.left = last;
			node.right = null;

			last = node;
		}
		return node.value;
	}

	private void addNodeAtEnd(K key, V value) {
		Node newNode = new Node(key, value);
		newNode.left = last;
		last.right = newNode;
		last = newNode;
		hashMap.put(key, newNode);
	}

	@Override
	public String toString() {
		Node temp = first;
		if (temp == null) {
			return "LRUCache is empty";
		}

		StringBuilder builder = new StringBuilder();
		builder.append("(" + temp.key + ", " + temp.value + ")");
		temp = temp.right;

		while (temp != null) {
			builder.append(", ").append("(" + temp.key + ", " + temp.value + ")");
			temp = temp.right;
		}
		return "LRUCache [" + builder.toString() + "]";
	}

}

public class LRUCacheImpl {

	public static void main(String[] args) {
		LRUCache<Integer, String> lruCache = new LRUCache<Integer, String>(4);
		lruCache.add(10, "temp");
		lruCache.add(20, "temp");
		lruCache.add(30, "temp");
		lruCache.add(40, "temp");
		System.out.println(lruCache);

		lruCache.get(10);
		System.out.println(lruCache);

		lruCache.add(30, "updatedtemp");
		System.out.println(lruCache);

	}

}
