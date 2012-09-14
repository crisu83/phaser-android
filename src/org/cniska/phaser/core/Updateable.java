package org.cniska.phaser.core;

import org.cniska.phaser.node.Node;

public interface Updateable {

	/**
	 * Updates the node.
	 *
	 * @param parent The parent node.
	 */
	public void update(Updateable parent);
}
