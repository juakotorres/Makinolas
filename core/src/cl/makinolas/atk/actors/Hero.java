package cl.makinolas.atk.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Hero extends AnimatedActor {

  private BodyDef myBodyDefinition;
  private boolean isJumping;
  private World myWorld;
  
  public Hero(World myWorld) {
    
    isJumping = false;
    isFacingRight = false;
    // Definici�n del cuerpo del jugador.
    this.myWorld = myWorld;
    // Definici�n del cuerpo del jugador.
    myBodyDefinition = new BodyDef();
    myBodyDefinition.type = BodyDef.BodyType.DynamicBody;
    myBodyDefinition.position.set(new Vector2(4,10));
    
    // Forma del collider del jugador.
    Body myBody = myWorld.createBody(myBodyDefinition);
    
    PolygonShape shape = new PolygonShape();
    shape.setAsBox(0.6f,0.7f);
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
    if (Gdx.input.isKeyPressed(Keys.LEFT) && myBody.getPosition().x > 0.5){
      vx -= 7;
      if (isFacingRight){
        isFacingRight = false;
      }
    }
    if (Gdx.input.isKeyPressed(Keys.RIGHT) && myBody.getPosition().x < 31.5){
      vx += 7;
      if (!isFacingRight){
        isFacingRight = true;
      }
    }
    if (Gdx.input.isKeyJustPressed(Keys.SPACE)){
      if(!isJumping){
        myBody.applyLinearImpulse(0, 8, myBody.getPosition().x, myBody.getPosition().y, true);
        isJumping = true;
      }
    }
    if (Gdx.input.isKeyJustPressed(Keys.Z)){
      GameActor fireball = new Fireball(myWorld, myBody.getPosition().x,myBody.getPosition().y,isFacingRight);
      getStage().addActor(fireball);
    }
    myBody.setLinearVelocity(vx, myBody.getLinearVelocity().y);
  }
  
  public void landedPlatform(){
    isJumping = false;
  }

  private void setAnimation(){
    setMasterTexture(new TextureRegion(new Texture(Gdx.files.internal("charmander.png"))),22,22);
    addAnimation(4,0.2f, new int[]{0, 0}, new int[]{0, 1}, new int[]{0, 2}, new int[]{0, 1});
  }
  
  @Override
  public boolean isHero(){
    return true;
  }
}
