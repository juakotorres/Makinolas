package cl.makinolas.atk.actors;

import cl.makinolas.atk.actors.ui.MobileGroup;
import cl.makinolas.atk.actors.ui.MobileKeyListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class SimpleInputController extends InputListener implements MobileKeyListener{
    
    private KeyHandable handable;
    
    public SimpleInputController(KeyHandable hd, MobileGroup mob){
        handable = hd;
        if(mob!=null)
            mob.setMobileKeyListener(this);
    }

    @Override
    public boolean keyDown(InputEvent event, int keycode) {
        handable.handleKey(keycode);
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
