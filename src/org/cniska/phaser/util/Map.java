package org.cniska.phaser.util;

import org.cniska.phaser.core.Updateable;

import java.util.HashMap;

public class Map<K, V> implements Updateable {

	protected HashMap<K, V> objects;
	protected HashMap<K, V> additions;
	protected HashMap<K, V> removals;

	public Map() {
		objects = new HashMap<K, V>();
		additions = new HashMap<K, V>();
		removals = new HashMap<K, V>();
	}

	public void add(K key, V value) {
		additions.put(key, value);
	}

	public V get(K key) {
		return objects.containsKey(key) ? objects.get(key) : null;
	}

	public void remove(K key) {
		V value = get(key);

		if (value != null) {
			removals.put(key, value);
		}
	}

	public int size() {
		return objects.size();
	}

	public boolean isEmpty() {
		return objects.isEmpty();
	}

	protected void applyChanges() {
		final int additionCount = additions.size();

		if (additionCount > 0) {
			for (HashMap.Entry<K, V> entry : additions.entrySet()) {
				objects.put(entry.getKey(), entry.getValue());
			}

			additions.clear();
		}

		final int removalCount = removals.size();

		if (removalCount > 0) {
			for (K key : removals.keySet()) {
				objects.remove(key);
			}

			removals.clear();
		}
	}

	@Override
	public void update(Updateable parent) {
		applyChanges();
	}
}
