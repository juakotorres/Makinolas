package cl.makinolas.atk.start;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import cl.makinolas.atk.actors.Background;
import cl.makinolas.atk.actors.Title;

import cl.makinolas.atk.screen.GameScreen;

public class StartingJourneyStage extends Stage{

  private Game myGame;
  private TextActor actualText;
  private int sceneCount;
  
  public StartingJourneyStage(Viewport v, GameScreen actualScreen, Game myGame) {
    super(v);
    
    sceneCount = 0;
    this.myGame = myGame;
    addActor(new Background("Background/negro.jpg", getCamera()));
    addActor(new Title("CharacterImages/startMap.png",220, 300));
    addActor(new Title("CharacterImages/Sensei.png",440, 300));

    //MobileGroup group = new MobileGroup(Gdx.app.getType() == Application.ApplicationType.Android);
    Gdx.input.setInputProcessor(this);
    
    actualText = new TextActor(GameText.firstStartText);
    addActor(actualText);
  }
  
  public void act(float delta){
    super.act(delta);
    
    if((Gdx.input.isKeyPressed(Keys.Z) || bottomPressed()) && actualText.hasFinished() && sceneCount == 1){
    //if(actualText.hasFinished() && sceneCount == 1){
      GameScreen gameScreen = new GameScreen(myGame);
      gameScreen.setStage(new BoyOrGirlStage(new FitViewport(640,480), gameScreen, myGame));
      myGame.setScreen(gameScreen);
    }
    
    
    if((Gdx.input.isKeyPressed(Keys.Z) || bottomPressed()) && actualText.hasFinished()){
    //if(actualText.hasFinished()){
      sceneCount = 1;
      actualText.remove();
      actualText = new TextActor(GameText.secondStartText);    
      addActor(actualText);
    }
    
  }

  private boolean bottomPressed() {
    return Gdx.input.isTouched() && Gdx.input.getY()>Gdx.graphics.getHeight()*0.7;
  }

}
