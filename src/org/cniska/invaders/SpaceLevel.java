package org.cniska.invaders;

import org.cniska.phaser.core.GameView;
import org.cniska.phaser.core.Updateable;
import org.cniska.phaser.event.Event;
import org.cniska.phaser.node.EntityListener;
import org.cniska.phaser.scene.Level;
import org.cniska.phaser.scene.World;

public class SpaceLevel extends Level implements EntityListener {

	protected int alienRowCount;
	protected int alienColCount;
	protected int alienCount = 0;

	/**
	 * Creates a new level.
	 */
	public SpaceLevel(GameView view, World world) {
		super(view, world);
	}

	@Override
	public void init() {
		super.init();

		if (id > 0) {
			SpaceData.SpaceLevelData data = (SpaceData.SpaceLevelData) view.getData().getLevel(id);

			if (data != null) {
				alienRowCount = data.alienRowCount;
				alienColCount = data.alienColCount;

				int offset, sx, sy, x, y;

				offset = 30;
				sx = (view.getWidth() / 2) - ((alienColCount * offset) / 2) + ((offset - 20) / 2);
				sy = 100;

				x = sx;
				y = sy;

				for (int i = 0; i < alienRowCount; i++) {
					for (int j = 0; j < alienColCount; j++) {
						Alien alien = (Alien) world.createActor(2);
						alien.position(x, y);
						alien.setVx(1);
						alien.setSx(x); // set starting x-coordinate
						alien.attach(this); // listen to alien events
						world.addActor(alien);

						if (i == (alienRowCount - 1)) {
							alien.setTorpedos(true);
						}

						alienCount++;
						x += offset;
					}
					x = sx;
					y += offset;
				}
			}
		}
	}

	@Override
	public void update(Updateable parent) {
		super.update(parent);

		if (alienCount == 0) {
			end();
		}
	}

	@Override
	public void onEntityRemove(Event event) {
		event.getSource().detach(this);
		alienCount--;
	}
}
