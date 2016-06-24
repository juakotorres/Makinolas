package cl.makinolas.atk.stages;

import cl.makinolas.atk.actors.*;
import cl.makinolas.atk.actors.friend.Eevee;
import cl.makinolas.atk.actors.ui.MainBar;
import cl.makinolas.atk.actors.ui.MobileGroup;
import cl.makinolas.atk.utils.LevelReader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.io.IOException;

public class GameStage extends Stage implements ContactListener {

  public static float elapsedTime;
  public static String levelName = "level1";

  private World suMundo;
  private float accumulator;
  private final float frameTime = 1 / 300f;
  private final float enemySpawn = 3f;
  private float nextEnemyAt;
  private Array<GameActor> gameActors;
  private Group ground, mons, ui;

  private MainBar bar;

  private OrthographicCamera camera;
  private Box2DDebugRenderer renderer;
  
  public GameStage(Viewport v){
    super(v);
    nextEnemyAt = enemySpawn;
    gameActors = new Array<GameActor>();
    suMundo = new World(new Vector2(0, -10), true);
    suMundo.setContactListener(this);

    addActor(new Background("Background/SuPuente.jpg", getCamera()));

    ground = new Group();
    addActor(ground);
    mons = new Group();
    addActor(mons);
    ui = new Group();
    addActor(ui);

    //MobileGroup group = new MobileGroup(Gdx.app.getType() == Application.ApplicationType.Android);
    MobileGroup group = new MobileGroup(true);
    Gdx.input.setInputProcessor(this);
    Hero hero =  new Hero(suMundo, group);
    createPlatforms();
    Portal portal = new Portal(suMundo, new Vector2(49, -6));
    addGameActor(portal);
    addGameActor(hero);
    bar = new MainBar(hero);
    ui.addActor(bar);
    ui.addActor(group);

    accumulator = 0;
    renderer = new Box2DDebugRenderer();
    setupCamera();
  }

  public void addGameActor(GameActor actor) {
    mons.addActor(actor);
    gameActors.add(actor);
  }

  private void createPlatforms() {
    LevelReader reader = LevelReader.getInstance();
    reader.setWorld(suMundo);
    try {
      Array<GameActor> platforms = reader.loadLevel(GameStage.levelName);
      for(GameActor p : platforms)
        ground.addActor(p);
    } catch (IOException e) {
      e.printStackTrace();
    }
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
  
  @Override
  public void act(float delta){
    super.act(delta);
    for(GameActor actor : gameActors){
      if(actor.isMonster() || actor.isAttack()){
        Body actorBody = actor.getBody();
        if(actorBody.getPosition().y < -200 || actorBody.getPosition().x < -100 || actorBody.getPosition().x > 200 || actor.isDead()){
          gameActors.removeValue(actor,true);
          suMundo.destroyBody(actorBody);
          actor.remove();
        }
      }
    }

    accumulator += delta;
    elapsedTime += delta;
    nextEnemyAt -= delta;
    
    while(accumulator >= frameTime){
      suMundo.step(frameTime, 6, 2);
      accumulator -= frameTime;
    }
    
    if(nextEnemyAt < 0){
       GameActor enemy = (new Eevee()).returnEnemy(suMundo, (int) getCamera().position.x);
       addGameActor(enemy);
       nextEnemyAt = enemySpawn;
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
    
    actor1.interact(actor2);
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
