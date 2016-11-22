package cl.makinolas.atk.platformCreator;

import cl.makinolas.atk.actors.platform.Platform;
import cl.makinolas.atk.stages.AbstractStage;
import cl.makinolas.atk.stages.CameraPosition;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by belisariops on 11/22/16.
 */
public abstract class AbstractPlatformCreator extends Actor implements Observer,PlatformCreator {
    protected int lastXPosition;
    protected int lastYPosition;
    protected Group ground;
    protected World myWorld;

    protected AbstractPlatformCreator(World suMundo, AbstractStage stage,
                                   int xInitialPosition, int yInitialPosition, Group ground) {
        stage.cameraObserver.addObserver(this);

        myWorld = suMundo;
        this.ground = ground;
        this.lastXPosition = xInitialPosition;
        this.lastYPosition = yInitialPosition;

    }

    @Override
    public void update(Observable o, Object arg) {
        float cameraPositionX = ((CameraPosition) o).getPositionX();
        float cameraPositionY = ((CameraPosition) o).getPositionY();
        createPlatform(cameraPositionX,cameraPositionY);


    }

    /*En este metodo debe ir la generacion de plataformas (en funcion de la posicion de la camara),
     *esto sera llamado en cada frame.                                                              */
    public abstract void createPlatform(float cameraPositionX, float cameraPositionY);

}
