package cl.makinolas.atk.actors.enemies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldManifold;

import cl.makinolas.atk.actors.GameActor;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.friend.Friend;

public class AttackDetector extends GameActor{

  private Enemy myEnemy;
  private boolean attackDetected;
  private BodyDef myBodyDefinition; 
  private Body myBody;
  
  public AttackDetector(World myWorld, Enemy enemy, Friend parent) {
    myEnemy = enemy;
    attackDetected = false;
    
    myBodyDefinition = new BodyDef();
    myBodyDefinition.type = BodyDef.BodyType.DynamicBody;
    myBodyDefinition.position.set(new Vector2(enemy.getBody().getPosition().x,enemy.getBody().getPosition().y));
    
    myBody = myWorld.createBody(myBodyDefinition);
    
    PolygonShape shape = new PolygonShape();
    shape.setAsBox(getBodySize(parent.getWidth() * 4), getBodySize(parent.getHeight() * 2));
    
    myBody.setGravityScale(0);
    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.isSensor = true;
    fixtureDef.density = 0;
    fixtureDef.shape = shape;
    myBody.createFixture(fixtureDef);
    myBody.resetMassData();
    shape.dispose();
    
    // Guardar body.
    setBody(myBody);
    
  }
  
  // This is used to get body width and height.
  private float getBodySize(int size){
    return (0.5f*size)/22;
  }
  
  @Override
  public void act(float delta){
    myBody.setTransform(new Vector2(myEnemy.getBody().getPosition().x,myEnemy.getBody().getPosition().y), 0);
    
    if(attackDetected){
      myEnemy.jump();
      attackDetected = false;
    }
  }
  

  @Override
  public void interact(GameActor actor2, WorldManifold worldManifold) {
    actor2.interactWithAttackDetector(this);
  }
  
  @Override
  public void interactWithAttack(Attacks attacks, WorldManifold worldManifold) {
    detected(attacks);
  }

  public void detected(Attacks attacks) {
    if(!attacks.getSource().isEnemy()){
      attackDetected = true;    
    }
  }
  
}
