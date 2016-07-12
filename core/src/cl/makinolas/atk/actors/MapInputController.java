package cl.makinolas.atk.actors;

import cl.makinolas.atk.actors.ui.MobileGroup;
import cl.makinolas.atk.actors.ui.MobileKeyListener;
import cl.makinolas.atk.stages.MapStage;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Created by nbravo on 06-07-16.
 */
public class MapInputController extends InputListener implements MobileKeyListener{
    
    private MapStage myMap;
    
    public MapInputController(MapStage map, MobileGroup mob){
        myMap = map;
        mob.setMobileKeyListener(this);
    }

    @Override
    public boolean keyDown(InputEvent event, int keycode) {
        if (myMap.getActualLevel().canGoForward(keycode))
            myMap.nextLevel();
        else if (myMap.getActualLevel().canGoBackward(keycode))
            myMap.lastLevel();
        return true;
    }

    @Override
    public void onMobileKeyDown(MobileGroup.MobileKeys key) {
        //intentionally blank
    }

    @Override
    public void onMobileKeyUp(MobileGroup.MobileKeys k) {
        //intentionally blank
    }
}
