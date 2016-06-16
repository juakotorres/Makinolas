package cl.makinolas.atk.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

public class Fireball extends GameActor {
  
  private Animation fireballAnimation;
  private BodyDef myBodyDefinition; 
  private float xVelocity;
  private float initialPosition;
  private float dt;
  
  public Fireball(World myWorld, float x , float y, boolean facingRight){
    
    dt = 0;
    this.xVelocity = (facingRight)? 10: -10;
    this.initialPosition= (facingRight)? .5f: -.5f;
    myBodyDefinition = new BodyDef();
    myBodyDefinition.type = BodyDef.BodyType.KinematicBody;
    myBodyDefinition.position.set(new Vector2(x + initialPosition,y));
    
    Body myBody = myWorld.createBody(myBodyDefinition);
    
    PolygonShape shape = new PolygonShape();
    shape.setAsBox(0f, 0f);
    
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
    
    // Guardar animaciones del jugador
    setAnimation();
  }
  
  @Override
  public void act(float delta){
    dt += delta;
    myBody.setLinearVelocity(xVelocity, 0);
  }
  
  private void setAnimation(){
    TextureRegion texregion = new TextureRegion(new Texture(Gdx.files.internal("fireball.png")));
    TextureRegion[][] animation = texregion.split(30, 37);
    
    Array<TextureRegion> moving = new Array<TextureRegion>();
    
    moving.addAll( new TextureRegion[]{animation[0][0], animation[0][1], animation[0][2], animation[0][3], animation[0][4]});
    
    fireballAnimation = new Animation(0.2f, moving, PlayMode.LOOP);
   
  }
  
  @Override
  public void draw(Batch batch, float alpha){
    Vector2 myPosition = myBody.getPosition();
    TextureRegion actualSprite = fireballAnimation.getKeyFrame(dt);
    batch.draw(actualSprite, myPosition.x * 20 - actualSprite.getRegionWidth() / 2 , myPosition.y * 20 - actualSprite.getRegionHeight() / 2,
        actualSprite.getRegionWidth() / 2, getOriginY(), actualSprite.getRegionWidth(), actualSprite.getRegionHeight(), (xVelocity < 0)?-1:1, 1, 0);
  }
}
