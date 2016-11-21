package cl.makinolas.atk.stages;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import cl.makinolas.atk.actors.Background;
import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.Title;
import cl.makinolas.atk.actors.ui.MainBar;

public class MenuStage extends Stage {
  private static boolean hasCorruptSAV;
  
  public MenuStage(Viewport v){
    super(v);
    addActor(new Background("Background/MenuBackground.jpg", getCamera()));
    addActor(new Title("Background/atk.png", 320, 350 ));
    Hero.getInstance().reset();
    MainBar.getInstance().reset();
    OptionsStage.setToFull();
  }

  public static void setCleanSAV() {
	hasCorruptSAV = false;	
  }

  public static void setCorruptSAV() {
	hasCorruptSAV = true;
  }
  
  public static boolean hasCorruptSAV() {
	  return hasCorruptSAV;
  }
}
