package cl.makinolas.atk.stages;

import com.badlogic.gdx.scenes.scene2d.Stage;

import cl.makinolas.atk.actors.Background;
import cl.makinolas.atk.actors.Title;

public class MenuStage extends Stage {
  
  public MenuStage(){
    addActor(new Background("Background/MenuBackground.jpg", getCamera()));
    addActor(new Title("Background/atk.png", 320, 350 ));
  }
}
