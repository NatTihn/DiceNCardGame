package io.github.gamblerDungeon;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
	private SpriteBatch batch;
	private Stage stage;

	private Card card;

	@Override
	public void create() {
		Gdx.graphics.setWindowedMode(Config.width, Config.height);
		stage = new Stage(new FitViewport(Config.width, Config.height));
		batch = new SpriteBatch();
		batch.getProjectionMatrix().setToOrtho2D(0, 0, Config.width, Config.height);

		card = new Card(0);
	}

	public void update(float dt) {
		Input.updateMouse(Gdx.input.getX(), Gdx.input.getY(), stage.getCamera());
	}

	public void render() {
		final float dt = Gdx.graphics.getDeltaTime();
		update(dt);
		ScreenUtils.clear(0.1f, 0.1f, 0.1f, 1);

		batch.begin();
		card.draw(batch);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public void resize(int width, int height) {
		// stage.getViewport().update(width, height);
		// stage = new Stage(new FitViewport(width, height));
		stage.getViewport().update(width, height, true);
	}
}
