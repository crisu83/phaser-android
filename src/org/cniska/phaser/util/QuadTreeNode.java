package org.cniska.phaser.util;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import org.cniska.phaser.debug.Debuggable;

import java.util.ArrayList;

public class QuadTreeNode implements Debuggable {

	public static final int QUAD_NORTHWEST = 0;
	public static final int QUAD_NORTHEAST = 1;
	public static final int QUAD_SOUTHWEST = 2;
	public static final int QUAD_SOUTHEAST = 3;

	protected int x, y, width, height;
	protected ArrayList<QuadTreeable> items;
	protected ArrayList<QuadTreeable> overlaps;
	protected ArrayList<QuadTreeNode> children;
	protected int depth = 0;
	protected int maxChildren;
	protected int maxDepth;

	public QuadTreeNode(int x, int y, int width, int height, int depth, int maxChildren, int maxDepth) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.depth = depth;
		this.maxChildren = maxChildren;
		this.maxDepth = maxDepth;
		items = new ArrayList<QuadTreeable>();
		overlaps = new ArrayList<QuadTreeable>();
		children = new ArrayList<QuadTreeNode>();
	}

	public void add(QuadTreeable item) {
		if (children.isEmpty()) {
			items.add(item);
			int len = items.size();

			if (len > maxChildren && depth < maxDepth) {
				divide();

				for (int i = 0; i < len; i++) {
					add(items.get(i));
				}

				items.clear();
			}
		} else {
			int index = find(item);
			QuadTreeNode node = children.get(index);

			if (item.getX() >= node.getX()
					&& item.getY() >= node.getY()
					&& item.x2() <= node.x2()
					&& item.y2() <= node.y2()) {
				children.get(index).add(item);
			} else {
				overlaps.add(item);
			}
		}
	}

	public ArrayList<QuadTreeable> getNeighbours(QuadTreeable item) {
		ArrayList<QuadTreeable> items = new ArrayList<QuadTreeable>();

		if (!children.isEmpty()) {
			ArrayList<QuadTreeable> neighbours = children.get(find(item)).getNeighbours(item);
			for (int j = 0, len2 = neighbours.size(); j < len2; j++) {
				items.add(neighbours.get(j));
			}
		}

		for (int i = 0, len = overlaps.size(); i < len; i++) {
			items.add(overlaps.get(i));
		}

		return items;
	}

	public void flush() {
		for (int i = 0, len = children.size(); i < len; i++) {
			children.get(i).flush();
		}

		items.clear();
		overlaps.clear();
		children.clear();
	}

	protected int find(QuadTreeable item) {
		boolean left, top;

		left = item.getX() < (x + (width / 2));
		top = item.getY() < (y + (height / 2));

		if (left) {
			return top ? QUAD_NORTHWEST : QUAD_SOUTHWEST;
		} else {
			return top ? QUAD_NORTHEAST : QUAD_SOUTHEAST;
		}
	}

	protected void divide() {
		int w, h, d;

		w = width / 2;
		h = height / 2;
		d = depth + 1;

		children.add(new QuadTreeNode(x, y, w, h, d, maxChildren, maxDepth));
		children.add(new QuadTreeNode(x + w, y, w, h, d, maxChildren, maxDepth));
		children.add(new QuadTreeNode(x, y + h, w, h, d, maxChildren, maxDepth));
		children.add(new QuadTreeNode(x + w, y + h, w, h, d, maxChildren, maxDepth));
	}

	public int x2() {
		return x + width;
	}

	public int y2() {
		return y + height;
	}

	@Override
	public void debug(Debuggable parent, Canvas canvas) {
		Paint paint = new Paint();
		paint.setColor(Color.DKGRAY);
		paint.setStyle(Paint.Style.STROKE);
		canvas.drawRect(x, y, x2(), y2(), paint);

		for (int i = 0, len = children.size(); i < len; i++) {
			children.get(i).debug(this, canvas);
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
