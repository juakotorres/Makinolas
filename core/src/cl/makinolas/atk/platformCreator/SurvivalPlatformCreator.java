
        package cl.makinolas.atk.platformCreator;

        import cl.makinolas.atk.actors.GameActor;
        import cl.makinolas.atk.actors.platform.Platform;
        import cl.makinolas.atk.actors.platform.SurvivalPlatform;
        import cl.makinolas.atk.actors.platform.myPlatform;
        import cl.makinolas.atk.gamemodes.SurvivalModeStage;
        import cl.makinolas.atk.stages.AbstractStage;
        import com.badlogic.gdx.math.Vector2;
        import com.badlogic.gdx.physics.box2d.World;
        import com.badlogic.gdx.scenes.scene2d.Group;
        import com.badlogic.gdx.utils.Array;

/**
 * Created by belisariops on 11/22/16.
 */
public class SurvivalPlatformCreator extends AbstractPlatformCreator {
    Platform platform1;
    Platform platform2;
    Platform platform3;
    Platform platform4;
    private float gravity;
    private int nextPlatformPosition;
    private boolean triplePlatform;
    private boolean doublePlatform;
    private boolean cuadruplePlatform;
    private float playerHeight;
    private Array<GameActor> gameActors;
    private AbstractStage stage;
    private float lasPosition;
    private Group mons;

    public SurvivalPlatformCreator(World suMundo, AbstractStage stage,
                                   int xInitialPosition, int yInitialPosition, Group ground,Group mons, Array<GameActor> gameActors) {
        super(suMundo,stage,xInitialPosition,yInitialPosition,ground);
        nextPlatformPosition = 3;
        triplePlatform = false;
        doublePlatform = false;
        cuadruplePlatform = false;
        gravity = 100;
        playerHeight=0;
        lasPosition = 240;
        this.stage = stage;
        this.mons = mons;
        this.gameActors = gameActors;
    }

    public void setPlayerHeight(float h) {
        playerHeight = h;
    }

    @Override
    public void createPlatform(float cameraPositionX, float cameraPositionY) {
        if (cameraPositionY < lasPosition+5 ) {
            //System.out.println(this.lastYPosition);
          return;
        }
        double random = Math.random();
        if (random >= 0.75 && !triplePlatform) {
            //platform1.setPosition(0,(cameraPositionY/18)-5);
            platform1 = new SurvivalPlatform(myWorld, "CU", 0, nextPlatformPosition, 4 , 1, (SurvivalModeStage)stage,mons,cameraPositionY );
            platform2 = new myPlatform(myWorld, "CU", 7, nextPlatformPosition, 4 , 1,cameraPositionY);
            platform3 = new myPlatform(myWorld, "CU", 14, nextPlatformPosition, 4 , 1,cameraPositionY);
            ground.addActor(platform1);
            ground.addActor(platform2);
            ground.addActor(platform3);
            gameActors.add(platform1);
            gameActors.add(platform2);
            gameActors.add(platform3);
            triplePlatform = true;
            doublePlatform = false;
            cuadruplePlatform = false;

        }

        else if (random >= 0.5 && !doublePlatform) {
            platform1 = new myPlatform(myWorld, "CU", 3, nextPlatformPosition, 5 , 1,cameraPositionY);
            platform2 = new myPlatform(myWorld, "CU", 11, nextPlatformPosition, 5 , 1,cameraPositionY);
            ground.addActor(platform1);
            ground.addActor(platform2);
            doublePlatform = true;
            triplePlatform = false;
            cuadruplePlatform = false;
        }

        else if (random < 0.5 && !cuadruplePlatform) {
            platform1 = new myPlatform(myWorld, "CU", 0, nextPlatformPosition, 3 , 1,cameraPositionY);
            platform2 = new myPlatform(myWorld, "CU", 5, nextPlatformPosition, 3 , 1,cameraPositionY);
            platform3 = new myPlatform(myWorld, "CU", 10, nextPlatformPosition, 3 , 1,cameraPositionY);
            platform4 = new myPlatform(myWorld, "CU", 15, nextPlatformPosition, 3 , 1,cameraPositionY);
            ground.addActor(platform1);
            ground.addActor(platform2);
            ground.addActor(platform3);
            ground.addActor(platform4);
            cuadruplePlatform = true;
            doublePlatform = false;
            triplePlatform = false;
        }

        else
            return;

        nextPlatformPosition +=2;//+(100 * playerHeight);

        myWorld.setGravity(new Vector2(0,-(gravity))); //-(playerHeight * gravity * 0.1f))));
        lasPosition = cameraPositionY;
        // ground.addActor(new Platform(myWorld, "CU", 0, 3, 10 , 1));
       /* if(Math.random()*50>40){
            ground.addActor(new Platform(myWorld, "CU", 0, (int)cameraPositionY -238, 10 , 1));
        }*/


    }
}
