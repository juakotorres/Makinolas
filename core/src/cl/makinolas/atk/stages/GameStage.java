package cl.makinolas.atk.stages;

import cl.makinolas.atk.actors.*;
import cl.makinolas.atk.utils.LevelReader;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import java.io.IOException;

public class GameStage extends Stage implements ContactListener {

  public static float elapsedTime;

  private World suMundo;
  private float accumulator;
  private final float frameTime = 1 / 300f;
  private final float enemySpawn = 3f;
  private float nextEnemyAt;
  private Array<GameActor> gameActors;
  
  private OrthographicCamera camera;
  private Box2DDebugRenderer renderer;
  
  public GameStage(){
    nextEnemyAt = enemySpawn;
    gameActors = new Array<GameActor>();
    suMundo = new World(new Vector2(0, -10), true);
    suMundo.setContactListener(this);
    GameActor hero =  new Hero(suMundo);
    addActor(new Background());
    createPlatforms();
    addGameActor(hero);
    accumulator = 0;
    renderer = new Box2DDebugRenderer();
    setupCamera();
  }

  public void addGameActor(GameActor actor) {
    addActor(actor);
    gameActors.add(actor);
  }

  private void createPlatforms() {
    LevelReader reader = LevelReader.getInstance();
    reader.setWorld(suMundo);
    try {
      Array<GameActor> platforms = reader.loadLevel("level1");
      for(GameActor p : platforms)
        addActor(p);
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
        if(actorBody.getPosition().y < -10 || actorBody.getPosition().x < -100 || actorBody.getPosition().x > 200 || actor.isDead()){
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
       GameActor enemy = new Gastly(suMundo, (int) getCamera().position.x);
       addGameActor(enemy);
       nextEnemyAt = enemySpawn;
    }
    
  }
  
  @Override
  public void draw() {
      super.draw();
      camera.update();
      renderer.render(suMundo, camera.combined);
  }

  @Override
  public void beginContact(Contact contact) {
    
    GameActor actor1 = (GameActor) contact.getFixtureA().getBody().getUserData();
    GameActor actor2 = (GameActor) contact.getFixtureB().getBody().getUserData();
    
    if(actor1.isHero() && actor2.isPlatform()){
      ((Hero) actor1).landedPlatform();
    } else if (actor2.isHero() && actor1.isPlatform()){
      ((Hero) actor2).landedPlatform();
    } else if (actor1.isAttack() && actor2.isMonster()){
      ((Monsters) actor2).damage(((Attacks) actor1).getAttackDamage(), (Attacks) actor1);
    } else if (actor2.isAttack() && actor1.isMonster()){
      ((Monsters) actor1).damage(((Attacks) actor2).getAttackDamage(), (Attacks) actor2);
    } else if (actor2.isHero() && actor1.isMonster()){
      ((Monsters) actor2).meleedamage(((Monsters) actor1).getMeleeDamage());
    } else if (actor1.isHero() && actor2.isMonster()){
      ((Monsters) actor1).meleedamage(((Monsters) actor2).getMeleeDamage());
    }
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
