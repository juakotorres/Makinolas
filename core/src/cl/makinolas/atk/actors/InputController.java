package cl.makinolas.atk.actors;

import cl.makinolas.atk.actors.ui.MobileGroup;
import cl.makinolas.atk.actors.ui.MobileKeyListener;
import cl.makinolas.atk.utils.SaveInstance;
import cl.makinolas.atk.utils.SaveManager;
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
                hero.jump(1);
                break;
            case Input.Keys.UP:
                hero.jump(2);
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
                hero.nextAllie();
                break;
            case Input.Keys.NUM_2:
                hero.prevAllie();
                break;
            case Input.Keys.NUM_8:
                SaveInstance save = new SaveInstance();
                save.heroX = hero.getBody().getPosition().x;
                save.heroY = hero.getBody().getPosition().y;
                save.friends = hero.saveMyFriends();
                SaveManager.getInstance().saveData(save,"ATK.sav");
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
            case Input.Keys.SPACE:
                hero.isNotPressingSpace();
                break;
            case Input.Keys.UP:
                hero.isNotPressingUp();
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
                hero.jump(1);
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
            case UP:
                hero.isNotPressingUp();
                break;
        default:
          break;
        }
    }
}
