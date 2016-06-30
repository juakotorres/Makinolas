package cl.makinolas.atk.actors.bosses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.HBar;

public class OldMewtwo extends Boss {
  
  
  public OldMewtwo(World myWorld) {
    
    health = 100;
    width = 39;
    height = 33;
    isAttacking = true;
    healthBar = new HBar(health, health, width, 4, new TextureRegion( new Texture(Gdx.files.internal("Overlays/bar_green.png"))));
    isDamaged = false;
    dead = false;
    accumulator = 0;
    
    // Definici�n del cuerpo del jugador.
    BodyDef myBodyDefinition = new BodyDef();
    myBodyDefinition.type = BodyDef.BodyType.DynamicBody;
    myBodyDefinition.position.set(new Vector2(16,3));
    
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
    setAnimation(new TextureRegion(new Texture(Gdx.files.internal("Actors/OldMewtwo.png"))), 39, 33);
    hurtAnimation = addAnimation(0.2f, 0);
    walkAnimation = addAnimation(0.2f, 3,4);
    attackAnimation = addAnimation(0.2f, 5,6,7,8,9);
    changeAnimation(walkAnimation);
    
  }
  
  @Override
  public void act(float delta){     
    super.act(delta);
  }
  
  
  @Override
  public int getMeleeDamage() {
    return 10;
  }
  
  
}
