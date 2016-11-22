package cl.makinolas.atk.minigames;

import cl.makinolas.atk.platformCreator.MinigamePlatformCreator;
import cl.makinolas.atk.utils.SaveManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

import cl.makinolas.atk.actors.Background;
import cl.makinolas.atk.actors.GameActor;
import cl.makinolas.atk.actors.fx.FxManager;
import cl.makinolas.atk.actors.platform.Platform;
import cl.makinolas.atk.actors.ui.BagVis;
import cl.makinolas.atk.actors.ui.MainBar;
import cl.makinolas.atk.actors.ui.MobileGroup;
import cl.makinolas.atk.audio.GDXMusicPlayer;
import cl.makinolas.atk.screen.GameScreen;
import cl.makinolas.atk.stages.AbstractStage;
import cl.makinolas.atk.stages.CameraPosition;

public class MinigameStage extends AbstractStage implements ContactListener{
  
  private World suMundo;
  private float accumulator;
  private final float frameTime = 1 / 300f;
  private Array<GameActor> gameActors;
  private Group ground, mons, ui, deco;
  private MainBar bar;
  private BagVis bagVis;
  private Platform initialPlatform;
  private MinigameCharacter hero;
  private BitmapFont large = new BitmapFont(Gdx.files.internal("Fonts/large.fnt"),Gdx.files.internal("Fonts/large.png"),false);
  private BitmapFont normal = new BitmapFont(Gdx.files.internal("Fonts/normal.fnt"),Gdx.files.internal("Fonts/normal.png"),false);
  private float score;
  private int hgsc;

  private OrthographicCamera camera;
  private Box2DDebugRenderer renderer;
  
  public MinigameStage(Viewport v, GameScreen actualScreen, Game myGame){
    super(v);

    musicplayer = GDXMusicPlayer.getInstance();
    myScreen = actualScreen;
    gameActors = new Array<GameActor>();
    suMundo = new World(new Vector2(0, -30), true);
    suMundo.setContactListener(this);
    initialPlatform =  new Platform(suMundo, "CU", 0, 0, 20, 1);
    addActor(new Background("Background/OldRuins1.1.png", getCamera()));
    hgsc = SaveManager.getInstance().getHighscore();


    musicplayer.PlayLooped("Music/Freesia.mp3");



    deco = new Group();
    addActor(deco);
    ground = new Group();
    addActor(ground);
    mons = new Group();
    addActor(mons);
    ui = new Group();
    addActor(ui);

    MobileGroup group = new MobileGroup(false);
    Gdx.input.setInputProcessor(this);
    
    cameraObserver = new CameraPosition();

    ground.addActor(initialPlatform);
    ground.addActor(new MinigamePlatformCreator(suMundo, this, 20, 0, ground));
    hero = new MinigameCharacter(suMundo);
    cameraObserver.setPosition(hero.getBody().getPosition().x, hero.getBody().getPosition().y);
    addGameActor(hero);
    ui.addActor(group);
    ui.addActor(BagVis.getInstance());

    FxManager.getInstance().setParent(ui);
   
    addListener(new MinigameInputController(hero,group));
    accumulator = 0;
    renderer = new Box2DDebugRenderer();
    //setupCamera();
  }

  public void addGameActor(GameActor actor) {
    mons.addActor(actor);
    gameActors.add(actor);
  }

  private void setupCamera() {
    camera = new OrthographicCamera(32, 24);
    camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
    camera.update();
  }
  
  public void changeCamera(float x, float y){
    //camera.position.set((x + 7), 7, 0);
    getCamera().position.set((x + 7) * 20, 7* 20, 0);
    getCamera().update();    
    //camera.update();
  }
  
  @Override
  public void act(float delta){
    
    for(GameActor actor : gameActors){
      Body actorBody = actor.getBody();
      if(actor.isMinigameCharacter() && actor.isDead()){
        if(score>hgsc)
          SaveManager.getInstance().setHighscore((int) score);
        changeDeadMenu();
      }
      if(actor.isEnemy() || actor.isPuff() || actor.isAttack() || actor.isBall() || actor.isItem() || actor.isDetector()){
        if(actorBody.getPosition().y < -200 || actorBody.getPosition().x < -100 || actor.isDead()){
          gameActors.removeValue(actor,true);
          suMundo.destroyBody(actorBody);
          actor.remove();
        }
      }
    }
    
    if(!paused) {
      super.act(delta); // move to first line in case of errors
      
      score += delta * 100;
      accumulator += delta;
      elapsedTime += delta;

      while (accumulator >= frameTime) {
        suMundo.step(frameTime, 6, 2);
        accumulator -= frameTime;
        cameraObserver.setPosition(hero.getBody().getPosition().x, hero.getBody().getPosition().y);
      }
    }
    
  }

  @Override
  public void draw() {
      super.draw();
      
      getBatch().begin();
      large.draw(getBatch(), "SCORE : " + (int) score, 100 + getCamera().position.x ,  300);
      normal.draw(getBatch(), "Highscore : "+hgsc, 140 + getCamera().position.x ,  280);
      getBatch().end();
      //camera.update();
      //renderer.render(suMundo, camera.combined);
  }

  @Override
  public void togglePause() {
    super.togglePause();
    if(isPaused()){
      bagVis = BagVis.getInstance();
      //bagVis.setPosition(getCamera().position.x,getCamera().position.y);
      bagVis.show();
    }
    else{
      bagVis.hide();
    }
  }

  @Override
  public void beginContact(Contact contact) {
    
    GameActor actor1 = (GameActor) contact.getFixtureA().getBody().getUserData();
    GameActor actor2 = (GameActor) contact.getFixtureB().getBody().getUserData();
    
    actor1.interact(actor2, contact.getWorldManifold());
  }

  @Override
  public void endContact(Contact contact) {
    
  }

  @Override
  public void preSolve(Contact contact, Manifold oldManifold) {
    
  }

  @Override
  public void postSolve(Contact contact, ContactImpulse impulse) {
    
  }
  
}
