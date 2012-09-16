package org.cniska.phaser.util;

import android.graphics.Canvas;
import org.cniska.phaser.debug.Debuggable;

import java.util.ArrayList;

public class QuadTree implements Debuggable {

	protected QuadTreeNode treeRoot;

	public QuadTree(int x, int y, int x2, int y2, int maxChildren, int maxDepth) {
		treeRoot = new QuadTreeNode(x, y, x2, y2, 0, maxChildren, maxDepth);
	}

	public void add(QuadTreeable item) {
		treeRoot.add(item);
	}

	public ArrayList<QuadTreeable> getNeighbours(QuadTreeable item) {
		return treeRoot.getNeighbours(item);
	}

	public void flush() {
		treeRoot.flush();
	}

	@Override
	public void debug(Debuggable parent, Canvas canvas) {
		treeRoot.debug(this, canvas);
	}
}
