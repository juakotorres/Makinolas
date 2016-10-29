package cl.makinolas.atk.actors.platform;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

public class WaterPlatform extends Platform {
	
	private TextureRegion mySprite;

	public WaterPlatform(World myWorld, int x, int y) {
		super(myWorld, "WT", x, y, 1, 1);
		
		mySprite = new TextureRegion(new Texture(Gdx.files.internal("Background/Water.png")));
		
	}
	
	@Override
	  public void draw(Batch batch, float parentAlpha) {
	    for (int i = 0; i < wp; i++) {
	      for (int j = 0; j < hp; j++) {
	        batch.draw(mySprite,xp + i*36, yp + j*36,37,37);
	      }
	    }
	  }

}
