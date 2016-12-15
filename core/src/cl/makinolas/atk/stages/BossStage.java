package cl.makinolas.atk.stages;

import java.io.IOException;

import cl.makinolas.atk.actors.bosses.BossFinder;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.InputController;
import cl.makinolas.atk.actors.Portal;
import cl.makinolas.atk.actors.Title;
import cl.makinolas.atk.actors.ui.MainBar;
import cl.makinolas.atk.actors.ui.MobileGroup;
import cl.makinolas.atk.audio.GDXMusicPlayer;
import cl.makinolas.atk.screen.GameScreen;
import cl.makinolas.atk.utils.LevelReader;

public class BossStage extends AbstractStage implements ContactListener {

  private World suMundo;
  private float accumulator;
  private final float frameTime = 1 / 300f;
  private boolean bossDefeated;
  private Array<GameActor> gameActors;
  private Group ground, mons, ui;
  private Game myGame;
  private MainBar bar;

  private OrthographicCamera camera;
  private Box2DDebugRenderer renderer;

  public BossStage(Viewport v, GameScreen actualScreen, Game myGame, Levels type){
    super(v);
    musicplayer= GDXMusicPlayer.getInstance();
    level = type;
    levelName = getLevelName();
    this.myGame = myGame;
    myScreen = actualScreen;
    gameActors = new Array<GameActor>();
    suMundo = new World(new Vector2(0, -30), true);
    suMundo.setContactListener(this);

    addActor(new Background(getLevelBackground(), getCamera()));
    

    musicplayer.PlayLooped(getLevelMusic());

    ground = new Group();
    addActor(ground);
    mons = new Group();
    addActor(mons);
    ui = new Group();
    addActor(ui);
    
    MobileGroup group = new MobileGroup(Gdx.app.getType() == Application.ApplicationType.Android);
    Gdx.input.setInputProcessor(this);
    Hero hero =  Hero.getInstance();
    hero.setWorld(suMundo);
    createPlatforms(myGame);
    
    bossDefeated = false;
    
    addActor(new Title("Overlays/bossBar2.png", 550,200));
    
    addGameActor(hero);
    bar = MainBar.getInstance();
    ui.addActor(bar);
    ui.addActor(group);

    addListener(new InputController(hero,group));
    accumulator = 0;
    renderer = new Box2DDebugRenderer();
    setupCamera();
  }
  

  public void addGameActor(GameActor actor) {
    mons.addActor(actor);
    gameActors.add(actor);
  }

  private void createPlatforms(Game g) {
    LevelReader reader = LevelReader.getInstance();
    reader.setWorld(suMundo);
    reader.setGame(g);
    reader.setStage(this);
    try {
      Array<GameActor> platforms = reader.loadLevel(getLevelName());
      for(GameActor p : platforms)
        ground.addActor(p);
    } catch (IOException e) {
      e.printStackTrace();
    }
    createBoss(reader.getBoss());
  }

  private void createBoss(String name){
    GameActor enemy = BossFinder.getInstance().create(name,suMundo,Hero.getInstance());
    addGameActor(enemy);
  }

  private void setupCamera() {
    camera = new OrthographicCamera(32, 24);
    camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
    camera.update();
  }
  
  public void bossIsDead(){
    bossDefeated = true;
  }
  
  public void changeCamera(float x, float y){
    camera.position.set(14, 7, 0);
    getCamera().position.set(14 * 20, 7* 20, 0);
    getCamera().update();    
    camera.update();
  }
  
  @Override
  public void act(float delta){
    super.act(delta);
    checkBossAlive();
    for(GameActor actor : gameActors){
      if(actor.isHero() && actor.isDead()){
    	musicplayer.StopMusic();
        changeDeadMenu();
      }
      if(actor.isMonster() || actor.isPuff() || actor.isBall() || actor.isAttack() || actor.isDetector()){
        Body actorBody = actor.getBody();
        if(actor.isHero() && (actorBody.getPosition().y < -200 || actorBody.getPosition().x < -100 || actor.isDead())){
            changeDeadMenu();
         }
        if(actorBody.getPosition().y < -200 || actorBody.getPosition().x < -100 || actorBody.getPosition().x > 200 || actor.isDead()){
          gameActors.removeValue(actor,true);
          suMundo.destroyBody(actorBody);
          actor.remove();
        }
      }
    }

    accumulator += delta;
    elapsedTime += delta;
    
    while(accumulator >= frameTime){
      suMundo.step(frameTime, 6, 2);
      accumulator -= frameTime;
    }
    
  }

  private void checkBossAlive() {
    if(bossDefeated){
      Portal portal = new Portal(suMundo, new Vector2(10, 3), myGame);
      musicplayer.StopMusic();
      addGameActor(portal); 
      bossDefeated = false;
    }    
  }


  @Override
  public void draw() {
      super.draw();
      //bar.drawCustom(getBatch(),getCamera().position.x,getCamera().position.y); //Custom draw for MainBar
      camera.update();
      renderer.render(suMundo, camera.combined);
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
