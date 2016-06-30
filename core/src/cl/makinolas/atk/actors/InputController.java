package cl.makinolas.atk.actors;

import cl.makinolas.atk.actors.ui.MobileGroup;
import cl.makinolas.atk.actors.ui.MobileKeyListener;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class InputController extends InputListener implements MobileKeyListener{

    private Hero hero;

    public InputController(Hero h, MobileGroup mob){
        hero = h;
        mob.setMobileKeyListener(this);
    }

    @Override
    public boolean keyDown(InputEvent event, int keycode) {
        switch (keycode){
            case Input.Keys.LEFT:
                hero.moveHorizontal(-1,false);
                break;
            case Input.Keys.RIGHT:
                hero.moveHorizontal(1,false);
                break;
            case Input.Keys.SPACE:
            case Input.Keys.UP:
                hero.jump();
                break;
            case Input.Keys.A:
                hero.getInventory().useSelItem1();
                break;
            case Input.Keys.S:
                hero.getInventory().useSelItem2();
                break;
            case Input.Keys.Z:
                hero.attackPrimary();
                break;
            case Input.Keys.X:
                hero.attackSecondary();
                break;
            case Input.Keys.NUM_1:
                hero.changeAllie(0);
                break;
            case Input.Keys.NUM_2:
                hero.changeAllie(1);
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(InputEvent event, int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
                hero.moveHorizontal(1,true);
                break;
            case Input.Keys.RIGHT:
                hero.moveHorizontal(-1,true);
                break;
        }
        return true;
    }

    @Override
    public void onMobileKeyDown(MobileGroup.MobileKeys key) {
        switch(key){
            case LEFT:
                hero.moveHorizontal(-1,false);
                break;
            case RIGHT:
                hero.moveHorizontal(1,false);
                break;
            case UP:
                hero.jump();
                break;
            case A:
                hero.attackPrimary();
                break;
        }
    }

    @Override
    public void onMobileKeyUp(MobileGroup.MobileKeys k) {
        switch (k) {
            case LEFT:
                hero.moveHorizontal(1,true);
                break;
            case RIGHT:
                hero.moveHorizontal(-1,true);
                break;
        }
    }
}
