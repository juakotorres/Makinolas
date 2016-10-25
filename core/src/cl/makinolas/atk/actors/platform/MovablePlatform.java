package cl.makinolas.atk.actors.platform;

import cl.makinolas.atk.GameConstants;
import cl.makinolas.atk.actors.Hero;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
//import com.sun.corba.se.impl.orbutil.HexOutputStream;

public class MovablePlatform extends Platform {

  private int fXp, fYp, iXp, iYp;
  private float myWp, myHp;
  private float vY, vX;
  private boolean heroIsInPlatform;

  public MovablePlatform(World myWorld, String textureCode,
                         int x, int finalXPosition,
                         int y, int finalYPosition,
                         int widthTiles, int heightTiles) {
    super(myWorld, textureCode, x, y, widthTiles, heightTiles);

    heroIsInPlatform = false;
    fXp = Math.max(finalXPosition,x);
    fYp = Math.max(finalYPosition,y);
    iXp = Math.min(x, finalXPosition);
    iYp = Math.min(y, finalYPosition);
    myWp = ((float) wp)/2;
    myHp = ((float) hp)/2;
    vY = (Math.abs(fYp - iYp) > 0) ? Math.signum(fYp - iYp)*3f : 0f;
    vX = (Math.abs(fXp - iXp) > 0) ? Math.signum(fXp - iXp)*3f : 0f;
  }

  @Override
  protected void setPlatformBody(World myWorld, int x, int y, int widthTiles, int heightTiles) {
    // Definici�n del cuerpo del jugador.
    myBodyDefinition = new BodyDef();
    myBodyDefinition.type = BodyDef.BodyType.DynamicBody;
    myBodyDefinition.fixedRotation=true;
    myBodyDefinition.position.set(new Vector2(x * TILE_FACTOR + widthTiles * TILE_FACTOR / 2, y * TILE_FACTOR + heightTiles * TILE_FACTOR / 2));

    // Forma del collider del jugador.
    Body myBody = myWorld.createBody(myBodyDefinition);

    PolygonShape shape = new PolygonShape();
    shape.setAsBox(widthTiles * TILE_FACTOR / 2, heightTiles * TILE_FACTOR / 2);
    ///
    myBody.setGravityScale(0);
    myBody.createFixture(shape, 0.5f).setFriction(0);
    myBody.resetMassData();
    shape.dispose();

    // Guardar body.
    setBody(myBody);
  }

  @Override
  public void act(float delta) {
    if((myBody.getPosition().x / 1.8f - myWp) < iXp - 0.1){
      myBody.setTransform((iXp + myWp) * 1.8f, myBody.getPosition().y, myBody.getAngle());
      vX = -vX;
    } else if((myBody.getPosition().x / 1.8f - myWp) > fXp + 0.1){
      myBody.setTransform((fXp + myWp) * 1.8f, myBody.getPosition().y, myBody.getAngle());
      vX = -vX;
    }
    if((myBody.getPosition().y / 1.8f - myHp) < iYp - 0.1){
      myBody.setTransform(myBody.getPosition().x,(iYp + myHp) * 1.8f, myBody.getAngle());
      vY = -vY;
    } else if((myBody.getPosition().y / 1.8f - myHp) > fYp + 0.1){
      myBody.setTransform(myBody.getPosition().x,(fYp + myHp) * 1.8f, myBody.getAngle());
      vY = -vY;
    }

    if(heroIsInPlatform){
      Vector2 heroVel = Hero.getInstance().getBody().getLinearVelocity();
      Hero.getInstance().setMovablePLatformSpeed(vX,vY);
    }

    myBody.setLinearVelocity(vX,vY);
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    xp = (int) ((myBody.getPosition().x / 1.8f - myWp )* TILE_FACTOR * GameConstants.WORLD_FACTOR);
    yp = (int) ((myBody.getPosition().y / 1.8f - myHp )* TILE_FACTOR* GameConstants.WORLD_FACTOR);
    for (int i = 0; i < wp; i++) {
      for (int j = 0; j < hp; j++) {
        batch.draw(region,xp + i*36, yp + j*36,37,37);
      }
    }
  }

  @Override
  public void interactWithHero(Hero hero, WorldManifold worldManifold){
    heroIsInPlatform = true;
    hero.landedPlatform(worldManifold, this);
  }

  @Override
  public void endHeroInteraction(Hero hero, WorldManifold worldManifold) {
    heroIsInPlatform = false;
    hero.setMovablePLatformSpeed(0,0);
  }
}
