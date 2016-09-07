package cl.makinolas.atk.stages;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
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
import cl.makinolas.atk.actors.LoadActor;
import cl.makinolas.atk.actors.Title;
import cl.makinolas.atk.actors.ui.MobileGroup;
import cl.makinolas.atk.screen.GameScreen;
import cl.makinolas.atk.screen.MapScreen;
import cl.makinolas.atk.screen.MenuScreen;

public class LoadStage extends AbstractStage{
  

  private World suMundo;
  private float accumulator;
  private final float frameTime = 1 / 300f;
  private Group ground, mons, ui;
  private Game myGame;
  
  private OrthographicCamera camera;
  private Box2DDebugRenderer renderer;
  private LoadActor[] options;
  private int lastSelected;
  private Title arrow;
  
  public LoadStage(Viewport v, GameScreen actualScreen, Game myGame) {
    super(v);
    
    lastSelected = 0;
    this.myGame = myGame;
    myScreen = actualScreen;
    suMundo = new World(new Vector2(0, -10), true);
    addActor(new Background("Background/Wood.png", getCamera()));
    addActor(new Title("Background/LoadFiles.png",220 ,400));
    
    arrow = new Title("CharacterImages/arrow.png", 50, 300);
    addActor(arrow);
    LoadActor firstSave = new LoadActor("Save 1", "ATK.sav", 80, 250);
    addActor(firstSave);
    
    LoadActor secondSave = new LoadActor("Save 2", "ATK.sav", 80, 140);
    addActor(secondSave);
    
    TextButton menuButton = new TextButton("Back to menu",  new Skin(Gdx.files.internal("Data/uiskin.json")));
    menuButton.setPosition(500, 50);
    menuButton.addListener(new ClickListener(){
        @Override
        public void clicked(InputEvent event, float x, float y) {
          MainMenu();
        }
    });
    
    addActor(menuButton);
    
    options = new LoadActor[]{firstSave,secondSave};
    
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
  
  protected void MainMenu() {
    MenuScreen menuScreen = new MenuScreen(myGame);
    myGame.setScreen(menuScreen);
  }

  protected void loadMap(){
    MapScreen mapScreen = new MapScreen(myGame,new MapStage(new FitViewport(640, 480),myGame));
    myGame.setScreen(mapScreen);
  }


  public void act(float delta){
    super.act(delta);
    
    accumulator += delta;
    elapsedTime += delta;
    
    while(accumulator >= frameTime){
      suMundo.step(frameTime, 6, 2);
      accumulator -= frameTime;
    }
    
    if (Gdx.input.isKeyJustPressed(Keys.LEFT)){
      int last = lastSelected;
      lastSelected = (lastSelected == 0)? 1 : (lastSelected - 1);
      changeArrow(last, lastSelected);
    } if (Gdx.input.isKeyJustPressed(Keys.RIGHT)){
      int last = lastSelected;
      lastSelected = (lastSelected == 1)? 0 : (lastSelected + 1);
      changeArrow(last, lastSelected);
    } if (Gdx.input.isKeyJustPressed(Keys.Z)){
      options[lastSelected].loadMap();
      loadMap();
    }
  }
  
  private void changeArrow(int previous, int actual) {
    
    if(actual == 0){
      arrow.changeCoordinates(50, 300);
    } else if( actual == 1){
      arrow.changeCoordinates(50, 190);
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
