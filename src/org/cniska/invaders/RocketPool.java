package org.cniska.invaders;

import org.cniska.phaser.core.GameView;
import org.cniska.phaser.event.Event;
import org.cniska.phaser.node.EntityListener;
import org.cniska.phaser.scene.World;
import org.cniska.phaser.util.Pool;
import org.cniska.phaser.util.Poolable;

import java.util.ArrayList;

public class RocketPool extends Pool implements EntityListener {

    protected GameView view;
    protected World world;

    public RocketPool(GameView view, World world) {
        super();
        this.view = view;
        this.world = world;
    }

    @Override
    public void fill(int size) {
        items = new ArrayList<Poolable>(size);

        for (int i = 0; i < size; i++) {
            items.add(new Rocket(view, world));
        }
    }

    @Override
    public void onEntityRemove(Event event) {
        release((Poolable) event.getSource());
    }
}
