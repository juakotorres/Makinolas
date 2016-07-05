package cl.makinolas.atk.stages;

import com.badlogic.gdx.scenes.scene2d.Stage;

import cl.makinolas.atk.actors.Background;
import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.Title;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MenuStage extends Stage {
  
  public MenuStage(Viewport v){
    super(v);
    addActor(new Background("Background/MenuBackground.jpg", getCamera()));
    addActor(new Title("Background/atk.png", 320, 350 ));
    Hero.getInstance().reset();
  }
}
