package cl.makinolas.atk.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class Hero extends GameActor {

  private BodyDef myBodyDefinition;
  private Animation heroWalkingAnimation;
  private boolean isJumping;
  private boolean isFacingRight;
  private float dt;
  private World myWorld;
  
  public Hero(World myWorld) {
    
    isJumping = false;
    isFacingRight = false;
    // Definici�n del cuerpo del jugador.
    dt = 0;
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
    dt += delta;
    
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
    TextureRegion texregion = new TextureRegion(new Texture(Gdx.files.internal("charmander.png")));
    TextureRegion[][] animation = texregion.split(22, 22);
    
    Array<TextureRegion> walking = new Array<TextureRegion>();
    
    walking.addAll( new TextureRegion[]{animation[0][0], animation[0][1], animation[0][2], animation[0][1]});
    
    heroWalkingAnimation = new Animation(0.2f, walking, PlayMode.LOOP);
  }
  
  @Override
  public void draw(Batch batch, float alpha){
    Vector2 myPosition = myBody.getPosition();
    TextureRegion actualSprite = heroWalkingAnimation.getKeyFrame(dt);
    batch.draw(actualSprite, myPosition.x * 20 - actualSprite.getRegionWidth() / 2 , myPosition.y * 20 - actualSprite.getRegionHeight() / 2,
        actualSprite.getRegionWidth() / 2, getOriginY(), actualSprite.getRegionWidth(), actualSprite.getRegionHeight(), (isFacingRight)?-1:1, 1, 0);
  }
  
  @Override
  public boolean isHero(){
    return true;
  }
}
