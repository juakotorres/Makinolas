package cl.makinolas.atk.stages;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import cl.makinolas.atk.actors.Background;
import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.Title;
import cl.makinolas.atk.actors.ui.MainBar;
import cl.makinolas.atk.actors.ui.MobileGroup;
import cl.makinolas.atk.screen.GameScreen;
import cl.makinolas.atk.utils.SaveManager;

public class LoadStage extends AbstractStage{
  

  private World suMundo;
  private float accumulator;
  private final float frameTime = 1 / 300f;
  private Group ground, mons, ui;
  private Game myGame;
  
  private OrthographicCamera camera;
  private Box2DDebugRenderer renderer;
  
  public LoadStage(Viewport v, GameScreen actualScreen, Game myGame) {
    super(v);
    
    this.myGame = myGame;
    myScreen = actualScreen;
    suMundo = new World(new Vector2(0, -10), true);
    addActor(new Background("Background/Wood.png", getCamera()));
    addActor(new Title("Background/LoadFiles.png",220 ,400));
    
    TextButton game1Button = new TextButton("Game 1",  new Skin(Gdx.files.internal("Data/uiskin.json")));
    game1Button.setPosition(280, 240);
    game1Button.addListener(new ClickListener(){
        @Override
        public void clicked(InputEvent event, float x, float y) {
            loadGame("1");
        }
    });
    addActor(game1Button);
    
    ground = new Group();
    addActor(ground);
    mons = new Group();
    addActor(mons);
    ui = new Group();
    addActor(ui);

    MobileGroup group = new MobileGroup(Gdx.app.getType() == Application.ApplicationType.Android);
    Gdx.input.setInputProcessor(this);

    ui.addActor(group);    
    
    accumulator = 0;
    renderer = new Box2DDebugRenderer();
    setupCamera();
  }
  
  protected void loadGame(String number) {
    SaveManager.getInstance().loadData("ATK.sav");  
    Hero.getInstance().reset();
    MainBar.getInstance().reset();
    
    GameScreen gameScreen = new GameScreen(myGame);
    gameScreen.setStage(new GameStage(new FitViewport(640,480), gameScreen, myGame, Levels.LEVEL1));
    myGame.setScreen(gameScreen);
  }

  public void act(float delta){
    super.act(delta);
    
    accumulator += delta;
    elapsedTime += delta;
    
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
