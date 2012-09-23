package org.cniska.phaser.core;

public interface Updateable {

	/**
	 * Updates the node.
	 *
	 * @param parent The parent node.
	 */
	public void update(Updateable parent);
}
