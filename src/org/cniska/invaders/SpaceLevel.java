package org.cniska.invaders;

import org.cniska.phaser.core.GameView;
import org.cniska.phaser.core.Updateable;
import org.cniska.phaser.event.Event;
import org.cniska.phaser.node.Entity;
import org.cniska.phaser.node.EntityListener;
import org.cniska.phaser.scene.Level;
import org.cniska.phaser.scene.World;

import java.util.ArrayList;

public class SpaceLevel extends Level implements EntityListener {

	protected int alienRowCount;
	protected int alienColCount;
	protected int alienCount = 0;
	protected boolean[] deadAliens;
	protected ArrayList<Alien> aliens;

	/**
	 * Creates a new level.
	 */
	public SpaceLevel(GameView view, World world) {
		super(view, world);
	}

	protected boolean alienBlocked(Alien alien) {
		for (int i = 0; i < deadAliens.length; i++) {
			if (i == (alien.getIndex() + alienColCount) && deadAliens[i]) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void init() {
		super.init();

		if (id > 0) {
			SpaceData.SpaceLevelData data = (SpaceData.SpaceLevelData) view.getData().getLevel(id);

			if (data != null) {
				alienRowCount = data.alienRowCount;
				alienColCount = data.alienColCount;
				int count = alienRowCount * alienColCount;
				aliens = new ArrayList<Alien>(count);
				deadAliens = new boolean[count];

				for (int i = 0; i < deadAliens.length; i++) {
					deadAliens[i] = false;
				}

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
						alien.setIndex(j + (i * alienColCount));
						alien.attach(this); // listen to alien events
						world.addActor(alien);
						aliens.add(alien);

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

		for (int i = 0, len = aliens.size(); i < len; i++) {
			Alien alien = aliens.get(i);
			if (!alienBlocked(alien)) {
				alien.setTorpedos(true);
			}
		}

		if (alienCount == 0) {
			end();
		}
	}

	@Override
	public void onEntityRemove(Event event) {
		Entity entity = (Entity) event.getSource();
		if (entity instanceof Alien) {
			Alien alien = (Alien) entity;
			entity.detach(this);
			deadAliens[alien.getIndex()] = true;
			alienCount--;
		}
	}
}
