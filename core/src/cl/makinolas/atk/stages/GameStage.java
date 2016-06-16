package cl.makinolas.atk.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import cl.makinolas.atk.actors.Background;
import cl.makinolas.atk.actors.Enemy;
import cl.makinolas.atk.actors.GameActor;
import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.Platform;

public class GameStage extends Stage implements ContactListener {
  
  private World suMundo;
  private float accumulator;
  private final float frameTime = 1 / 300f;
  private final float enemySpawn = 3f;
  private float nextEnemyAt;
  
  private OrthographicCamera camera;
  private Box2DDebugRenderer renderer;
  
  public GameStage(){
    nextEnemyAt = enemySpawn;
    suMundo = new World(new Vector2(0, -10), true);
    suMundo.setContactListener(this);
    Actor hero =  new Hero(suMundo);
    Actor platform = new Platform(suMundo, 0, 0, 32f, 1f);
    addActor(new Background());
    addActor(hero);
    addActor(platform);
    accumulator = 0;
    renderer = new Box2DDebugRenderer();
    setupCamera();
  }
  
  private void setupCamera() {
    camera = new OrthographicCamera(32, 24);
    camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
    camera.update();
}
  
  @Override
  public void act(float delta){
    for(Actor actor : getActors())
    {
      if(((GameActor) actor).isEnemy() || ((GameActor) actor).isAttack()){
        Body actorBody = ((GameActor) actor).getBody();
        if(actorBody.getPosition().x < 0 || actorBody.getPosition().x > 32){
          suMundo.destroyBody(actorBody);
          actor.remove();
        }
      }

    }
    super.act(delta);
    
    accumulator += delta;
    nextEnemyAt -= delta;
    
    while(accumulator >= frameTime){
      suMundo.step(frameTime, 6, 2);
      accumulator -= frameTime;
    }
    
    if(nextEnemyAt < 0){
       GameActor enemy = new Enemy(suMundo, new TextureRegion(new Texture(Gdx.files.internal("Gastly.png"))),
                                   new int[]{30,30}, new int[][]{new int[]{3},new int[]{0,1},new int[]{0,2},new int[]{0,3}});
       addActor(enemy);
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
