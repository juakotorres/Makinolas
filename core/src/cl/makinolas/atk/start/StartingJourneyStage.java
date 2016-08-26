package cl.makinolas.atk.start;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import cl.makinolas.atk.actors.Background;
import cl.makinolas.atk.actors.Title;
import cl.makinolas.atk.actors.ui.MobileGroup;
import cl.makinolas.atk.screen.GameScreen;
import cl.makinolas.atk.stages.AbstractStage;

public class StartingJourneyStage extends AbstractStage{

  private World suMundo;
  private float accumulator;
  private final float frameTime = 1 / 300f;
  private Group ground, mons, ui;
  private Game myGame;
  
  private OrthographicCamera camera;
  private Box2DDebugRenderer renderer;
  private TextActor actualText;
  private int sceneCount;
  
  public StartingJourneyStage(Viewport v, GameScreen actualScreen, Game myGame) {
    super(v);
    
    sceneCount = 0;
    this.myGame = myGame;
    myScreen = actualScreen;
    suMundo = new World(new Vector2(0, -10), true);
    addActor(new Background("Background/negro.jpg", getCamera()));
    addActor(new Title("CharacterImages/startMap.png",220, 300));
    addActor(new Title("CharacterImages/Sensei.png",440, 300));
    
    ground = new Group();
    addActor(ground);
    mons = new Group();
    addActor(mons);
    ui = new Group();
    addActor(ui);

    MobileGroup group = new MobileGroup(Gdx.app.getType() == Application.ApplicationType.Android);
    Gdx.input.setInputProcessor(this);

    ui.addActor(group);    
    
    actualText = new TextActor(GameText.firstStartText);
    addActor(actualText);
    accumulator = 0;
    renderer = new Box2DDebugRenderer();
    setupCamera();
  }
  
  public void act(float delta){
    super.act(delta);
    
    accumulator += delta;
    elapsedTime += delta;
    
    if(Gdx.input.isKeyPressed(Keys.Z) && actualText.hasFinished() && sceneCount == 1){
      GameScreen gameScreen = new GameScreen(myGame);
      gameScreen.setStage(new ChooseStage(new FitViewport(640,480), gameScreen, myGame));
      myGame.setScreen(gameScreen);
    }
    
    
    if(Gdx.input.isKeyPressed(Keys.Z) && actualText.hasFinished()){
      sceneCount = 1;
      actualText.remove();
      actualText = new TextActor(GameText.secondStartText);    
      addActor(actualText);
    }
    
    while(accumulator >= frameTime){
      suMundo.step(frameTime, 6, 2);
      accumulator -= frameTime;
    }
    
  }
  
  @Override
  public void draw() {
      super.draw();
      //bar.drawCustom(getBatch(),getCamera().position.x,getCamera().position.y); //Custom draw for MainBar
      camera.update();
      renderer.render(suMundo, camera.combined);
  }
  
  private void setupCamera() {
    camera = new OrthographicCamera(32, 24);
    camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
    camera.update();
  }
  

  public void changeCamera(float x, float y){
    camera.position.set(x, y, 0);
    getCamera().position.set(x * 20, y * 20, 0);
    getCamera().update();    
    camera.update();
  }
}
