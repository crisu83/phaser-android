package org.cniska.phaser.util;

import org.cniska.phaser.core.Updateable;

import java.util.ArrayList;

public class List<E> implements Updateable {

	protected ArrayList<E> objects;
	protected ArrayList<E> additions;
	protected ArrayList<E> removals;

	public List() {
		objects = new ArrayList<E>();
		additions = new ArrayList<E>();
		removals = new ArrayList<E>();
	}

	public void add(E object) {
		additions.add(object);
	}

	public E get(int index) {
		return index < objects.size() ? objects.get(index) : null;
	}

	public void remove(E object) {
		removals.add(object);
	}

	public void merge(List<E> list) {
		for (int i = 0, len = list.size(); i < len; i++) {
			add(list.get(i));
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

	@Override
	public void update(Updateable parent) {
		applyChanges();
	}
}
