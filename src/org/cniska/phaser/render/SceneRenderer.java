package org.cniska.phaser.render;

import android.graphics.Canvas;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.node.Node;
import org.cniska.phaser.node.Scene;

import java.util.Vector;

public class SceneRenderer extends Node implements Renderer {

	// Member variables
	// ----------------------------------------

	protected Vector<Scene> scenes;

	// Methods
	// ----------------------------------------

	/**
	 * Creates a new renderer.
	 */
	public SceneRenderer(GameView view) {
		super(view);
		scenes = new Vector<Scene>();
	}

	public void add(Scene scene) {
		scenes.add(scene);
	}

	// Overridden methods
	// ----------------------------------------

	@Override
	public void render(Canvas canvas) {
		for (Scene scene : scenes) {
			SpriteRenderer renderer = scene.getRenderer();
			renderer.render(canvas);
		}
	}
}
