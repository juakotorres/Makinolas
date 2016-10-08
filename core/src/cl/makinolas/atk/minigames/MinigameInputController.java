package cl.makinolas.atk.minigames;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import cl.makinolas.atk.actors.ui.BagVis;
import cl.makinolas.atk.actors.ui.MobileGroup;
import cl.makinolas.atk.actors.ui.MobileKeyListener;
import cl.makinolas.atk.stages.AbstractStage;

public class MinigameInputController extends InputListener implements MobileKeyListener{
  
  private MinigameCharacter hero;

  public MinigameInputController(MinigameCharacter h, MobileGroup mob){
      hero = h;
      mob.setMobileKeyListener(this);
  }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        hero.jump(1);
        return true;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        hero.isNotPressingSpace();
    }

    @Override
  public boolean keyDown(InputEvent event, int keycode) {
      if(!((AbstractStage) hero.getStage()).isPaused()) {
          switch (keycode) {
              case Input.Keys.SPACE:
                  hero.jump(1);
                  break;
              case Input.Keys.UP:
                  hero.jump(2);
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
          case Input.Keys.SPACE:
            hero.isNotPressingSpace();
            break;
          case Input.Keys.UP:
            hero.isNotPressingSpace();
            break;
          
            
      }
      return true;
  }

  @Override
  public void onMobileKeyDown(MobileGroup.MobileKeys key) {
      if(((AbstractStage) hero.getStage()).isPaused()) return;
      switch(key){
          case UP:
              hero.jump(1);
              break;
      default:
        break;
      }
  }

  @Override
  public void onMobileKeyUp(MobileGroup.MobileKeys k) {
      if(((AbstractStage) hero.getStage()).isPaused()) return;
      switch (k) {
          case UP:
            hero.isNotPressingSpace();
            break;
      default:
        break;
      }
  }

}

