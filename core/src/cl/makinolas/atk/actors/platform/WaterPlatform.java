package cl.makinolas.atk.actors.platform;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldManifold;

import cl.makinolas.atk.GameConstants;
import cl.makinolas.atk.actors.GameActor;
import cl.makinolas.atk.actors.Hero;

public class WaterPlatform extends GameActor {
	
	private TextureRegion texture;
	static final float TILE_FACTOR = 1.8f;
	protected BodyDef myBodyDefinition;
	int xp, yp, wp, hp;


	
    
	public WaterPlatform(World myWorld, int x, int y) {
		
		
		texture = new TextureRegion(new Texture(Gdx.files.internal("Background/Water.png")));

		myBodyDefinition = new BodyDef();
	    myBodyDefinition.position.set(new Vector2(x*TILE_FACTOR + 1 * TILE_FACTOR /2, y*TILE_FACTOR + 1 * TILE_FACTOR / 2));

	    Body myBody = myWorld.createBody(myBodyDefinition);
	    PolygonShape shape = new PolygonShape();
	    shape.setAsBox(1 * TILE_FACTOR /2, 1 * TILE_FACTOR / 2);
	    myBody.setGravityScale(1);
	    myBody.createFixture(shape, 0.5f).setFriction(0);
	    myBody.resetMassData();
	    shape.dispose();
	    setBody(myBody);
	    
	    xp = (int) (x * TILE_FACTOR * GameConstants.WORLD_FACTOR);
	    yp = (int) (y * TILE_FACTOR* GameConstants.WORLD_FACTOR);
	    wp = 1;
	    hp = 1;
	  }
	    
	
	@Override
	public void interactWithHero(Hero hero, WorldManifold worldManifold){	    
	    
	    System.out.println("HOLA");
	  }
	
	@Override
	  public void endHeroInteraction(Hero hero, WorldManifold worldManifold) {
	    System.out.println("CHAO");
	  }
	
	@Override
	  public void act(float delta) {
	   
	        
	  }
	
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
	    for (int i = 0; i < wp; i++) {
	      for (int j = 0; j < hp; j++) {
	        batch.draw(texture, xp + i*36, yp + j*36,37,37);
	      }
	    }
	  }


	@Override
	public void interact(GameActor actor2, WorldManifold worldManifold) {
		System.out.println("interact");
	}


	@Override
	public void endInteraction(GameActor actor2, WorldManifold worldManifold) {
		// TODO Auto-generated method stub
		
	}

	

}
