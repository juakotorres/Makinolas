package cl.makinolas.atk.actors.platform;

import cl.makinolas.atk.actors.GameActor;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;

/**
 * Created by belisariops on 12/15/16.
 */
public class myPlatform extends Platform {
    public myPlatform(World myWorld, String textureCode, int x, int y, int widthTiles, int heightTiles,float cameraPositionY) {
        super(myWorld, textureCode, x, y, widthTiles, heightTiles);
        cameraPositionWhenCreated = cameraPositionY;
    }



}
