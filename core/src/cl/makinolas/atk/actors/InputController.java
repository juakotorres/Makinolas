package cl.makinolas.atk.actors;

import cl.makinolas.atk.actors.friend.Enemies;
import cl.makinolas.atk.actors.ui.BagVis;
import cl.makinolas.atk.actors.ui.MobileGroup;
import cl.makinolas.atk.actors.ui.MobileKeyListener;
import cl.makinolas.atk.audio.GDXSoundEffectsHero;
import cl.makinolas.atk.audio.GDXSoundEffectsPlayer;
import cl.makinolas.atk.stages.AbstractStage;
import cl.makinolas.atk.utils.SaveManager;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class InputController extends InputListener implements MobileKeyListener{

    private Hero hero;
    private GDXSoundEffectsPlayer mplayer = GDXSoundEffectsHero.getInstance();
    public InputController(Hero h, MobileGroup mob){
        hero = h;
        mob.setMobileKeyListener(this);
    }

    @Override
    public boolean keyDown(InputEvent event, int keycode) {
        if(!((AbstractStage) hero.getStage()).isPaused()) {
            switch (keycode) {
                case Input.Keys.LEFT:
                    hero.moveHorizontal(-1, false);
                    break;
                case Input.Keys.RIGHT:
                    hero.moveHorizontal(1, false);
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
                    hero.prevAllie();
                    break;
                case Input.Keys.NUM_2:
                    hero.nextAllie();
                    break;
                    
                //BORRAR!!!!!!!!!!!!!!!!!!!!
                case Input.Keys.NUM_3:
                	hero.foo();
                	break;
                	
                case Input.Keys.H:
                	hero.gainExp(100, Enemies.EEVEE);
                	break;
                case Input.Keys.NUM_8:
                    SaveManager.getInstance().saveState();
                    break;
                case Input.Keys.P:
                    ((AbstractStage) hero.getStage()).togglePause();
                    break;
            }
        }
        else{
            switch (keycode) {
                case Input.Keys.P:
                    ((AbstractStage) hero.getStage()).togglePause();
                    mplayer.PlayPauseMenuOut();
                    break;
                default:
                	
                    BagVis.getInstance().handleKey(keycode);
                    break;
            }
        }
        return true;
    }

    @Override
    public boolean keyUp(InputEvent event, int keycode) {
        if(((AbstractStage) hero.getStage()).isPaused()) return true;
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
                hero.isNotPressingSpace();
                break;
            case Input.Keys.Z:
                hero.isNotPressingPrimaryAttack();
                break;
        }
        return true;
    }

    @Override
    public void onMobileKeyDown(MobileGroup.MobileKeys key) {
        if(!((AbstractStage) hero.getStage()).isPaused()) {
            switch (key) {
                case LEFT:
                    hero.moveHorizontal(-1, false);
                    break;
                case RIGHT:
                    hero.moveHorizontal(1, false);
                    break;
                case UP:
                    hero.jump(1);
                    break;
                case A:
                    hero.attackPrimary();
                    break;
                case ITEM1:
                    hero.getInventory().useSelItem1();
                    break;
                case ITEM2:
                    hero.getInventory().useSelItem2();
                    break;
                case CHG:
                    hero.nextAllie();
                    break;
                case PAUSE:
                    ((AbstractStage) hero.getStage()).togglePause();
                    break;
            }
        }
        else{
            switch (key) {
                case PAUSE:
                    ((AbstractStage) hero.getStage()).togglePause();
                    break;
                default:
                    BagVis.getInstance().handleKey(key);
                    break;
            }
        }
    }

    @Override
    public void onMobileKeyUp(MobileGroup.MobileKeys k) {
        if(((AbstractStage) hero.getStage()).isPaused()) return;
        switch (k) {
            case LEFT:
                hero.moveHorizontal(1,true);
                break;
            case RIGHT:
                hero.moveHorizontal(-1,true);
                break;
            case UP:
                hero.isNotPressingSpace();
                break;
        default:
          break;
        }
    }
}
