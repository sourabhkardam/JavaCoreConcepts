package com.corejava.concepts.collections.hashmap;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * This map doesn't support collison logic.
 */
class CustomHashMap<K, V> implements Map<K, V> {
	private int size;

	private class Node<K, V> {
		private int hashCode;
		private K key;
		private V value;
		private Node next;

		public Node(int hashCode, K key, V value) {
			super();
			this.hashCode = hashCode;
			this.key = key;
			this.value = value;
		}
	}

	private Node[] buckets;

	public CustomHashMap(int capacity) {
		buckets = new Node[capacity];
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	@Override
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public V get(Object key) {
		int index = getBucketIndex((K) key);

		Node temp = buckets[index];
		if (temp == null)
			return null;

		while (temp != null) {
			if (((V) key).equals(temp.key)) {
				return (V) temp.value;
			}
			temp = temp.next;
		}

		return null;
	}

	private int getBucketIndex(K key) {
//		int index = key.hashCode() % buckets.length;
		return getHashCode(key) & (buckets.length - 1);
	}

	private int getHashCode(K key) {
		return key.hashCode();
	}

	@Override
	public V put(K key, V value) {
		int index = getBucketIndex((K) key);

		Node temp = buckets[index];
		if (temp == null) {
			buckets[index] = new Node(getHashCode(key), key, value);
			size++;
			return value;
		}

		boolean isPresent = false;
		Node last = temp;
		while (temp != null) {
			// key already exist, update it
			if (key.equals(temp.key)) {
				buckets[index].value = value;
				isPresent = true;
				break;
			}

			if (temp.next == null) {
				last = temp;
			}

			temp = temp.next;
		}

		if (!isPresent) {
			last.next = new Node(getHashCode(key), key, value);
			size++;
		}

		return value;
	}

	@Override
	public V remove(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("{");
		Arrays.stream(buckets).forEach(e -> {
			if (e != null)
				result.append("[" + e.key + ", " + e.value + "], ");
		});
		return result.substring(0, result.length() - 2).concat("}");

	}

}

public class CustomizedHashMap {

	public static void main(String[] args) {
		CustomHashMap<Integer, String> empMap = new CustomHashMap<Integer, String>(10);

		// Put operation
		empMap.put(11253, "Sourabh");
//		empMap.put(11251, "Rohit");
		empMap.put(11250, "Mohit");
		empMap.put(11253, "Sourabh Kumar");
		empMap.put(10776, "Sourabh");

		// Get operation
		System.out.println(empMap.get(111));
		System.out.println(empMap.get(11253));
		System.out.println(empMap.get(11251));

		// Check size
		System.out.println(empMap.size());

		System.out.println(empMap);
	}

}
