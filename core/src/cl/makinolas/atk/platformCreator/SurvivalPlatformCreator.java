package cl.makinolas.atk.platformCreator;

import cl.makinolas.atk.actors.platform.Platform;
import cl.makinolas.atk.stages.AbstractStage;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * Created by belisariops on 11/22/16.
 */
public class SurvivalPlatformCreator extends AbstractPlatformCreator {
    Platform platform1;
    Platform platform2;
    Platform platform3;
    Platform platform4;
    private int nextPlatformPosition;
    private boolean triplePlatform;
    private boolean doublePlatform;
    private boolean cuadruplePlatform;


    public SurvivalPlatformCreator(World suMundo, AbstractStage stage,
                                   int xInitialPosition, int yInitialPosition, Group ground) {
        super(suMundo,stage,xInitialPosition,yInitialPosition,ground);
        nextPlatformPosition = 3;
        triplePlatform = false;
        doublePlatform = false;
        cuadruplePlatform = false;
    }

    @Override
    public void createPlatform(float cameraPositionX, float cameraPositionY) {
        double random = Math.random();
        if (random >= 0.75 && !triplePlatform) {
            //platform1.setPosition(0,(cameraPositionY/18)-5);
            platform1 = new Platform(myWorld, "CU", 0, nextPlatformPosition, 4 , 1);
            platform2 = new Platform(myWorld, "CU", 7, nextPlatformPosition, 4 , 1);
            platform3 = new Platform(myWorld, "CU", 14, nextPlatformPosition, 4 , 1);
            ground.addActor(platform1);
            ground.addActor(platform2);
            ground.addActor(platform3);
            triplePlatform = true;
            doublePlatform = false;
            cuadruplePlatform = false;
            nextPlatformPosition +=2;
        }

        else if (random >= 0.5 && !doublePlatform) {
            platform1 = new Platform(myWorld, "CU", 4, nextPlatformPosition, 4 , 1);
            platform2 = new Platform(myWorld, "CU", 10, nextPlatformPosition, 4 , 1);
            ground.addActor(platform1);
            ground.addActor(platform2);
            doublePlatform = true;
            triplePlatform = false;
            cuadruplePlatform = false;
            nextPlatformPosition +=2;
        }

        else if (random < 0.5 && !cuadruplePlatform) {
            platform1 = new Platform(myWorld, "CU", 0, nextPlatformPosition, 3 , 1);
            platform2 = new Platform(myWorld, "CU", 5, nextPlatformPosition, 3 , 1);
            platform3 = new Platform(myWorld, "CU", 10, nextPlatformPosition, 3 , 1);
            platform4 = new Platform(myWorld, "CU", 15, nextPlatformPosition, 3 , 1);
            ground.addActor(platform1);
            ground.addActor(platform2);
            ground.addActor(platform3);
            ground.addActor(platform4);
            cuadruplePlatform = true;
            doublePlatform = false;
            triplePlatform = false;
            nextPlatformPosition +=2;
        }
       // ground.addActor(new Platform(myWorld, "CU", 0, 3, 10 , 1));
       /* if(Math.random()*50>40){
            ground.addActor(new Platform(myWorld, "CU", 0, (int)cameraPositionY -238, 10 , 1));
        }*/


    }
}
