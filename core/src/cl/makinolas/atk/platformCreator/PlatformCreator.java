package cl.makinolas.atk.platformCreator;

import java.util.Observable;

/**
 * Created by belisariops on 11/22/16.
 */
public interface PlatformCreator {

    public void update(Observable o, Object arg);

    public abstract void createPlatform(float cameraPositionX, float cameraPositionY);
}
