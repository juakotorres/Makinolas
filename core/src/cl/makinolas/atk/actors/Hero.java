package cl.makinolas.atk.actors;

import cl.makinolas.atk.stages.GameStage;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Hero extends Monsters {

  private boolean isJumping;
  private int health;
  private boolean isDamaged;
  private boolean dead;
  private World myWorld;
  
  public Hero(World myWorld) {
    
    isJumping = false;
    isFacingRight = false;
    health = 100;
    isDamaged = false;
    dead = false;
    // Definici�n del cuerpo del jugador.
    this.myWorld = myWorld;
    // Definici�n del cuerpo del jugador.
    BodyDef myBodyDefinition = new BodyDef();
    myBodyDefinition.type = BodyDef.BodyType.DynamicBody;
    myBodyDefinition.position.set(new Vector2(4,3));
    
    // Forma del collider del jugador.
    Body myBody = myWorld.createBody(myBodyDefinition);
    
    PolygonShape shape = new PolygonShape();
    shape.setAsBox(0.6f,0.5f);
    ///
    myBody.setGravityScale(1);
    myBody.createFixture(shape, 0.5f);
    myBody.resetMassData();
    shape.dispose();
    
    // Guardar body.
    setBody(myBody);
    
    // Guardar animaciones del jugador
    setAnimation();
  }
  
  @Override
  public void act(float delta){
    int vx = 0;
    if (Gdx.input.isKeyPressed(Keys.LEFT)){
      vx -= 7;
      if (isFacingRight){
        isFacingRight = false;
      }
    }
    if (Gdx.input.isKeyPressed(Keys.RIGHT)){
      vx += 7;
      if (!isFacingRight){
        isFacingRight = true;
      }
    }
    if (Gdx.input.isKeyJustPressed(Keys.SPACE)){
      if(!isJumping){
        myBody.applyLinearImpulse(0, 7, myBody.getPosition().x, myBody.getPosition().y, true);
        isJumping = true;
      }
    }
    if (Gdx.input.isKeyJustPressed(Keys.Z)){
      GameActor fireball = new Fireball(myWorld, myBody.getPosition().x,myBody.getPosition().y,isFacingRight, this);
      ((GameStage) getStage()).addGameActor(fireball);
    }
    myBody.setLinearVelocity(vx, myBody.getLinearVelocity().y);
    
    ((GameStage) getStage()).changeCamera(myBody.getPosition().x , myBody.getPosition().y );
  }
  
  public void landedPlatform(){
    isJumping = false;
  }

  private void setAnimation(){
    setMasterTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/charmander.png"))),22,22);
    addAnimation(4,0.2f, new int[]{0, 0}, new int[]{0, 1}, new int[]{0, 2}, new int[]{0, 1});
  }
  
  @Override
  public boolean isHero(){
    return true;
  }

  public int getHealth(){
    return health;
  }

  @Override
  public void damage(int damage, Attacks inflictor)  {
    if(!inflictor.getSource().isHero()){
      health -= damage;   
      isDamaged = true;
      inflictor.setDead();
    }
    if(health <= 0){
      dead = true;
    }

  }
  
  @Override
  public boolean isDead(){
    return dead;
  }


  @Override
  public int getMeleeDamage() {
    return 0;
  }

  @Override
  public void meleedamage(int damage) {
    health -= damage;   
    isDamaged = true;
    if(health <= 0){
      dead = true;
    }   
  }
}
