package org.cniska.phaser.util;

import java.util.ArrayList;

public abstract class Pool {

    protected ArrayList<Poolable> items;

    public Pool() {
    }

    public Poolable allocate() {
        final int index = items.size() - 1;
        Poolable item = items.get(index);
        items.remove(index);
        return item;
    }

    public void release(Poolable item) {
        item.reset();
        items.add(item);
    }

    public abstract void fill(int size);
}
