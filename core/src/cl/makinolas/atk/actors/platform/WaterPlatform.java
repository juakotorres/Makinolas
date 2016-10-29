package cl.makinolas.atk.actors.platform;

import com.badlogic.gdx.physics.box2d.World;

public class WaterPlatform extends Platform {

	public WaterPlatform(World myWorld, int x, int y) {
		super(myWorld, "WT", x, y, 1, 1);
		
	}

}
