package cl.makinolas.atk.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameScreen extends SimpleScreen {
  
  private Stage stage;
  private Game myGame;
  
  public GameScreen(Game myGame) {
    this.myGame = myGame;
  }

  @Override
  public void show() {
  }

  @Override
  public void render(float delta) {
    //Clear the screen
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    //Update the stage
    stage.draw();
    stage.act(delta);
    
    // FIXME Duplicación de código, debería heredar el render de SimpleScreen
    Gdx.gl.glEnable(GL20.GL_BLEND);
    ShapeRenderer shapeRenderer = new ShapeRenderer();
    shapeRenderer.begin(ShapeType.Filled);
    shapeRenderer.setColor(0, 0, 0, super.brightnessModifier);
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

  public void mainMenu() {
    myGame.setScreen(new MenuScreen(myGame));
  }

  public void setStage(Stage nextStage) {
    stage = nextStage;
    Gdx.input.setInputProcessor(stage);
  }
  
}
