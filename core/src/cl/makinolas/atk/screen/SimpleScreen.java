package cl.makinolas.atk.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;

import cl.makinolas.atk.stages.OptionsStage;

public abstract class SimpleScreen implements Screen {

    protected Stage stage;
    protected Game myGame;
    protected float brightnessModifier; // 0 = bright, 0.5 = dark
    
    public SimpleScreen() {}

    public SimpleScreen(Game g, Stage s){
        myGame = g;
        stage = s;
        Gdx.input.setInputProcessor(stage);
        brightnessModifier = OptionsStage.getBrightness();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
        
        // FIXME El setColor recibe un alpha entre 0 (brillante) y 0.5 (oscuro).
        // FIXME MapScreen no lo lee bien, arreglar eso.
        // FIXME En options debería modificar esto.
        Gdx.gl.glEnable(GL20.GL_BLEND);
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, brightnessModifier);
        shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
    
    public void setBrightness(float modifier) {
    	this.brightnessModifier = modifier;
    }
}
