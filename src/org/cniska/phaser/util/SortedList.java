package org.cniska.phaser.util;

import org.cniska.phaser.core.Updateable;

import java.util.Collections;
import java.util.Comparator;

public class SortedList<E> extends List<E> {

	protected Comparator<E> comparator;
	protected boolean sorted = true;

	public SortedList(Comparator<E> comparator) {
		this.comparator = comparator;
	}

	public void sort() {
		Collections.sort(objects, comparator);
	}

	@Override
	public void add(E object) {
		super.add(object);
		sorted = false;
	}

	@Override
	public void update(Updateable parent) {
		super.update(parent);

		if (!sorted) {
			sort();
			sorted = true;
		}
	}
}
