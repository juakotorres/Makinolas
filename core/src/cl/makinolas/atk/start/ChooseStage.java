package cl.makinolas.atk.start;

import cl.makinolas.atk.screen.MapScreen;
import cl.makinolas.atk.stages.MapStage;
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
import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.Title;
import cl.makinolas.atk.actors.friend.Charmander;
import cl.makinolas.atk.actors.friend.Friend;
import cl.makinolas.atk.actors.friend.Snivy;
import cl.makinolas.atk.actors.friend.Totodile;
import cl.makinolas.atk.actors.ui.MainBar;
import cl.makinolas.atk.actors.ui.MobileGroup;
import cl.makinolas.atk.screen.GameScreen;
import cl.makinolas.atk.stages.AbstractStage;
import cl.makinolas.atk.stages.GameStage;
import cl.makinolas.atk.stages.Levels;
import cl.makinolas.atk.utils.SaveManager;

public class ChooseStage extends AbstractStage {

  private World suMundo;
  private float accumulator;
  private final float frameTime = 1 / 300f;
  private Group ground, mons, ui;
  private Game myGame;
  
  private OrthographicCamera camera;
  private Box2DDebugRenderer renderer;
  private PokemonStarter[] options;
  private int lastSelected;
  private Title arrow;
  
  public ChooseStage(Viewport v, GameScreen actualScreen, Game myGame) {
    super(v);
    
    lastSelected = 0;
    this.myGame = myGame;
    myScreen = actualScreen;
    suMundo = new World(new Vector2(0, -10), true);
    addActor(new Background("CharacterImages/background1.png", getCamera()));
    addActor(new Title("CharacterImages/choosecharacter.png",220 ,400));
    
    arrow = new Title("CharacterImages/arrow.png", 50, 300);
    addActor(arrow);
    PokemonStarter firstOption = new PokemonStarter("CharacterImages/charmander.png", new Charmander(5),120,300
        ,"CharacterImages/firetype.png", GameText.charmanderDescription );
    addActor(firstOption);
    PokemonStarter secondOption = new PokemonStarter("CharacterImages/snivy.png", new Snivy(5), 320,300
        ,"CharacterImages/grasstype.png", GameText.snivyDescription );
    addActor(secondOption);
    PokemonStarter thirdOption = new PokemonStarter("CharacterImages/totodile.png", new Totodile(5), 220,100,
        "CharacterImages/watertype.png", GameText.totodileDescription);
    addActor(thirdOption);
    
    options = new PokemonStarter[]{firstOption,secondOption, thirdOption};
    
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
      lastSelected = (lastSelected == 0)? 2 : (lastSelected - 1);
      changeArrow(last, lastSelected);
    } if (Gdx.input.isKeyJustPressed(Keys.RIGHT)){
      int last = lastSelected;
      lastSelected = (lastSelected == 2)? 0 : (lastSelected + 1);
      changeArrow(last, lastSelected);
    }
    
  }
  
  private void changeArrow(int previous, int actual) {
    options[previous].notSelected();
    options[actual].isSelected();
    
    if(actual == 0){
      arrow.changeCoordinates(50, 300);
    } else if( actual == 1){
      arrow.changeCoordinates(250, 300);
    } else {
      arrow.changeCoordinates(150, 100);
    }
    
  }

  public void setChosenInitial(Friend friend){
    SaveManager.getInstance().startGameSave(friend);
    Hero.getInstance().reset();
    MainBar.getInstance().reset();

    MapScreen mapScreen = new MapScreen(myGame,new MapStage(new FitViewport(640, 480),myGame));
    myGame.setScreen(mapScreen);
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
