package cl.makinolas.atk.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Hero extends GameActor {

  private BodyDef myBodyDefinition;
  private TextureRegion heroSprite;
  private boolean isJumping;
  private boolean isFacingRight;
  
  public Hero(World myWorld) {
    
    isJumping = false;
    isFacingRight = false;
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
    
    // Crear sprite para jugador.
    setSprite();
  }
  
  @Override
  public void act(float delta){
    int vx = 0;
    if (Gdx.input.isKeyPressed(Keys.LEFT)){
      vx -= 4;
      if (isFacingRight){
        heroSprite.flip(true, false);
        isFacingRight = false;
      }
    }
    if (Gdx.input.isKeyPressed(Keys.RIGHT)){
      vx += 4;
      if (!isFacingRight){
        heroSprite.flip(true, false);
        isFacingRight = true;
      }
    }
    if (Gdx.input.isKeyJustPressed(Keys.SPACE)){
      if(!isJumping){
        myBody.applyLinearImpulse(0, 8, myBody.getPosition().x, myBody.getPosition().y, true);
        isJumping = true;
      }
    }
    myBody.setLinearVelocity(vx, myBody.getLinearVelocity().y);
  }
  
  public void landedPlatform(){
    isJumping = false;
  }
  
  private void setSprite(){
    TextureRegion texregion = new TextureRegion(new Texture(Gdx.files.internal("hero.png")));
    heroSprite = texregion;
  }
  
  @Override
  public void draw(Batch batch, float alpha){
    Vector2 myPosition = myBody.getPosition();
    batch.draw(heroSprite, myPosition.x * 20 - heroSprite.getRegionWidth() / 2 , myPosition.y * 20 - heroSprite.getRegionHeight() / 2);
  }
  
  @Override
  public boolean isHero(){
    return true;
  }
}
