package org.cniska.phaser.util;

import org.cniska.phaser.core.Updateable;

import java.util.HashMap;

public class Map<K, V> implements Updateable {

	// Member variables
	// ----------------------------------------

	protected HashMap<K, V> objects;
	protected HashMap<K, V> additions;
	protected HashMap<K, V> removals;

	// Methods
	// ----------------------------------------

	/**
	 * Creates a new map.
	 */
	public Map() {
		objects = new HashMap<K, V>();
		additions = new HashMap<K, V>();
		removals = new HashMap<K, V>();
	}

	/**
	 * Adds a value to the map.
	 *
	 * @param key The key.
	 * @param value The value.
	 */
	public void add(K key, V value) {
		additions.put(key, value);
	}

	/**
	 * Returns the value with the given key.
	 *
	 * @param key The key.
	 * @return The value.
	 */
	public V get(K key) {
		return objects.containsKey(key) ? objects.get(key) : null;
	}

	/**
	 * Removes the value with the given key.
	 *
	 * @param key The key.
	 */
	public void remove(K key) {
		V value = get(key);

		if (value != null) {
			removals.put(key, value);
		}
	}

	/**
	 * Returns the size of the map.
	 *
	 * @return The length.
	 */
	public int size() {
		return objects.size();
	}

	/**
	 * Returns whether the map is empty.
	 *
	 * @return The result.
	 */
	public boolean isEmpty() {
		return objects.isEmpty();
	}

	/**
	 * Applies the changes to the map by adding and removing values.
	 */
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

	// Overridden methods
	// ----------------------------------------

	@Override
	public void update(Updateable parent) {
		applyChanges();
	}
}
