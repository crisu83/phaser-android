package org.cniska.phaser.util;

import org.cniska.phaser.core.Updateable;

import java.util.ArrayList;

public class List<E> implements Updateable {

	// Member variables
	// ----------------------------------------

	protected ArrayList<E> objects;
	protected ArrayList<E> additions;
	protected ArrayList<E> removals;

	// Methods
	// ----------------------------------------

	/**
	 * Creates a new list.
	 */
	public List() {
		objects = new ArrayList<E>();
		additions = new ArrayList<E>();
		removals = new ArrayList<E>();
	}

	/**
	 * Adds an object to the list.
	 *
	 * @param object The object to add.
	 */
	public void add(E object) {
		additions.add(object);
	}

	/**
	 * Returns the object with the given index.
	 *
	 * @param index The index.
	 * @return The object.
	 */
	public E get(int index) {
		return index < objects.size() ? objects.get(index) : null;
	}

	/**
	 * Removes an object from the list.
	 *
	 * @param object The object to remove.
	 */
	public void remove(E object) {
		removals.add(object);
	}

	/**
	 * Merges the list with another list.
	 *
	 * @param list The other list.
	 */
	public void merge(List<E> list) {
		for (int i = 0, len = list.size(); i < len; i++) {
			add(list.get(i));
		}
	}

	/**
	 * Returns the size of the list.
	 *
	 * @return The length.
	 */
	public int size() {
		return objects.size();
	}

	/**
	 * Returns whether the list is empty.
	 *
	 * @return The result.
	 */
	public boolean isEmpty() {
		return objects.isEmpty();
	}

	/**
	 * Applies changes to the list by adding and removing objects.
	 */
	protected void applyChanges() {
		final int additionCount = additions.size();

		if (additionCount > 0) {
			for (int i = 0; i < additionCount; i++) {
				objects.add(additions.get(i));
			}

			additions.clear();
		}

		final int removalCount = removals.size();

		if (removalCount > 0) {
			for (int i = 0; i < removalCount; i++) {
				objects.remove(removals.get(i));
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
