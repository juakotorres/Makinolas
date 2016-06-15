package cl.makinolas.atk.actors;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Platform extends GameActor {
  
  private BodyDef myBodyDefinition;
  

  public Platform(World myWorld, int x, int y, float width , float height) {
    
    // Definición del cuerpo del jugador.
    myBodyDefinition = new BodyDef();
    myBodyDefinition.position.set(new Vector2(x, y));
    
    // Forma del collider del jugador.
    Body myBody = myWorld.createBody(myBodyDefinition);
    
    PolygonShape shape = new PolygonShape();
    shape.setAsBox(width, height);
    ///
    myBody.setGravityScale(1);
    myBody.createFixture(shape, 0.5f);
    myBody.resetMassData();
    shape.dispose();
    
    // Guardar body.
    setBody(myBody);
  }
  
  @Override
  public boolean isPlatform(){
    return true;
  }
}
