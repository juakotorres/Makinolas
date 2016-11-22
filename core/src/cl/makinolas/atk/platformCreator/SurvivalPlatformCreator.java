package cl.makinolas.atk.platformCreator;

import cl.makinolas.atk.stages.AbstractStage;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * Created by belisariops on 11/22/16.
 */
public class SurvivalPlatformCreator extends AbstractPlatformCreator {

    public SurvivalPlatformCreator(World suMundo, AbstractStage stage,
                                   int xInitialPosition, int yInitialPosition, Group ground) {
        super(suMundo,stage,xInitialPosition,yInitialPosition,ground);
    }

    @Override
    public void createPlatform(float cameraPositionX, float cameraPositionY) {

    }
}
